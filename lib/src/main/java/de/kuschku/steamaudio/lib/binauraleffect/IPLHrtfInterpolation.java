package de.kuschku.steamaudio.lib.binauraleffect;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

import java.util.Collections;
import java.util.Iterator;

public enum IPLHrtfInterpolation implements IntValuedEnum<IPLHrtfInterpolation> {
    IPL_HRTFINTERPOLATION_NEAREST(0), IPL_HRTFINTERPOLATION_BILINEAR(1);

    public final long value;

    IPLHrtfInterpolation(long value) {
        this.value = value;
    }

    public static IntValuedEnum<IPLHrtfInterpolation> fromValue(int value) {
        return FlagSet.fromValue(value, values());
    }

    public long value() {
        return this.value;
    }

    public Iterator<IPLHrtfInterpolation> iterator() {
        return Collections.singleton(this).iterator();
    }
}
