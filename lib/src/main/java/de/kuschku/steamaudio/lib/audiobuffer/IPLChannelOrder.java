package de.kuschku.steamaudio.lib.audiobuffer;

import de.kuschku.steamaudio.lib.util.EnumWrapper;
import de.kuschku.steamaudio.lib.util.IntValuedEnum;

/**
 * Whether the data is interleaved or deinterleaved.
 */
public class IPLChannelOrder extends EnumWrapper<IPLChannelOrder.Enum> {
    public IPLChannelOrder() {
        super(Enum::of);
    }

    public IPLChannelOrder(IPLChannelOrder.Enum value) {
        super(value, IPLChannelOrder.Enum::of);
    }

    public IPLChannelOrder(int value) {
        super(value, Enum::of);
    }

    public enum Enum implements IntValuedEnum {
        /**
         * Sample values for each channel are stored one after another, followed by the next set of sample values for
         * each
         * channel, etc. In the case of 2-channel stereo, this would correspond to <code>LRLRLRLR...</code>
         */
        IPL_CHANNELORDER_INTERLEAVED(0),

        /**
         * All sample values for the first channel are stored one after another, followed by the sample values for the
         * next
         * channel, etc. In the case of 2-channel stereo, this would correspond to <code>LLLL...RRRR...</code>
         */
        IPL_CHANNELORDER_DEINTERLEAVED(1),

        IPL_CHANNELORDER_UNKNOWN(-1);

        private final int value;

        Enum(int value) {
            this.value = value;
        }

        public static Enum of(int value) {
            switch (value) {
                case 0:
                    return IPL_CHANNELORDER_INTERLEAVED;
                case 1:
                    return IPL_CHANNELORDER_DEINTERLEAVED;
                default:
                    return IPL_CHANNELORDER_UNKNOWN;
            }
        }

        public int value() {
            return value;
        }

        public IPLChannelOrder wrap() {
            return new IPLChannelOrder(this);
        }
    }
}
