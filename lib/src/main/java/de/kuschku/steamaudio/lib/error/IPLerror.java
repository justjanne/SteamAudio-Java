package de.kuschku.steamaudio.lib.error;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

import java.util.Collections;
import java.util.Iterator;

/**
 * Status codes returned by Phonon API functions.
 */
public enum IPLerror implements IntValuedEnum<IPLerror> {
    /**
     * The operation completed successfully.
     */
    IPL_STATUS_SUCCESS(0),

    /**
     * An unspecified error occurred.
     */
    IPL_STATUS_FAILURE(1),

    /**
     * The system ran out of memory.
     */
    IPL_STATUS_OUTOFMEMORY(2),

    /**
     * An error occurred while initializing an external dependency.
     */
    IPL_STATUS_INITIALIZATION(3);

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
