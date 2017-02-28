package de.kuschku.steamaudio.lib.audiobuffer;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

import java.util.Collections;
import java.util.Iterator;

/**
 * Whether the audio buffer is encoded using Ambisonics or not.
 */
public enum IPLChannelLayoutType implements IntValuedEnum<IPLChannelLayoutType> {
    /**
     * Indicates that each channel of audio data is intended to be played back by a single speaker. This corresponds to
     * most multi-speaker mono, stereo, or surround sound configurations.
     */
    IPL_CHANNELLAYOUTTYPE_SPEAKERS(0),

    /**
     * Indicates that each channel of audio data is to be interpreted as a series of Ambisonics coefficients. Playing
     * back such an audio buffer requires a software or hardware Ambisonics decoder. Phonon contains a software
     * Ambisonics decoder.
     */
    IPL_CHANNELLAYOUTTYPE_AMBISONICS(1);

    public final long value;

    IPLChannelLayoutType(long value) {
        this.value = value;
    }

    public static IntValuedEnum<IPLChannelLayoutType> fromValue(int value) {
        return FlagSet.fromValue(value, values());
    }

    public long value() {
        return this.value;
    }

    public Iterator<IPLChannelLayoutType> iterator() {
        return Collections.singleton(this).iterator();
    }
}
