package de.kuschku.steamaudio.lib.binauraleffect;

import de.kuschku.steamaudio.lib.util.EnumWrapper;
import de.kuschku.steamaudio.lib.util.IntValuedEnum;

/**
 * Techniques for interpolating HRTF data. This is used when rendering a point source whose position relative to the
 * listener is not contained in the measured HRTF data used by Phonon.
 */
public class IPLHrtfInterpolation extends EnumWrapper<IPLHrtfInterpolation.Enum> {
    public IPLHrtfInterpolation() {
        super(Enum::of);
    }

    public IPLHrtfInterpolation(IPLHrtfInterpolation.Enum value) {
        super(value, IPLHrtfInterpolation.Enum::of);
    }

    public IPLHrtfInterpolation(int value) {
        super(value, Enum::of);
    }

    public enum Enum implements IntValuedEnum {
        /**
         * Nearest-neighbor filtering, i.e., no interpolation. Selects the measurement location that is closest to the
         * source's actual location.
         */
        IPL_HRTFINTERPOLATION_NEAREST(0),

        /**
         * Bilinear filtering. Incurs a relatively high CPU overhead as compared to nearest-neighbor filtering, so use
         * this
         * for sounds where it has a significant benefit.
         */
        IPL_HRTFINTERPOLATION_BILINEAR(1),

        IPL_HRTFINTERPOLATION_UNKNOWN(-1);

        private final int value;

        Enum(int value) {
            this.value = value;
        }

        public static Enum of(int value) {
            switch (value) {
                case 0:
                    return IPL_HRTFINTERPOLATION_NEAREST;
                case 1:
                    return IPL_HRTFINTERPOLATION_BILINEAR;
                default:
                    return IPL_HRTFINTERPOLATION_UNKNOWN;
            }
        }

        public int value() {
            return value;
        }

        public IPLHrtfInterpolation wrap() {
            return new IPLHrtfInterpolation(this);
        }
    }
}
