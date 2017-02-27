package de.kuschku.steamaudio.lib.rendersettings;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

import java.util.Collections;
import java.util.Iterator;

public enum IPLConvolutionType implements IntValuedEnum<IPLConvolutionType> {
    IPL_CONVOLUTIONTYPE_PHONON(0), IPL_CONVOLUTIONTYPE_TRUEAUDIONEXT(1);

    public final long value;

    IPLConvolutionType(long value) {
        this.value = value;
    }

    public static IntValuedEnum<IPLConvolutionType> fromValue(int value) {
        return FlagSet.fromValue(value, values());
    }

    public long value() {
        return this.value;
    }

    public Iterator<IPLConvolutionType> iterator() {
        return Collections.singleton(this).iterator();
    }
}
