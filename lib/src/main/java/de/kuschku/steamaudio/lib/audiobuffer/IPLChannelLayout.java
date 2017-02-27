package de.kuschku.steamaudio.lib.audiobuffer;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

import java.util.Collections;
import java.util.Iterator;

public enum IPLChannelLayout implements IntValuedEnum<IPLChannelLayout> {
    IPL_CHANNELLAYOUT_MONO(0), IPL_CHANNELLAYOUT_STEREO(1), IPL_CHANNELLAYOUT_QUADRAPHONIC(2),
    IPL_CHANNELLAYOUT_FIVEPOINTONE(3), IPL_CHANNELLAYOUT_SEVENPOINTONE(4), IPL_CHANNELLAYOUT_CUSTOM(5);

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
