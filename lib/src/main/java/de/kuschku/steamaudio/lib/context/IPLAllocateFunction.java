package de.kuschku.steamaudio.lib.context;

import org.bridj.Callback;
import org.bridj.Pointer;
import org.bridj.ann.Ptr;

@SuppressWarnings("deprecation")
public abstract class IPLAllocateFunction extends Callback<IPLAllocateFunction> {
    public Pointer<?> apply(@Ptr long IPLsize1, @Ptr long IPLsize2) {
        return Pointer.pointerToAddress(apply$2(IPLsize1, IPLsize2));
    }

    @Ptr
    public long apply$2(@Ptr long IPLsize1, @Ptr long IPLsize2) {
        return Pointer.getPeer(apply(IPLsize1, IPLsize2));
    }
}
