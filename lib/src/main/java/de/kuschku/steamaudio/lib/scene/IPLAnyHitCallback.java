package de.kuschku.steamaudio.lib.scene;

import org.bridj.Callback;
import org.bridj.Pointer;
import org.bridj.ann.Ptr;

public abstract class IPLAnyHitCallback extends Callback<IPLAnyHitCallback> {
    public void apply(Pointer<Float> origin, Pointer<Float> direction, float minDistance, float maxDistance,
                      Pointer<Integer> hitExists, Pointer<?> userData) {
        apply(Pointer.getPeer(origin), Pointer.getPeer(direction), minDistance, maxDistance, Pointer.getPeer(hitExists),
                Pointer.getPeer(userData));
    }

    @SuppressWarnings("deprecation")
    public void apply(@Ptr long origin, @Ptr long direction, float minDistance, float maxDistance, @Ptr long hitExists,
                      @Ptr long userData) {
        apply(Pointer.pointerToAddress(origin, Float.class), Pointer.pointerToAddress(direction, Float.class),
                minDistance, maxDistance, Pointer.pointerToAddress(hitExists, Integer.class),
                Pointer.pointerToAddress(userData));
    }
}
