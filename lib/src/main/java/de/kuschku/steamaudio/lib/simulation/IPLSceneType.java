package de.kuschku.steamaudio.lib.simulation;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

import java.util.Collections;
import java.util.Iterator;

public enum IPLSceneType implements IntValuedEnum<IPLSceneType> {
    IPL_SCENETYPE_PHONON(0), IPL_SCENETYPE_EMBREE(1), IPL_SCENETYPE_FIRERAYS(2), IPL_SCENETYPE_CUSTOM(3);

    public final long value;

    IPLSceneType(long value) {
        this.value = value;
    }

    public static IntValuedEnum<IPLSceneType> fromValue(int value) {
        return FlagSet.fromValue(value, values());
    }

    public long value() {
        return this.value;
    }

    public Iterator<IPLSceneType> iterator() {
        return Collections.singleton(this).iterator();
    }
}
