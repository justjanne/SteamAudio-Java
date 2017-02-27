package de.kuschku.steamaudio.lib.directsound;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

import java.util.Collections;
import java.util.Iterator;

public enum IPLDirectOcclusionMethod implements IntValuedEnum<IPLDirectOcclusionMethod> {
    IPL_DIRECTOCCLUSION_NONE(0), IPL_DIRECTOCCLUSION_RAYCAST(1), IPL_DIRECTOCCLUSION_VOLUMETRIC(2);

    public final long value;

    IPLDirectOcclusionMethod(long value) {
        this.value = value;
    }

    public static IntValuedEnum<IPLDirectOcclusionMethod> fromValue(int value) {
        return FlagSet.fromValue(value, values());
    }

    public long value() {
        return this.value;
    }

    public Iterator<IPLDirectOcclusionMethod> iterator() {
        return Collections.singleton(this).iterator();
    }
}
