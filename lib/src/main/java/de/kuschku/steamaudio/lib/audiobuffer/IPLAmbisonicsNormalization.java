package de.kuschku.steamaudio.lib.audiobuffer;

import de.kuschku.steamaudio.lib.util.EnumWrapper;
import de.kuschku.steamaudio.lib.util.IntValuedEnum;

/**
 * Normalization conventions for Ambisonics channels. There are a few different ways of normalizing the values of the
 * Ambisonics channels relative to each other. Phonon supports the most popular ones.
 */
public class IPLAmbisonicsNormalization extends EnumWrapper<IPLAmbisonicsNormalization.Enum> {
    public IPLAmbisonicsNormalization() {
        super(Enum::of);
    }

    public IPLAmbisonicsNormalization(IPLAmbisonicsNormalization.Enum value) {
        super(value, Enum::of);
    }

    public IPLAmbisonicsNormalization(int value) {
        super(value, Enum::of);
    }

    public enum Enum implements IntValuedEnum {
        /**
         * This is the normalization scheme used in Furse-Malham higher-order Ambisonics. Each channel is normalized to
         * not
         * exceed 1.0, and a -3 dB gain correction is applied to channel 0.
         */
        IPL_AMBISONICSNORMALIZATION_FURSEMALHAM(0),

        /**
         * Also called Schmidt semi-normalized form. This is the normalization scheme used in the AmbiX format.
         */
        IPL_AMBISONICSNORMALIZATION_SN3D(1),

        /**
         * This normalization scheme is based on the mathematical definition of Ambisonics. It is closely related to
         * {@link
         * #IPL_AMBISONICSNORMALIZATION_SN3D} by a series of scaling factors. This normalization scheme is used
         * internally
         * throughout Phonon, and using it results in the fastest performance.
         */
        IPL_AMBISONICSNORMALIZATION_N3D(2),

        IPL_AMBISONICSNORMALIZATION_UNKNOWN(-1);

        public final int value;

        Enum(int value) {
            this.value = value;
        }

        static Enum of(int value) {
            switch (value) {
                case 0:
                    return IPL_AMBISONICSNORMALIZATION_FURSEMALHAM;
                case 1:
                    return IPL_AMBISONICSNORMALIZATION_SN3D;
                case 2:
                    return IPL_AMBISONICSNORMALIZATION_N3D;
                default:
                    return IPL_AMBISONICSNORMALIZATION_UNKNOWN;
            }
        }

        public int value() {
            return value;
        }

        public IPLAmbisonicsNormalization wrap() {
            return new IPLAmbisonicsNormalization(this);
        }
    }
}
