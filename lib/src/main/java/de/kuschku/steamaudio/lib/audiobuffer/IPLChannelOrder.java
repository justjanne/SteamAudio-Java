package de.kuschku.steamaudio.lib.audiobuffer;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

import java.util.Collections;
import java.util.Iterator;

/**
 * Whether the data is interleaved or deinterleaved.
 */
public enum IPLChannelOrder implements IntValuedEnum<IPLChannelOrder> {
    /**
     * Sample values for each channel are stored one after another, followed by the next set of sample values for each
     * channel, etc. In the case of 2-channel stereo, this would correspond to {@code LRLRLRLR...}
     */
    IPL_CHANNELORDER_INTERLEAVED(0),

    /**
     * All sample values for the first channel are stored one after another, followed by the sample values for the next
     * channel, etc. In the case of 2-channel stereo, this would correspond to {@code LLLL...RRRR...}
     */
    IPL_CHANNELORDER_DEINTERLEAVED(1);

    public final long value;

    IPLChannelOrder(long value) {
        this.value = value;
    }

    public static IntValuedEnum<IPLChannelOrder> fromValue(int value) {
        return FlagSet.fromValue(value, values());
    }

    public long value() {
        return this.value;
    }

    public Iterator<IPLChannelOrder> iterator() {
        return Collections.singleton(this).iterator();
    }
}
