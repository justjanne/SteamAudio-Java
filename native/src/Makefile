OUT_FILE_PATH = ../build/steamaudio-native/lib/$(ARCH)/$(PREFIX)steamaudio-bridge.$(EXT)

${OUT_FILE_PATH}: ../src/steamaudio-bridge.c ../src/steamaudio-bridge.h ../include/steamaudio.h
	mkdir -p ../build/steamaudio-native/lib/$(ARCH)/
	cc $(ARGS) -shared -fPIC -o ${OUT_FILE_PATH} -L../lib/$(ARCH) -I../include -lsteamaudio ../src/steamaudio-bridge.c
