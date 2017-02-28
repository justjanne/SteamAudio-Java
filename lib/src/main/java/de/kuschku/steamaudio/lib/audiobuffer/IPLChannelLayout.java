package de.kuschku.steamaudio.lib.audiobuffer;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

import java.util.Collections;
import java.util.Iterator;

/**
 * The type of speaker configuration, for audio formats that are not encoded using Ambisonics.
 */
public enum IPLChannelLayout implements IntValuedEnum<IPLChannelLayout> {
    /**
     * A single speaker, typically in front of the user.
     */
    IPL_CHANNELLAYOUT_MONO(0),

    /**
     * A pair of speakers, one to the left of the user, and one to the right. This is also the setting to use when
     * playing audio over headphones.
     */
    IPL_CHANNELLAYOUT_STEREO(1),

    /**
     * Four speakers: front left, front right, back left, and back right.
     */
    IPL_CHANNELLAYOUT_QUADRAPHONIC(2),

    /**
     * Six speakers: front left, front center, front right, back left, back right, and subwoofer.
     */
    IPL_CHANNELLAYOUT_FIVEPOINTONE(3),

    /**
     * Eight speakers: front left, front center, front right, side left, side right, back left, back right, and
     * subwoofer.
     */
    IPL_CHANNELLAYOUT_SEVENPOINTONE(4),

    /**
     * Lets you specify your own speaker configuration. You can specify any number of speakers, and set their positions
     * relative to the user. This is useful if you have a large speaker array, or if you want Phonon to account for the
     * heights at which the speakers have been installed.
     */
    IPL_CHANNELLAYOUT_CUSTOM(5);

    public final long value;

    IPLChannelLayout(long value) {
        this.value = value;
    }

    public static IntValuedEnum<IPLChannelLayout> fromValue(int value) {
        return FlagSet.fromValue(value, values());
    }

    public long value() {
        return this.value;
    }

    public Iterator<IPLChannelLayout> iterator() {
        return Collections.singleton(this).iterator();
    }
}
