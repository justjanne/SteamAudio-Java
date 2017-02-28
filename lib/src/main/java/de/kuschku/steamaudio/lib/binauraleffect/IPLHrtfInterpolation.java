package de.kuschku.steamaudio.lib.binauraleffect;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

import java.util.Collections;
import java.util.Iterator;

/**
 * Techniques for interpolating HRTF data. This is used when rendering a point source whose position relative to
 * the listener is not contained in the measured HRTF data used by Phonon.
 */
public enum IPLHrtfInterpolation implements IntValuedEnum<IPLHrtfInterpolation> {
    /**
     * Nearest-neighbor filtering, i.e., no interpolation. Selects the measurement location that is closest to the
     * source's actual location.
     */
    IPL_HRTFINTERPOLATION_NEAREST(0),

    /**
     * Bilinear filtering. Incurs a relatively high CPU overhead as compared to nearest-neighbor filtering, so use this
     * for sounds where it has a significant benefit.
     */
    IPL_HRTFINTERPOLATION_BILINEAR(1);

    public final long value;

    IPLHrtfInterpolation(long value) {
        this.value = value;
    }

    public static IntValuedEnum<IPLHrtfInterpolation> fromValue(int value) {
        return FlagSet.fromValue(value, values());
    }

    public long value() {
        return this.value;
    }

    public Iterator<IPLHrtfInterpolation> iterator() {
        return Collections.singleton(this).iterator();
    }
}
