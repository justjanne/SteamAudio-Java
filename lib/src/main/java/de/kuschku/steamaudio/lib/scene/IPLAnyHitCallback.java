package de.kuschku.steamaudio.lib.scene;

import org.bridj.Callback;
import org.bridj.Pointer;
import org.bridj.ann.Ptr;

public abstract class IPLAnyHitCallback extends Callback<IPLAnyHitCallback> {
    /**
     * A callback that is called to calculate whether a ray hits any geometry. Strictly speaking, the function looks for
     * any intersection with a ray _interval_ (equivalent to a line segment).
     *
     * @param origin      Array containing the x, y, z coordinates (in that order) of the ray's origin.
     * @param direction   Array containing the x, y, z coordinates (in that order) of a unit-length vector along the
     *                    ray's direction.
     * @param minDistance The minimum distance from the origin at which an intersection may occur for it to be
     *                    considered.
     * @param maxDistance The maximum distance from the origin at which an intersection may occur for it to be
     *                    considered.
     * @param hitExists   [out] An integer indicating whether the ray intersects any geometry. A value of 0 indicates no
     *                    intersection, 1 indicates that an intersection exists.
     * @param userData    Pointer a block of memory containing arbitrary data, specified during the call to {@link
     *                    Scene#setRayTracerCallbacks}.
     */
    public void apply(Pointer<Float> origin, Pointer<Float> direction, float minDistance, float maxDistance,
                      Pointer<Integer> hitExists, Pointer<?> userData) {
        apply(Pointer.getPeer(origin), Pointer.getPeer(direction), minDistance, maxDistance, Pointer.getPeer(hitExists),
                Pointer.getPeer(userData));
    }

    /**
     * A callback that is called to calculate whether a ray hits any geometry. Strictly speaking, the function looks for
     * any intersection with a ray _interval_ (equivalent to a line segment).
     *
     * @param origin      Array containing the x, y, z coordinates (in that order) of the ray's origin.
     * @param direction   Array containing the x, y, z coordinates (in that order) of a unit-length vector along the
     *                    ray's direction.
     * @param minDistance The minimum distance from the origin at which an intersection may occur for it to be
     *                    considered.
     * @param maxDistance The maximum distance from the origin at which an intersection may occur for it to be
     *                    considered.
     * @param hitExists   [out] An integer indicating whether the ray intersects any geometry. A value of 0 indicates no
     *                    intersection, 1 indicates that an intersection exists.
     * @param userData    Pointer a block of memory containing arbitrary data, specified during the call to {@link
     *                    Scene#setRayTracerCallbacks}.
     */
    @SuppressWarnings("deprecation")
    public void apply(@Ptr long origin, @Ptr long direction, float minDistance, float maxDistance, @Ptr long hitExists,
                      @Ptr long userData) {
        apply(Pointer.pointerToAddress(origin, Float.class), Pointer.pointerToAddress(direction, Float.class),
                minDistance, maxDistance, Pointer.pointerToAddress(hitExists, Integer.class),
                Pointer.pointerToAddress(userData));
    }
}
