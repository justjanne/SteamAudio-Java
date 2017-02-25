package de.kuschku.steamaudio.lib.simsettings;

import de.kuschku.steamaudio.lib.util.EnumWrapper;
import de.kuschku.steamaudio.lib.util.IntValuedEnum;

/**
 * The type of simulation to perform. All sound sources must use the same type of simulation; it is not currently
 * possible to use real-time simulation for some sources and baked data for others.
 */
public class IPLSimulationType extends EnumWrapper<IPLSimulationType.Enum> {
    public IPLSimulationType() {
        super(Enum::of);
    }

    public IPLSimulationType(IPLSimulationType.Enum value) {
        super(value, IPLSimulationType.Enum::of);
    }

    public IPLSimulationType(int value) {
        super(value, Enum::of);
    }

    public enum Enum implements IntValuedEnum {
        /**
         * Real-time simulation. Sound propagation from all sound sources is constantly updated in a separate thread, as
         * the
         * player moves and interacts with the scene. This is a very performance-intensive approach, and requires the
         * user
         * to have a powerful PC for optimal results. This is also the type of simulation to choose when generating
         * baked
         * data.
         */
        IPL_SIMTYPE_REALTIME(0),

        /**
         * Simulation using baked data. If baked data has been generated for the scene and sound sources, simulation
         * will be
         * carried out by looking up information from the baked data. This approach has much lower CPU usage than
         * real-time
         * simulation, but at the cost of increased memory usage.
         */
        IPL_SIMTYPE_BAKED(1),

        IPL_SIMTYPE_UNKNOWN(-1);

        private final int value;

        Enum(int value) {
            this.value = value;
        }

        public static Enum of(int value) {
            switch (value) {
                case 0:
                    return IPL_SIMTYPE_REALTIME;
                case 1:
                    return IPL_SIMTYPE_BAKED;
                default:
                    return IPL_SIMTYPE_UNKNOWN;
            }
        }

        public int value() {
            return value;
        }

        public IPLSimulationType wrap() {
            return new IPLSimulationType(this);
        }
    }
}
