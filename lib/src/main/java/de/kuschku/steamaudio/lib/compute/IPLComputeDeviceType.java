package de.kuschku.steamaudio.lib.compute;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

import java.util.Collections;
import java.util.Iterator;

/**
 * The type of device to use with OpenCL. The appropriate OpenCL drivers must be installed on the user's system.
 * Multiple OpenCL drivers may be installed on the same system; in this case the first available driver that exposes the
 * specified kind of device will be used.
 */
public enum IPLComputeDeviceType implements IntValuedEnum<IPLComputeDeviceType> {
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
    IPL_COMPUTEDEVICE_ANY(2);

    public final long value;

    IPLComputeDeviceType(long value) {
        this.value = value;
    }

    public static IntValuedEnum<IPLComputeDeviceType> fromValue(int value) {
        return FlagSet.fromValue(value, values());
    }

    public long value() {
        return this.value;
    }

    public Iterator<IPLComputeDeviceType> iterator() {
        return Collections.singleton(this).iterator();
    }
}
