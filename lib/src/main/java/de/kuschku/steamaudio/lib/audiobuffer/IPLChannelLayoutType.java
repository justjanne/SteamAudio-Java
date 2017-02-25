package de.kuschku.steamaudio.lib.audiobuffer;

import de.kuschku.steamaudio.lib.util.EnumWrapper;
import de.kuschku.steamaudio.lib.util.IntValuedEnum;

/**
 * Whether the audio buffer is encoded using Ambisonics or not.
 */
public class IPLChannelLayoutType extends EnumWrapper<IPLChannelLayoutType.Enum> {
    public IPLChannelLayoutType() {
        super(Enum::of);
    }

    public IPLChannelLayoutType(IPLChannelLayoutType.Enum value) {
        super(value, IPLChannelLayoutType.Enum::of);
    }

    public IPLChannelLayoutType(int value) {
        super(value, Enum::of);
    }

    public enum Enum implements IntValuedEnum {
        /**
         * Indicates that each channel of audio data is intended to be played back by a single speaker. This corresponds
         * to
         * most multi-speaker mono, stereo, or surround sound configurations.
         */
        IPL_CHANNELLAYOUTTYPE_SPEAKERS(0),

        /**
         * Indicates that each channel of audio data is to be interpreted as a series of Ambisonics coefficients.
         * Playing
         * back such an audio buffer requires a software or hardware Ambisonics decoder. Phonon contains a software
         * Ambisonics decoder.
         */
        IPL_CHANNELLAYOUTTYPE_AMBISONICS(1),

        IPL_CHANNELLAYOUTTYPE_UNKNOWN(-1);

        private final int value;

        Enum(int value) {
            this.value = value;
        }

        public static Enum of(int value) {
            switch (value) {
                case 0:
                    return IPL_CHANNELLAYOUTTYPE_SPEAKERS;
                case 1:
                    return IPL_CHANNELLAYOUTTYPE_AMBISONICS;
                default:
                    return IPL_CHANNELLAYOUTTYPE_UNKNOWN;
            }
        }

        public int value() {
            return value;
        }

        public IPLChannelLayoutType wrap() {
            return new IPLChannelLayoutType(this);
        }
    }
}
