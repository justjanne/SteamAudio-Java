UID = $(shell id -u)
GID = $(shell id -g)

all: build ../target/steamaudio-native-darwin_universal.jar ../target/steamaudio-native-linux_x64.jar ../target/steamaudio-native-win32.jar ../target/steamaudio-native-win64.jar

build:
	mkdir -p ../target
	mkdir -p build/steamaudio-native/lib/darwin_universal
	mkdir -p build/steamaudio-native/lib/linux_x64
	mkdir -p build/steamaudio-native/lib/win32
	mkdir -p build/steamaudio-native/lib/win64

build/steamaudio-native/lib/darwin_universal/libsteamaudio-bridge.dylib: src/steamaudio-bridge.c src/steamaudio-bridge.h
	docker run --rm -v $(CURDIR):/workdir -u $(UID):$(GID) -e CROSS_TRIPLE=x86_64-apple-darwin -e ARCH="darwin_universal" -e ARGS="-arch i386 -arch x86_64" -e EXT="dylib" -e PREFIX="lib" multiarch/crossbuild ./makenative.sh

build/steamaudio-native/lib/linux_x64/libsteamaudio-bridge.so: src/steamaudio-bridge.c src/steamaudio-bridge.h
	docker run --rm -v $(CURDIR):/workdir -u $(UID):$(GID) -e ARCH="linux_x64" -e ARGS="-m64" -e EXT="so" -e PREFIX="lib" multiarch/crossbuild ./makenative.sh
	
build/steamaudio-native/lib/win32/steamaudio-bridge.dll: src/steamaudio-bridge.c src/steamaudio-bridge.h
	docker run --rm -v $(CURDIR):/workdir -u $(UID):$(GID) -e CROSS_TRIPLE=i686-w64-mingw32 -e ARCH="win32" -e EXT="dll" multiarch/crossbuild ./makenative.sh

build/steamaudio-native/lib/win64/steamaudio-bridge.dll: src/steamaudio-bridge.c src/steamaudio-bridge.h
	docker run --rm -v $(CURDIR):/workdir -u $(UID):$(GID) -e CROSS_TRIPLE=x86_64-w64-mingw32 -e ARCH="win64" -e EXT="dll" multiarch/crossbuild ./makenative.sh

build/steamaudio-native/lib/darwin_universal/libsteamaudio.dylib: lib/darwin_universal/libsteamaudio.dylib
	cp -r lib/darwin_universal/libsteamaudio.dylib build/steamaudio-native/lib/darwin_universal/libsteamaudio.dylib

build/steamaudio-native/lib/linux_x64/libsteamaudio.so: lib/linux_x64/libsteamaudio.so
	cp -r lib/linux_x64/libsteamaudio.so build/steamaudio-native/lib/linux_x64/libsteamaudio.so

build/steamaudio-native/lib/win32/steamaudio.dll: lib/win32/steamaudio.dll
	cp -r lib/win32/steamaudio.dll build/steamaudio-native/lib/win32/steamaudio.dll

build/steamaudio-native/lib/win64/steamaudio.dll: lib/win64/steamaudio.dll
	cp -r lib/win64/steamaudio.dll build/steamaudio-native/lib/win64/steamaudio.dll

../target/steamaudio-native-darwin_universal.jar: build/steamaudio-native/lib/darwin_universal/libsteamaudio.dylib build/steamaudio-native/lib/darwin_universal/libsteamaudio-bridge.dylib
	cd build/steamaudio-native/ && zip -r9 ../../../target/steamaudio-native-darwin_universal.jar lib/darwin_universal/libsteamaudio.dylib lib/darwin_universal/libsteamaudio-bridge.dylib

../target/steamaudio-native-linux_x64.jar: build/steamaudio-native/lib/linux_x64/libsteamaudio.so build/steamaudio-native/lib/linux_x64/libsteamaudio-bridge.so
	cd build/steamaudio-native/ && zip -r9 ../../../target/steamaudio-native-linux_x64.jar lib/linux_x64/libsteamaudio.so lib/linux_x64/libsteamaudio-bridge.so

../target/steamaudio-native-win32.jar: build/steamaudio-native/lib/win32/steamaudio.dll build/steamaudio-native/lib/win32/steamaudio-bridge.dll
	cd build/steamaudio-native/ && zip -r9 ../../../target/steamaudio-native-win32.jar lib/win32/steamaudio.dll lib/win32/steamaudio-bridge.dll

../target/steamaudio-native-win64.jar: build/steamaudio-native/lib/win64/steamaudio.dll build/steamaudio-native/lib/win64/steamaudio-bridge.dll
	cd build/steamaudio-native/ && zip -r9 ../../../target/steamaudio-native-win64.jar lib/win64/steamaudio.dll lib/win64/steamaudio-bridge.dll
