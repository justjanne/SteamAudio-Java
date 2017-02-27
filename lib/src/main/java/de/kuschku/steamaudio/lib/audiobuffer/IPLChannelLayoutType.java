package de.kuschku.steamaudio.lib.audiobuffer;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

import java.util.Collections;
import java.util.Iterator;

public enum IPLChannelLayoutType implements IntValuedEnum<IPLChannelLayoutType> {
    IPL_CHANNELLAYOUTTYPE_SPEAKERS(0), IPL_CHANNELLAYOUTTYPE_AMBISONICS(1);

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
