package de.kuschku.steamaudio.lib.rendersettings;

import de.kuschku.steamaudio.lib.conveffect.ConvolutionEffect;
import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

import java.util.Collections;
import java.util.Iterator;

/**
 * The backend to use for applying convolution effects for sound propagation. Phonon lets you choose from multiple
 * convolution implementations, with different trade-offs.
 */
public enum IPLConvolutionType implements IntValuedEnum<IPLConvolutionType> {
    /**
     * Phonon's built-in convolution algorithm. This is a highly optimized, but single-threaded CPU-based
     * implementation. With this implementation, there is a significant performance advantage to using {@link
     * ConvolutionEffect#getMixedEnvironmentalAudio} compared to using {@link ConvolutionEffect#getWetAudio}.
     */
    IPL_CONVOLUTIONTYPE_PHONON(0),

    /**
     * The AMD TrueAudio Next convolution algorithm. This is GPU-based implementation, that requires an AMD GPU that
     * supports AMD TrueAudio Next. With this implementation, there is no major performance advantage to using {@link
     * ConvolutionEffect#getMixedEnvironmentalAudio} as compared to using {@link ConvolutionEffect#getWetAudio}.
     */
    IPL_CONVOLUTIONTYPE_TRUEAUDIONEXT(1);

    public final long value;

    IPLConvolutionType(long value) {
        this.value = value;
    }

    public static IntValuedEnum<IPLConvolutionType> fromValue(int value) {
        return FlagSet.fromValue(value, values());
    }

    public long value() {
        return this.value;
    }

    public Iterator<IPLConvolutionType> iterator() {
        return Collections.singleton(this).iterator();
    }
}
