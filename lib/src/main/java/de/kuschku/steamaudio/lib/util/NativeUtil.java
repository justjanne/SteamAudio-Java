package de.kuschku.steamaudio.lib.util;

import org.bridj.Pointer;

import java.nio.charset.StandardCharsets;

public class NativeUtil {
    private NativeUtil() {

    }

    public static Pointer<Byte> pointerToString(String text) {
        return Pointer.pointerToBytes(StandardCharsets.UTF_8.encode(text));
    }
}
