package de.kuschku.steamaudio.lib.audiobuffer;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

import java.util.Collections;
import java.util.Iterator;

/**
 * Normalization conventions for Ambisonics channels. There are a few different ways of normalizing the values of
 * the Ambisonics channels relative to each other. Phonon supports the most popular ones.
 */
public enum IPLAmbisonicsNormalization implements IntValuedEnum<IPLAmbisonicsNormalization> {
    /**
     * This is the normalization scheme used in Furse-Malham higher-order Ambisonics. Each channel is normalized to not
     * exceed 1.0, and a -3 dB gain correction is applied to channel 0.
     */
    IPL_AMBISONICSNORMALIZATION_FURSEMALHAM(0),

    /**
     * Also called Schmidt semi-normalized form. This is the normalization scheme used in the AmbiX format.
     */
    IPL_AMBISONICSNORMALIZATION_SN3D(1),

    /**
     * This normalization scheme is based on the mathematical definition of Ambisonics. It is closely related to {@link
     * #IPL_AMBISONICSNORMALIZATION_SN3D} by a series of scaling factors. This normalization scheme is used internally
     * throughout Phonon, and using it results in the fastest performance.
     */
    IPL_AMBISONICSNORMALIZATION_N3D(2);

    public final long value;

    IPLAmbisonicsNormalization(long value) {
        this.value = value;
    }

    public static IntValuedEnum<IPLAmbisonicsNormalization> fromValue(int value) {
        return FlagSet.fromValue(value, values());
    }

    public long value() {
        return this.value;
    }

    public Iterator<IPLAmbisonicsNormalization> iterator() {
        return Collections.singleton(this).iterator();
    }
}
