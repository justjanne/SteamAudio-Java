package de.kuschku.steamaudio.lib.directsound;

import de.kuschku.steamaudio.lib.environment.Environment;
import de.kuschku.steamaudio.lib.scene.Scene;
import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

import java.util.Collections;
import java.util.Iterator;

/**
 * The algorithm to use when checking for direct path occlusion. Phonon can check whether a direct sound path is
 * occluded by scene geometry, and calculate a simple attenuation factor accordingly.
 */
public enum IPLDirectOcclusionMethod implements IntValuedEnum<IPLDirectOcclusionMethod> {
    /**
     * Does not perform any occlusion checks. Sound will be audible through solid objects.
     */
    IPL_DIRECTOCCLUSION_NONE(0),

    /**
     * Performs a rudimentary occlusion test by checking if the ray from the listener to the source is occluded by any
     * source geometry. If so, the sound will be considered to be completely inaudible. The {@link Environment} object
     * created by the game engine must have a valid {@link Scene} object for this to work. <b>Not supported if using
     * Radeon Rays as your ray tracer.</b>
     */
    IPL_DIRECTOCCLUSION_RAYCAST(1),

    /**
     * Performs a slightly more complicated occlusion test: the source is treated as a sphere, and rays are traced from
     * the listener to various points in the interior of the sphere. The proportion of rays that are occluded by scene
     * geometry determines the attenuation of the sound source. The {@link Environment} object created by the game
     * engine must have a valid {@link Scene} object for this to work.
     */
    IPL_DIRECTOCCLUSION_VOLUMETRIC(2);

    public final long value;

    IPLDirectOcclusionMethod(long value) {
        this.value = value;
    }

    public static IntValuedEnum<IPLDirectOcclusionMethod> fromValue(int value) {
        return FlagSet.fromValue(value, values());
    }

    public long value() {
        return this.value;
    }

    public Iterator<IPLDirectOcclusionMethod> iterator() {
        return Collections.singleton(this).iterator();
    }
}
