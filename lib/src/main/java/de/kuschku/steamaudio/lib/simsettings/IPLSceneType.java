package de.kuschku.steamaudio.lib.simsettings;

import de.kuschku.steamaudio.lib.util.EnumWrapper;
import de.kuschku.steamaudio.lib.util.IntValuedEnum;

/**
 * The ray tracer to use for scene representation and simulation. Phonon lets you choose from multiple ray tracing
 * implementations, each with different trade-offs. You can also choose to use your own ray tracing implementation.
 */
public class IPLSceneType extends EnumWrapper<IPLSceneType.Enum> {
    public IPLSceneType() {
        super(Enum::of);
    }

    public IPLSceneType(IPLSceneType.Enum value) {
        super(value, IPLSceneType.Enum::of);
    }

    public IPLSceneType(int value) {
        super(value, Enum::of);
    }

    public enum Enum implements IntValuedEnum {
        /**
         * Phonon's built-in ray tracer. This is a highly optimized, but single-threaded CPU implementation.
         */
        IPL_SCENETYPE_PHONON(0),

        /**
         * The Intel Embree ray tracer. This is a multi-core CPU implementation, so is likely to be faster than the
         * Phonon
         * ray tracer. However, since it uses all available CPU cores, it may end up starving the audio processing
         * thread
         * when used for real-time simulation. This is a good choice for reducing bake times.
         */
        IPL_SCENETYPE_EMBREE(1),

        /**
         * The AMD Radeon Rays ray tracer. This is an OpenCL implementation, and can use either the CPU or the GPU. If
         * using
         * the GPU, it is likely to be significantly faster than the Phonon ray tracer. However, on heavy real-time
         * simulation workloads, it may impact the application's frame rate.
         */
        IPL_SCENETYPE_FIRERAYS(2),

        /**
         * Allows you to specify callbacks to your own ray tracer. Useful if your application already uses a
         * high-performance ray tracer. This option uses the least amount of memory at run-time, since it does not have
         * to
         * build any ray tracing data structures of its own.
         */
        IPL_SCENETYPE_CUSTOM(3),

        IPL_SCENETYPE_UNKNOWN(-1);

        private final int value;

        Enum(int value) {
            this.value = value;
        }

        public static Enum of(int value) {
            switch (value) {
                case 0:
                    return IPL_SCENETYPE_PHONON;
                case 1:
                    return IPL_SCENETYPE_EMBREE;
                case 2:
                    return IPL_SCENETYPE_FIRERAYS;
                case 3:
                    return IPL_SCENETYPE_CUSTOM;
                default:
                    return IPL_SCENETYPE_UNKNOWN;
            }
        }

        public int value() {
            return value;
        }

        public IPLSceneType wrap() {
            return new IPLSceneType(this);
        }
    }
}
