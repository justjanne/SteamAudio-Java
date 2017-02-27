/*
 * Copyright 2011 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.kuschku.steamaudio.demo;

import java.io.*;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.UUID;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Loads shared libraries from JAR files. Call {@link SharedLibraryLoader#load() to load the
 * required LWJGL 3 native shared libraries.
 *
 * @author mzechner
 * @author Nathan Sweet
 */
public class SharedLibraryLoader {
    private static final boolean isARM = System.getProperty("os.arch").startsWith("arm");
    // JDK 8 only.
    private static final String abi =
            (System.getProperty("sun.arch.abi") != null ? System.getProperty("sun.arch.abi") : "");
    static private final HashSet<String> loadedLibraries = new HashSet<>();
    private static boolean isWindows = System.getProperty("os.name").contains("Windows");
    private static boolean isLinux = System.getProperty("os.name").contains("Linux");
    private static boolean isMac = System.getProperty("os.name").contains("Mac");
    private static boolean isIos = false;
    private static boolean isAndroid = false;
    private static boolean is64Bit =
            System.getProperty("os.arch").equals("amd64") || System.getProperty("os.arch").equals("x86_64");
    private static boolean load = true;

    static {
        String vm = System.getProperty("java.runtime.name");
        if (vm != null && vm.contains("Android Runtime")) {
            isAndroid = true;
            isWindows = false;
            isLinux = false;
            isMac = false;
            is64Bit = false;
        }
        if (!isAndroid && !isWindows && !isLinux && !isMac) {
            isIos = true;
            is64Bit = false;
        }
    }

    static {
        // Don't extract natives if using JWS.
        try {
            Method method = Class.forName("javax.jnlp.ServiceManager").getDeclaredMethod("lookup", String.class);
            method.invoke(null, "javax.jnlp.PersistenceService");
            load = false;
        } catch (Throwable ex) {
            load = true;
        }
    }

    private String nativesJar;

    private SharedLibraryLoader() {
    }

    /**
     * Extracts the LWJGL native libraries from the classpath and sets the "org.lwjgl.librarypath" system property.
     */
    public static synchronized void load() {
        load(false);
    }

    /**
     * Extracts the LWJGL native libraries from the classpath and sets the "org.lwjgl.librarypath" system property.
     */
    public static synchronized void load(boolean disableOpenAL) {
        if (!load) return;

        SharedLibraryLoader loader = new SharedLibraryLoader();
        File nativesDir = null;
        try {
            if (SharedLibraryLoader.isWindows) {
                nativesDir = loader.extractFile(SharedLibraryLoader.is64Bit ? "lwjgl.dll" : "lwjgl32.dll", null)
                        .getParentFile();
                if (!disableOpenAL) loader.extractFile(SharedLibraryLoader.is64Bit ? "OpenAL.dll" : "OpenAL32.dll",
                        nativesDir.getName());
            } else if (SharedLibraryLoader.isMac) {
                nativesDir = loader.extractFile("liblwjgl.dylib", null).getParentFile();
                if (!disableOpenAL) loader.extractFile("libopenal.dylib", nativesDir.getName());
            } else if (SharedLibraryLoader.isLinux) {
                nativesDir = loader.extractFile(SharedLibraryLoader.is64Bit ? "liblwjgl.so" : "liblwjgl32.so", null)
                        .getParentFile();
                if (!disableOpenAL) loader.extractFile(SharedLibraryLoader.is64Bit ? "libopenal.so" : "libopenal32.so",
                        nativesDir.getName());
            }
        } catch (Throwable ex) {
            throw new RuntimeException("Unable to extract LWJGL natives.", ex);
        }
        if (nativesDir != null) System.setProperty("org.lwjgl.librarypath", nativesDir.getAbsolutePath());
        load = false;
    }

    /**
     * Returns a CRC of the remaining bytes in the stream.
     */
    private String crc(InputStream input) {
        if (input == null) throw new IllegalArgumentException("input cannot be null.");
        CRC32 crc = new CRC32();
        byte[] buffer = new byte[4096];
        try {
            while (true) {
                int length = input.read(buffer);
                if (length == -1) break;
                crc.update(buffer, 0, length);
            }
        } catch (Exception ex) {
            try {
                input.close();
            } catch (IOException ignored) {
            }
        }
        return Long.toString(crc.getValue(), 16);
    }

