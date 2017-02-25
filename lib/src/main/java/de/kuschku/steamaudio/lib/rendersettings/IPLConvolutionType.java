package de.kuschku.steamaudio.lib.rendersettings;

import de.kuschku.steamaudio.lib.conveffect.ConvEffectApi;
import de.kuschku.steamaudio.lib.util.EnumWrapper;
import de.kuschku.steamaudio.lib.util.IntValuedEnum;

/**
 * The backend to use for applying convolution effects for sound propagation. Phonon lets you choose from multiple
 * convolution implementations, with different trade-offs.
 */
public class IPLConvolutionType extends EnumWrapper<IPLConvolutionType.Enum> {
    public IPLConvolutionType() {
        super(Enum::of);
    }

    public IPLConvolutionType(IPLConvolutionType.Enum value) {
        super(value, IPLConvolutionType.Enum::of);
    }

    public IPLConvolutionType(int value) {
        super(value, Enum::of);
    }

    public enum Enum implements IntValuedEnum {
        /**
         * Phonon's built-in convolution algorithm. This is a highly optimized, but single-threaded CPU-based
         * implementation. With this implementation, there is a significant performance advantage to using {@link
         * ConvEffectApi#iplGetMixedEnvironmentalAudio} compared to using {@link ConvEffectApi#iplGetWetAudioForConvolutionEffect}.
         */
        IPL_CONVOLUTIONTYPE_PHONON(0),

        /**
         * The AMD TrueAudio Next convolution algorithm. This is GPU-based implementation, that requires an AMD GPU
         * that
         * supports AMD TrueAudio Next. With this implementation, there is no major performance advantage to using
         * {@link
         * ConvEffectApi#iplGetMixedEnvironmentalAudio} as compared to using {@link ConvEffectApi#iplGetWetAudioForConvolutionEffect}.
         */
        IPL_CONVOLUTIONTYPE_TRUEAUDIONEXT(1),

        IPL_CONVOLUTIONTYPE_UNKNOWN(-1);

        private final int value;

        Enum(int value) {
            this.value = value;
        }

        public static Enum of(int value) {
            switch (value) {
                case 0:
                    return IPL_CONVOLUTIONTYPE_PHONON;
                case 1:
                    return IPL_CONVOLUTIONTYPE_TRUEAUDIONEXT;
                default:
                    return IPL_CONVOLUTIONTYPE_UNKNOWN;
            }
        }

        public int value() {
            return value;
        }

        public IPLConvolutionType wrap() {
            return new IPLConvolutionType(this);
        }
    }
}
