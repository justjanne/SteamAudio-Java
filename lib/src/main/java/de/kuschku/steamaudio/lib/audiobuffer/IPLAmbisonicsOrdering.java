package de.kuschku.steamaudio.lib.audiobuffer;

import de.kuschku.steamaudio.lib.util.EnumWrapper;
import de.kuschku.steamaudio.lib.util.IntValuedEnum;

/**
 * The order in which Ambisonics channels are stored in an audio buffer. Each Ambisonics channel is a series of
 * coefficients for a corresponding basis function, denoted by <code> Y_l^m(\theta,\phi) </code>, where
 * <code>\theta</code> and <code>\phi</code> are two angles which pinpoint the source relative to the listener, and
 * <code>l</code> and <code>m</code> are two two integers which, taken together, identify a single Ambisonics channel.
 * Here, <code> l \geq 0 </code> and <code>$ -l \leq m \leq l </code>. <p> There are many different conventions used by
 * the audio engineering community to encode Ambisonics coefficients. Phonon supports many of them. <p> This enumeration
 * defines the sequence in which Ambisonics channels are stored. Since two integers are needed to identify an Ambisonics
 * channel, there is more than one way to use a single integer to identify an Ambisonics channel.
 */
public class IPLAmbisonicsOrdering extends EnumWrapper<IPLAmbisonicsOrdering.Enum> {
    public IPLAmbisonicsOrdering() {
        super(Enum::of);
    }

    public IPLAmbisonicsOrdering(IPLAmbisonicsOrdering.Enum value) {
        super(value, IPLAmbisonicsOrdering.Enum::of);
    }

    public IPLAmbisonicsOrdering(int value) {
        super(value, Enum::of);
    }

    public enum Enum implements IntValuedEnum {
        /**
         * Specifies the Furse-Malham (FuMa) channel ordering. This is an extension of traditional B-format encoding to
         * higher-order Ambisonics.
         */
        IPL_AMBISONICSORDERING_FURSEMALHAM(0),

        /**
         * Specifies the Ambisonics Channel Number scheme for channel ordering. This is the new standard adopted by the
         * AmbiX Ambisonics format. The position of each Ambisonics channel is uniquely calculated as <code> ACN = l^2 +
         * l +
         * m </code>.
         */
        IPL_AMBISONICSORDERING_ACN(1),

        IPL_AMBISONICSORDERING_UNKNOWN(-1);

        public final int value;

        Enum(int value) {
            this.value = value;
        }

        public static Enum of(int value) {
            switch (value) {
                case 0:
                    return IPL_AMBISONICSORDERING_FURSEMALHAM;
                case 1:
                    return IPL_AMBISONICSORDERING_ACN;
                default:
                    return IPL_AMBISONICSORDERING_UNKNOWN;
            }
        }

        public int value() {
            return value;
        }

        public IPLAmbisonicsOrdering wrap() {
            return new IPLAmbisonicsOrdering(this);
        }
    }
}
