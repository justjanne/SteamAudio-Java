package de.kuschku.steamaudio.lib.scene;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;

public interface IPLAnyHitCallback extends Callback {
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
     * @param hitExists   [out] An integer indicating whether the ray intersects any geometry. A value of 0 indicates
     *                    no intersection, 1 indicates that an intersection exists.
     * @param userData    Pointer a block of memory containing arbitrary data, specified during the call to {@link
     *                    SceneApi#iplSetRayTracerCallbacks}.
     */
    void apply(FloatByReference origin, FloatByReference direction, float minDistance, float maxDistance,
               IntByReference hitExists, Pointer userData);
}
