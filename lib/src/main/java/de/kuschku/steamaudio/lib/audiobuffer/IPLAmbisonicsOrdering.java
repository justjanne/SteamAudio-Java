package de.kuschku.steamaudio.lib.audiobuffer;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

import java.util.Collections;
import java.util.Iterator;

public enum IPLAmbisonicsOrdering implements IntValuedEnum<IPLAmbisonicsOrdering> {
    IPL_AMBISONICSORDERING_FURSEMALHAM(0), IPL_AMBISONICSORDERING_ACN(1);

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
