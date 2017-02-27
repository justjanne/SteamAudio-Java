package de.kuschku.steamaudio.lib.compute;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

import java.util.Collections;
import java.util.Iterator;

public enum IPLComputeDeviceType implements IntValuedEnum<IPLComputeDeviceType> {
    IPL_COMPUTEDEVICE_CPU(0), IPL_COMPUTEDEVICE_GPU(1), IPL_COMPUTEDEVICE_ANY(2);

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
