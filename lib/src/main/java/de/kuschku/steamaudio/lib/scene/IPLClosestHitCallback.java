package de.kuschku.steamaudio.lib.scene;

import org.bridj.Callback;
import org.bridj.Pointer;
import org.bridj.ann.Ptr;

public abstract class IPLClosestHitCallback extends Callback<IPLClosestHitCallback> {
    /**
     * A callback that is called to calculate the closest hit along a ray. Strictly speaking, the intersection is
     * calculated with a ray _interval_ (equivalent to a line segment). Any ray interval may have multiple points of
     * intersection with scene geometry; this function must return information about the point of intersection thatis
     * closest to the ray's origin.
     *
     * @param origin           Array containing the x, y, z coordinates (in that order) of the ray's origin.
     * @param direction        Array containing the x, y, z coordinates (in that order) of a unit-length vector along
     *                         the ray's direction.
     * @param minDistance      The minimum distance from the origin at which an intersection may occur for it to be
     *                         considered. This function must not return any intersections closer to the origin than
     *                         this value.
     * @param maxDistance      The maximum distance from the origin at which an intersection may occur for it to be
     *                         considered. This function must not return any intersections farther from the origin than
     *                         this value.
     * @param hitDistance      [out] Distance between the origin and the closest intersection point on the ray.
     * @param hitNormal        [out] Array containing the x, y, z coordinates (in that order) of the unit-length surface
     *                         normal of the geometry at the closest intersection point.
     * @param hitMaterialIndex [out] Index of the material of the surface at the closest intersection point. The
     *                         returned value must lie between 0 and N-1, where N is the value of {@code numMaterials}
     *                         passed to {@link Scene#Scene}.
     * @param userData         Pointer a block of memory containing arbitrary data, specified during the call to {@link
     *                         Scene#setRayTracerCallbacks}.
     */
    public void apply(Pointer<Float> origin, Pointer<Float> direction, float minDistance, float maxDistance,
                      Pointer<Float> hitDistance, Pointer<Float> hitNormal, Pointer<Integer> hitMaterialIndex,
                      Pointer<?> userData) {
        apply(Pointer.getPeer(origin), Pointer.getPeer(direction), minDistance, maxDistance,
                Pointer.getPeer(hitDistance), Pointer.getPeer(hitNormal), Pointer.getPeer(hitMaterialIndex),
                Pointer.getPeer(userData));
    }

    /**
     * A callback that is called to calculate the closest hit along a ray. Strictly speaking, the intersection is
     * calculated with a ray _interval_ (equivalent to a line segment). Any ray interval may have multiple points of
     * intersection with scene geometry; this function must return information about the point of intersection thatis
     * closest to the ray's origin.
     *
     * @param origin           Array containing the x, y, z coordinates (in that order) of the ray's origin.
     * @param direction        Array containing the x, y, z coordinates (in that order) of a unit-length vector along
     *                         the ray's direction.
     * @param minDistance      The minimum distance from the origin at which an intersection may occur for it to be
     *                         considered. This function must not return any intersections closer to the origin than
     *                         this value.
     * @param maxDistance      The maximum distance from the origin at which an intersection may occur for it to be
     *                         considered. This function must not return any intersections farther from the origin than
     *                         this value.
     * @param hitDistance      [out] Distance between the origin and the closest intersection point on the ray.
     * @param hitNormal        [out] Array containing the x, y, z coordinates (in that order) of the unit-length surface
     *                         normal of the geometry at the closest intersection point.
     * @param hitMaterialIndex [out] Index of the material of the surface at the closest intersection point. The
     *                         returned value must lie between 0 and N-1, where N is the value of {@code numMaterials}
     *                         passed to {@link Scene#Scene}.
     * @param userData         Pointer a block of memory containing arbitrary data, specified during the call to {@link
     *                         Scene#setRayTracerCallbacks}.
     */
    @SuppressWarnings("deprecation")
    public void apply(@Ptr long origin, @Ptr long direction, float minDistance, float maxDistance,
                      @Ptr long hitDistance, @Ptr long hitNormal, @Ptr long hitMaterialIndex, @Ptr long userData) {
        apply(Pointer.pointerToAddress(origin, Float.class), Pointer.pointerToAddress(direction, Float.class),
                minDistance, maxDistance, Pointer.pointerToAddress(hitDistance, Float.class),
                Pointer.pointerToAddress(hitNormal, Float.class),
                Pointer.pointerToAddress(hitMaterialIndex, Integer.class), Pointer.pointerToAddress(userData));
    }
}