    /**
     * Maps a platform independent library name to a platform dependent name.
     */
    private String mapLibraryName(String libraryName) {
        if (isWindows) return libraryName + (is64Bit ? "64.dll" : ".dll");
        if (isLinux) return "lib" + libraryName + (isARM ? "arm" + abi : "") + (is64Bit ? "64.so" : ".so");
        if (isMac) return "lib" + libraryName + (is64Bit ? "64.dylib" : ".dylib");
        return libraryName;
    }

    /**
     * Loads a shared library for the platform the application is running on.
     *
     * @param libraryName The platform independent library name. If not contain a prefix (eg lib) or suffix (eg .dll).
     */
    public synchronized void load(String libraryName) {
        // in case of iOS, things have been linked statically to the executable, bail out.
        if (isIos) return;

        libraryName = mapLibraryName(libraryName);
        if (loadedLibraries.contains(libraryName)) return;

        try {
            if (isAndroid) System.loadLibrary(libraryName);
            else loadFile(libraryName);
        } catch (Throwable ex) {
            throw new RuntimeException(
                    "Couldn't load shared library '" + libraryName + "' for target: " + System.getProperty("os.name") +
                            (is64Bit ? ", 64-bit" : ", 32-bit"), ex);
        }
        loadedLibraries.add(libraryName);
    }

