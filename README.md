# SteamAudio-Java
Java Bindings for Steam Audio

##Supported Platforms

This library supports Windows (i386/x86_64), Linux (i386/x86_64) and macOS. Only Linux-x86_64 was tested at this time.

##Documentation

The Javadoc is available here: http://dl.kuschku.de/javadoc/steamaudio-java  
A tutorial for the library will be provided at a later time.

##Future Plans

In the future I intend to provide an object-oriented wrapper for the library, to make working with the library easier.

##License

The native binaries (in the subfolder `natives/` and the gradle subproject `:natives`) are under the Valve SDK license, as described in [LICENSE.steam-audio.md](https://github.com/justjanne/SteamAudio-Java/blob/master/LICENSE.steam-audio.md).

The Java bindings, and the demo projects (in the subfolders `demo/` and `lib/`, and the gradle subprojects `:demo` and `:lib`) are under Mozilla Public License, as described in [LICENSE.bindings.md](https://github.com/justjanne/SteamAudio-Java/blob/master/LICENSE.bindings.md)

The Demo Audio (in `demo/src/main/resources/demo-audio.ogg`) is the title "High as a Kite" from the Big Buck Bunny soundtrack by Jan Morgenstern and licensed under CC-BY-NC-ND 3.0.
