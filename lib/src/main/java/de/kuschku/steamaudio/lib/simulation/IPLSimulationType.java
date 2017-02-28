package de.kuschku.steamaudio.lib.simulation;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

import java.util.Collections;
import java.util.Iterator;

/**
 * The type of simulation to perform. All sound sources must use the same type of simulation; it is not currently
 * possible to use real-time simulation for some sources and baked data for others.
 */
public enum IPLSimulationType implements IntValuedEnum<IPLSimulationType> {
    /**
     * Real-time simulation. Sound propagation from all sound sources is constantly updated in a separate thread, as the
     * player moves and interacts with the scene. This is a very performance-intensive approach, and requires the user
     * to have a powerful PC for optimal results. This is also the type of simulation to choose when generating baked
     * data.
     */
    IPL_SIMTYPE_REALTIME(0),

    /**
     * Simulation using baked data. If baked data has been generated for the scene and sound sources, simulation will be
     * carried out by looking up information from the baked data. This approach has much lower CPU usage than real-time
     * simulation, but at the cost of increased memory usage.
     */
    IPL_SIMTYPE_BAKED(1);

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
