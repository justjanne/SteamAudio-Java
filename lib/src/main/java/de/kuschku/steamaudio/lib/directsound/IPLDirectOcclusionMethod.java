package de.kuschku.steamaudio.lib.directsound;

import de.kuschku.steamaudio.lib.util.EnumWrapper;
import de.kuschku.steamaudio.lib.util.IntValuedEnum;

/**
 * The algorithm to use when checking for direct path occlusion. Phonon can check whether a direct sound path is
 * occluded by scene geometry, and calculate a simple attenuation factor accordingly.
 */
public class IPLDirectOcclusionMethod extends EnumWrapper<IPLDirectOcclusionMethod.Enum> {
    public IPLDirectOcclusionMethod() {
        super(Enum::of);
    }

    public IPLDirectOcclusionMethod(IPLDirectOcclusionMethod.Enum value) {
        super(value, IPLDirectOcclusionMethod.Enum::of);
    }

    public IPLDirectOcclusionMethod(int value) {
        super(value, Enum::of);
    }

    public enum Enum implements IntValuedEnum {
        /**
         * Does not perform any occlusion checks. Sound will be audible through solid objects.
         */
        IPL_DIRECTOCCLUSION_NONE(0),

        /**
         * Performs a rudimentary occlusion test by checking if the ray from the listener to the source is occluded by
         * any
         * source geometry. If so, the sound will be considered to be completely inaudible. The Environment object
         * created
         * by the game engine must have a valid Scene object for this to work. <p> <b>Not supported if using Radeon Rays
         * as
         * your raytracer.</b>
         */
        IPL_DIRECTOCCLUSION_RAYCAST(1),

        /**
         * Performs a slightly more complicated occlusion test: the source is treated as a sphere, and rays are traced
         * from
         * the listener to various points in the interior of the sphere. The proportion of rays that are occluded by
         * scene
         * geometry determines the attenuation of the sound source. The Environment object created by the game engine
         * must
         * have a valid Scene object for this to work.
         */
        IPL_DIRECTOCCLUSION_VOLUMETRIC(2),

        IPL_DIRECTOCCLUSION_METHOD_UNKNOWN(-1);

        private final int value;

        Enum(int value) {
            this.value = value;
        }

        public static Enum of(int value) {
            switch (value) {
                case 0:
                    return IPL_DIRECTOCCLUSION_NONE;
                case 1:
                    return IPL_DIRECTOCCLUSION_RAYCAST;
                case 2:
                    return IPL_DIRECTOCCLUSION_VOLUMETRIC;
                default:
                    return IPL_DIRECTOCCLUSION_METHOD_UNKNOWN;
            }
        }

        public int value() {
            return value;
        }

        public IPLDirectOcclusionMethod wrap() {
            return new IPLDirectOcclusionMethod(this);
        }
    }
}