    private InputStream readFile(String path) {
        if (nativesJar == null) {
            InputStream input = SharedLibraryLoader.class.getResourceAsStream("/" + path);
            if (input == null) throw new RuntimeException("Unable to read file for extraction: " + path);
            return input;
        }

        // Read from JAR.
        ZipFile file = null;
        try {
            file = new ZipFile(nativesJar);
            ZipEntry entry = file.getEntry(path);
            if (entry == null) throw new RuntimeException("Couldn't find '" + path + "' in JAR: " + nativesJar);
            return file.getInputStream(entry);
        } catch (IOException ex) {
            throw new RuntimeException("Error reading '" + path + "' in JAR: " + nativesJar, ex);
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    /**
     * Extracts the specified file into the temp directory if it does not already exist or the CRC does not match. If
     * file extraction fails and the file exists at java.library.path, that file is returned.
     *
     * @param sourcePath The file to extract from the classpath or JAR.
     * @param dirName    The name of the subdirectory where the file will be extracted. If null, the file's CRC will be
     *                   used.
     *
     * @return The extracted file.
     */
    private File extractFile(String sourcePath, String dirName) throws IOException {
        try {
            String sourceCrc = crc(readFile(sourcePath));
            if (dirName == null) dirName = sourceCrc;

            File extractedFile = getExtractedFile(dirName, new File(sourcePath).getName());
            return extractFile(sourcePath, sourceCrc, extractedFile);
        } catch (RuntimeException ex) {
            // Fallback to file at java.library.path location, eg for applets.
            File file = new File(System.getProperty("java.library.path"), sourcePath);
            if (file.exists()) return file;
            throw ex;
        }
    }

    /**
     * Returns a path to a file that can be written. Tries multiple locations and verifies writing succeeds.
     */
    private File getExtractedFile(String dirName, String fileName) {
        // Temp directory with username in path.
        File idealFile = new File(
                System.getProperty("java.io.tmpdir") + "/libgdx" + System.getProperty("user.name") + "/" + dirName,
                fileName);
        if (canWrite(idealFile)) return idealFile;

        // System provided temp directory.
        try {
            File file = File.createTempFile(dirName, null);
            if (file.delete()) {
                file = new File(file, fileName);
                if (canWrite(file)) return file;
            }
        } catch (IOException ignored) {
        }

        // User home.
        File file = new File(System.getProperty("user.home") + "/.libgdx/" + dirName, fileName);
        if (canWrite(file)) return file;

        // Relative directory.
        file = new File(".temp/" + dirName, fileName);
        if (canWrite(file)) return file;

        return idealFile; // Will likely fail, but we did our best.
    }

    /**
     * Returns true if the parent directories of the file can be created and the file can be written.
     */
    private boolean canWrite(File file) {
        File parent = file.getParentFile();
        File testFile;
        if (file.exists()) {
            if (!file.canWrite() || !canExecute(file)) return false;
            // Don't overwrite existing file just to check if we can write to directory.
            testFile = new File(parent, UUID.randomUUID().toString());
        } else {
            parent.mkdirs();
            if (!parent.isDirectory()) return false;
            testFile = file;
        }
        try {
            new FileOutputStream(testFile).close();
            return canExecute(testFile);
        } catch (Throwable ex) {
            return false;
        } finally {
            testFile.delete();
        }
    }

    private boolean canExecute(File file) {
        try {
            Method canExecute = File.class.getMethod("canExecute");
            if ((Boolean) canExecute.invoke(file)) return true;

            Method setExecutable = File.class.getMethod("setExecutable", boolean.class, boolean.class);
            setExecutable.invoke(file, true, false);

            return (Boolean) canExecute.invoke(file);
        } catch (Exception ignored) {
        }
        return false;
    }

    private File extractFile(String sourcePath, String sourceCrc, File extractedFile) {
        String extractedCrc = null;
        if (extractedFile.exists()) {
            try {
                extractedCrc = crc(new FileInputStream(extractedFile));
            } catch (FileNotFoundException ignored) {
            }
        }

        // If file doesn't exist or the CRC doesn't match, extract it to the temp dir.
        if (extractedCrc == null || !extractedCrc.equals(sourceCrc)) {
            try {
                InputStream input = readFile(sourcePath);
                extractedFile.getParentFile().mkdirs();
                FileOutputStream output = new FileOutputStream(extractedFile);
                byte[] buffer = new byte[4096];
                while (true) {
                    int length = input.read(buffer);
                    if (length == -1) break;
                    output.write(buffer, 0, length);
                }
                input.close();
                output.close();
            } catch (IOException ex) {
                throw new RuntimeException(
                        "Error extracting file: " + sourcePath + "\nTo: " + extractedFile.getAbsolutePath(), ex);
            }
        }

        return extractedFile;
    }

    /**
     * Extracts the source file and calls System.load. Attemps to extract and load from multiple locations. Throws
     * runtime exception if all fail.
     */
    private void loadFile(String sourcePath) {
        String sourceCrc = crc(readFile(sourcePath));

        String fileName = new File(sourcePath).getName();

        // Temp directory with username in path.
        File file = new File(
                System.getProperty("java.io.tmpdir") + "/libgdx" + System.getProperty("user.name") + "/" + sourceCrc,
                fileName);
        Throwable ex = loadFile(sourcePath, sourceCrc, file);
        if (ex == null) return;

        // System provided temp directory.
        try {
            file = File.createTempFile(sourceCrc, null);
            if (file.delete() && loadFile(sourcePath, sourceCrc, file) == null) return;
        } catch (Throwable ignored) {
        }

        // User home.
        file = new File(System.getProperty("user.home") + "/.libgdx/" + sourceCrc, fileName);
        if (loadFile(sourcePath, sourceCrc, file) == null) return;

        // Relative directory.
        file = new File(".temp/" + sourceCrc, fileName);
        if (loadFile(sourcePath, sourceCrc, file) == null) return;

        // Fallback to java.library.path location, eg for applets.
        file = new File(System.getProperty("java.library.path"), sourcePath);
        if (file.exists()) {
            System.load(file.getAbsolutePath());
            return;
        }

        throw new RuntimeException(ex);
    }

    /**
     * @return null if the file was extracted and loaded.
     */
    private Throwable loadFile(String sourcePath, String sourceCrc, File extractedFile) {
        try {
            System.load(extractFile(sourcePath, sourceCrc, extractedFile).getAbsolutePath());
            return null;
        } catch (Throwable ex) {
            ex.printStackTrace();
            return ex;
        }
    }
}
