package de.kuschku.steamaudio.lib.simulation;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

import java.util.Collections;
import java.util.Iterator;

public enum IPLSimulationType implements IntValuedEnum<IPLSimulationType> {
    IPL_SIMTYPE_REALTIME(0), IPL_SIMTYPE_BAKED(1);

    public final long value;

    IPLSimulationType(long value) {
        this.value = value;
    }

    public static IntValuedEnum<IPLSimulationType> fromValue(int value) {
        return FlagSet.fromValue(value, values());
    }

    public long value() {
        return this.value;
    }

    public Iterator<IPLSimulationType> iterator() {
        return Collections.singleton(this).iterator();
    }
}
