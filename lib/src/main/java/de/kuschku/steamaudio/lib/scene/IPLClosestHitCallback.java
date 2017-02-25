package de.kuschku.steamaudio.lib.scene;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;

public interface IPLClosestHitCallback extends Callback {
    /**
     * A callback that is called to calculate the closest hit along a ray. Strictly speaking, the intersection is
     * calculated with a ray _interval_ (equivalent to a line segment). Any ray interval may have multiple points of
     * intersection with scene geometry; this function must return information about the point of intersection that is
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
     * @param hitNormal        [out] Array containing the x, y, z coordinates (in that order) of the unit-length
     *                         surface normal of the geometry at the closest intersection point.
     * @param hitMaterialIndex [out] Index of the material of the surface at the closest intersection point. The
     *                         returned value must lie between 0 and N-1, where N is the value of {@code numMaterials}
     *                         passed to {@link SceneApi#iplCreateScene}.
     * @param userData         Pointer a block of memory containing arbitrary data, specified during the call to {@link
     *                         SceneApi#iplSetRayTracerCallbacks}.
     */
    void apply(FloatByReference origin, FloatByReference direction, float minDistance, float maxDistance,
               FloatByReference hitDistance, FloatByReference hitNormal, IntByReference hitMaterialIndex,
               Pointer userData);
}
