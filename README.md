# SteamAudio-Java
Java Bindings for Steam Audio

##Supported Platforms

This library supports Windows (i386/x86_64), Linux (i386/x86_64) and macOS. Only
Linux-x86_64 was tested at this time.

##Documentation

The Javadoc is available here: http://dl.kuschku.de/javadoc/steamaudio-java  
A tutorial for the library will be provided at a later time.

##Future Plans

Further documentation and integration with Java Sound are planned, as is an
API-compatible interface to paulscode.

##License

The native binaries (in the subfolder `natives/` and the gradle subproject
`:natives`) are under the Valve SDK license, as described in [LICENSE.Valve.md](https://github.com/justjanne/SteamAudio-Java/blob/master/LICENSE.Valve.md).

The Java bindings, and the demo projects (in the subfolders `demo/` and `lib/`,
and the gradle subprojects `:demo` and `:lib`) are under Mozilla Public License,
as described in [LICENSE.MPL.md](https://github.com/justjanne/SteamAudio-Java/blob/master/LICENSE.MPL.md)

The Demo Audio (in `demo/src/main/resources/sounds/demo-audio.ogg`) is the title
"High as a Kite" from the Big Buck Bunny soundtrack by Jan Morgenstern and
licensed under CC BY-NC-ND 3.0, as described in [LICENSE.CC.BY-NC-ND.3.md](https://github.com/justjanne/SteamAudio-Java/blob/master/LICENSE.CC.BY-NC-ND.3.md).

The LWJGL loader (in `demo/src/main/java/de/kuschku/steamaudio/demo/SharedLibraryLoader.java`)
from Nathan Sweet and mzechner is licensed under Apache2, as described in [LICENSE.Apache.md](https://github.com/justjanne/SteamAudio-Java/blob/master/LICENSE.Apache.md)

The Engine used in the demo (in `demo/src/main/java/org/lwjglb`) from Antonio
Hernández Bejarano is licensed under CC BY-SA 4.0, as described in [LICENSE.CC.BY-SA.4.md](https://github.com/justjanne/SteamAudio-Java/blob/master/LICENSE.CC.BY-SA.4.md). 
