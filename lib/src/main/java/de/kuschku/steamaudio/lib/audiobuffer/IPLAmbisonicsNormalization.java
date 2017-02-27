package de.kuschku.steamaudio.lib.audiobuffer;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

import java.util.Collections;
import java.util.Iterator;

public enum IPLAmbisonicsNormalization implements IntValuedEnum<IPLAmbisonicsNormalization> {
    IPL_AMBISONICSNORMALIZATION_FURSEMALHAM(0), IPL_AMBISONICSNORMALIZATION_SN3D(1), IPL_AMBISONICSNORMALIZATION_N3D(2);

    public final long value;

    IPLAmbisonicsNormalization(long value) {
        this.value = value;
    }

    public static IntValuedEnum<IPLAmbisonicsNormalization> fromValue(int value) {
        return FlagSet.fromValue(value, values());
    }

    public long value() {
        return this.value;
    }

    public Iterator<IPLAmbisonicsNormalization> iterator() {
        return Collections.singleton(this).iterator();
    }
}
