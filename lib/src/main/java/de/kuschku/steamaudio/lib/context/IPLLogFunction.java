package de.kuschku.steamaudio.lib.context;

import org.bridj.Callback;
import org.bridj.Pointer;
import org.bridj.ann.Ptr;

public abstract class IPLLogFunction extends Callback<IPLLogFunction> {
    public void apply(Pointer<Byte> message) {
        apply(Pointer.getPeer(message));
    }

    @SuppressWarnings("deprecation")
    public void apply(@Ptr long message) {
        apply(Pointer.pointerToAddress(message, Byte.class));
    }
}
