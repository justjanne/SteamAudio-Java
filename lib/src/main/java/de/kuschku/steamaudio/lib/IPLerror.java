package de.kuschku.steamaudio.lib;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

import java.util.Collections;
import java.util.Iterator;

public enum IPLerror implements IntValuedEnum<IPLerror> {
    IPL_STATUS_SUCCESS(0), IPL_STATUS_FAILURE(1), IPL_STATUS_OUTOFMEMORY(2), IPL_STATUS_INITIALIZATION(3);

    public final long value;

    IPLerror(long value) {
        this.value = value;
    }

    public static IPLerror fromValue(int value) {
        return (IPLerror) FlagSet.fromValue(value, values());
    }

    public long value() {
        return this.value;
    }

    public Iterator<IPLerror> iterator() {
        return Collections.singleton(this).iterator();
    }
}
