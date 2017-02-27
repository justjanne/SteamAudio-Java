package de.kuschku.steamaudio.lib.context;

import org.bridj.Pointer;

import java.util.function.Consumer;

public class Context extends IPLContext {
    public Context logCallback(Consumer<String> logCallback) {
        logCallback(Pointer.getPointer(new IPLLogFunction() {
            @Override
            public void apply(Pointer<Byte> message) {
                logCallback.accept(message.getCString());
            }
        }));
        return this;
    }
}
