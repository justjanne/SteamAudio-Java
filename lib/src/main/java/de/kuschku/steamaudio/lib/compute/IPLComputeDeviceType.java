package de.kuschku.steamaudio.lib.compute;

import de.kuschku.steamaudio.lib.util.EnumWrapper;
import de.kuschku.steamaudio.lib.util.IntValuedEnum;

/**
 * The type of device to use with OpenCL. The appropriate OpenCL drivers must be installed on the user's system.
 * Multiple OpenCL drivers may be installed on the same system; in this case the first available driver that exposes the
 * specified kind of device will be used.
 */
public class IPLComputeDeviceType extends EnumWrapper<IPLComputeDeviceType.Enum> {
    public IPLComputeDeviceType() {
        super(Enum::of);
    }

    public IPLComputeDeviceType(IPLComputeDeviceType.Enum value) {
        super(value, IPLComputeDeviceType.Enum::of);
    }

    public IPLComputeDeviceType(int value) {
        super(value, Enum::of);
    }

    public enum Enum implements IntValuedEnum {
        /**
         * Use a CPU device only.
         */
        IPL_COMPUTEDEVICE_CPU(0),

        /**
         * Use a GPU device only.
         */
        IPL_COMPUTEDEVICE_GPU(1),

        /**
         * Use either a CPU or GPU device, whichever is listed first by the driver.
         */
        IPL_COMPUTEDEVICE_ANY(2),

        IPL_COMPUTEDEVICE_UNKNOWN(-1);

        private final int value;

        Enum(int value) {
            this.value = value;
        }

        public static Enum of(int value) {
            switch (value) {
                case 0:
                    return IPL_COMPUTEDEVICE_CPU;
                case 1:
                    return IPL_COMPUTEDEVICE_GPU;
                case 2:
                    return IPL_COMPUTEDEVICE_ANY;
                default:
                    return IPL_COMPUTEDEVICE_UNKNOWN;
            }
        }

        public int value() {
            return value;
        }

        public IPLComputeDeviceType wrap() {
            return new IPLComputeDeviceType(this);
        }
    }
}
