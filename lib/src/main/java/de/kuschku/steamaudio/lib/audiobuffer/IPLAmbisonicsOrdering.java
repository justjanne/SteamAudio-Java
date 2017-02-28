package de.kuschku.steamaudio.lib.audiobuffer;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

import java.util.Collections;
import java.util.Iterator;

/**
 * The order in which Ambisonics channels are stored in an audio buffer. Each Ambisonics channel is a series of
 * coefficients for a corresponding basis function, denoted by {@code Y_l^m(\theta,\phi) }, where {@code \theta} and
 * {@code \phi} are two angles which pinpoint the source relative to the listener, and {@code l} and {@code m} are two
 * two integers which, taken together, identify a single Ambisonics channel. Here, {@code l \geq 0 } and
 * {@code -l \leq m \leq l }.
 * <p>
 * There are many different conventions used by the audio engineering community to encode Ambisonics coefficients.
 * Phonon supports many of them.
 * <p>
 * This enumeration defines the sequence in which Ambisonics channels are stored. Since two integers are needed to
 * identify an Ambisonics channel, there is more than one way to use a single integer to identify an Ambisonics
 * channel.
 */
public enum IPLAmbisonicsOrdering implements IntValuedEnum<IPLAmbisonicsOrdering> {
    /**
     * Specifies the Furse-Malham (FuMa) channel ordering. This is an extension of traditional B-format encoding to
     * higher-order Ambisonics.
     */
    IPL_AMBISONICSORDERING_FURSEMALHAM(0),

    /**
     * Specifies the Ambisonics Channel Number scheme for channel ordering. This is the new standard adopted by the
     * AmbiX Ambisonics format. The position of each Ambisonics channel is uniquely calculated as {@code ACN = l^2 + l +
     * m }.
     */
    IPL_AMBISONICSORDERING_ACN(1);

    public final long value;

    IPLAmbisonicsOrdering(long value) {
        this.value = value;
    }

    public static IntValuedEnum<IPLAmbisonicsOrdering> fromValue(int value) {
        return FlagSet.fromValue(value, values());
    }

    public long value() {
        return this.value;
    }

    public Iterator<IPLAmbisonicsOrdering> iterator() {
        return Collections.singleton(this).iterator();
    }
}
