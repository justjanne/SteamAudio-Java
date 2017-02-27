package de.kuschku.steamaudio.lib.scene;

import org.bridj.Callback;
import org.bridj.Pointer;
import org.bridj.ann.Ptr;

public abstract class IPLClosestHitCallback extends Callback<IPLClosestHitCallback> {
    public void apply(Pointer<Float> origin, Pointer<Float> direction, float minDistance, float maxDistance,
                      Pointer<Float> hitDistance, Pointer<Float> hitNormal, Pointer<Integer> hitMaterialIndex,
                      Pointer<?> userData) {
        apply(Pointer.getPeer(origin), Pointer.getPeer(direction), minDistance, maxDistance,
                Pointer.getPeer(hitDistance), Pointer.getPeer(hitNormal), Pointer.getPeer(hitMaterialIndex),
                Pointer.getPeer(userData));
    }

    @SuppressWarnings("deprecation")
    public void apply(@Ptr long origin, @Ptr long direction, float minDistance, float maxDistance,
                      @Ptr long hitDistance, @Ptr long hitNormal, @Ptr long hitMaterialIndex, @Ptr long userData) {
        apply(Pointer.pointerToAddress(origin, Float.class), Pointer.pointerToAddress(direction, Float.class),
                minDistance, maxDistance, Pointer.pointerToAddress(hitDistance, Float.class),
                Pointer.pointerToAddress(hitNormal, Float.class),
                Pointer.pointerToAddress(hitMaterialIndex, Integer.class), Pointer.pointerToAddress(userData));
    }
}
