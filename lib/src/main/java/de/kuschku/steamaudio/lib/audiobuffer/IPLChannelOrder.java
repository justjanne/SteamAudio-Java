package de.kuschku.steamaudio.lib.audiobuffer;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

import java.util.Collections;
import java.util.Iterator;

public enum IPLChannelOrder implements IntValuedEnum<IPLChannelOrder> {
    IPL_CHANNELORDER_INTERLEAVED(0), IPL_CHANNELORDER_DEINTERLEAVED(1);

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
