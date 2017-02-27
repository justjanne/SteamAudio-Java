package de.kuschku.steamaudio.lib.context;

import org.bridj.Callback;
import org.bridj.Pointer;
import org.bridj.ann.Ptr;

public abstract class IPLFreeFunction extends Callback<IPLFreeFunction> {
    public void apply(Pointer<?> IPLvoidPtr1) {
        apply(Pointer.getPeer(IPLvoidPtr1));
    }

    @SuppressWarnings("deprecation")
    public void apply(@Ptr long IPLvoidPtr1) {
        apply(Pointer.pointerToAddress(IPLvoidPtr1));
    }
}
