# SteamAudio-Java
Java Bindings for Steam Audio

## Supported Platforms

This library supports Windows (i386/x86_64), Linux (i386/x86_64) and macOS. Only
Linux-x86_64 was tested at this time.

## Build

To build the library, run `./gradlew :lib:jar :make`.

To run the demo, run `./gradlew :demo:run`

The project can only be built on Linux, but will create libraries for all
supported systems and architectures.

## Documentation

The Javadoc is available here: http://dl.kuschku.de/javadoc/steamaudio-java  
A tutorial for the library will be provided at a later time.

## Future Plans

Further documentation and integration with Java Sound are planned, as is an
API-compatible interface to paulscode.

## License

The native binaries (in the subfolder `natives/` and the gradle subproject
`:natives`) are under the Valve SDK license, as described in [LICENSE.Valve.md](https://github.com/justjanne/SteamAudio-Java/blob/master/LICENSE.Valve.md).

The Java bindings, and the demo projects (in the folders `demo/` and `lib/`,
and the gradle modules `:demo` and `:lib`) are under Mozilla Public License,
as described in [LICENSE.MPL.md](https://github.com/justjanne/SteamAudio-Java/blob/master/LICENSE.MPL.md)

The Javadoc of the library (in the folder `lib/`,
and the gradle module `:lib`) are derived from the Steam Audio library from Valve, and licensed under the Valve SDK license, as described in [LICENSE.Valve.md](https://github.com/justjanne/SteamAudio-Java/blob/master/LICENSE.Valve.md).

The Demo Audio (in `demo/src/main/resources/sounds/demo-audio.ogg`) is the title
"High as a Kite" from the Big Buck Bunny soundtrack by Jan Morgenstern and
licensed under CC BY-NC-ND 3.0, as described in [LICENSE.CC.BY-NC-ND.3.md](https://github.com/justjanne/SteamAudio-Java/blob/master/LICENSE.CC.BY-NC-ND.3.md).

The LWJGL loader (in `demo/src/main/java/de/kuschku/steamaudio/demo/SharedLibraryLoader.java`)
from Nathan Sweet and mzechner is licensed under Apache2, as described in [LICENSE.Apache.md](https://github.com/justjanne/SteamAudio-Java/blob/master/LICENSE.Apache.md)

The Engine used in the demo (in `demo/src/main/java/org/lwjglb`) from Antonio
Hernández Bejarano is licensed under CC BY-SA 4.0, as described in [LICENSE.CC.BY-SA.4.md](https://github.com/justjanne/SteamAudio-Java/blob/master/LICENSE.CC.BY-SA.4.md). 
