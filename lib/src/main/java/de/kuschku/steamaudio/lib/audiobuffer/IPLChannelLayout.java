package de.kuschku.steamaudio.lib.audiobuffer;

import de.kuschku.steamaudio.lib.util.EnumWrapper;
import de.kuschku.steamaudio.lib.util.IntValuedEnum;

/**
 * The type of speaker configuration, for audio formats that are not encoded using Ambisonics.
 */
public class IPLChannelLayout extends EnumWrapper<IPLChannelLayout.Enum> {
    public IPLChannelLayout() {
        super(Enum::of);
    }

    public IPLChannelLayout(IPLChannelLayout.Enum value) {
        super(value, IPLChannelLayout.Enum::of);
    }

    public IPLChannelLayout(int value) {
        super(value, Enum::of);
    }

    public enum Enum implements IntValuedEnum {
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
         * Lets you specify your own speaker configuration. You can specify any number of speakers, and set their
         * positions
         * relative to the user. This is useful if you have a large speaker array, or if you want Phonon to account for
         * the
         * heights at which the speakers have been installed.
         */
        IPL_CHANNELLAYOUT_CUSTOM(5),

        IPL_CHANNELLAYOUT_UNKNOWN(-1);

        public final int value;

        Enum(int value) {
            this.value = value;
        }

        public static Enum of(int value) {
            switch (value) {
                case 0:
                    return IPL_CHANNELLAYOUT_MONO;
                case 1:
                    return IPL_CHANNELLAYOUT_STEREO;
                case 2:
                    return IPL_CHANNELLAYOUT_QUADRAPHONIC;
                case 3:
                    return IPL_CHANNELLAYOUT_FIVEPOINTONE;
                case 4:
                    return IPL_CHANNELLAYOUT_SEVENPOINTONE;
                case 5:
                    return IPL_CHANNELLAYOUT_CUSTOM;
                default:
                    return IPL_CHANNELLAYOUT_UNKNOWN;
            }
        }

        public int value() {
            return value;
        }

        public IPLChannelLayout wrap() {
            return new IPLChannelLayout(this);
        }
    }
}
