package de.kuschku.steamaudio.lib.probes;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

import java.util.Collections;
import java.util.Iterator;

public enum IPLProbePlacement implements IntValuedEnum<IPLProbePlacement> {
    IPL_PLACEMENT_CENTROID(0), IPL_PLACEMENT_OCTREE(1), IPL_PLACEMENT_UNIFORMFLOOR(2);

    public final long value;

    IPLProbePlacement(long value) {
        this.value = value;
    }

    public static IntValuedEnum<IPLProbePlacement> fromValue(int value) {
        return FlagSet.fromValue(value, values());
    }

    public long value() {
        return this.value;
    }

    public Iterator<IPLProbePlacement> iterator() {
        return Collections.singleton(this).iterator();
    }
}
