package de.kuschku.steamaudio.lib.rendersettings;

import org.bridj.BridJ;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library("steamaudio")
public class IPLRenderingSettings extends StructObject {
    static {
        BridJ.register();
    }

    public IPLRenderingSettings() {
        super();
    }

    public IPLRenderingSettings(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    @Field(0)
    public int samplingRate() {
        return this.io.getIntField(this, 0);
    }

    @Field(0)
    public IPLRenderingSettings samplingRate(int samplingRate) {
        this.io.setIntField(this, 0, samplingRate);
        return this;
    }

    @Field(1)
    public int frameSize() {
        return this.io.getIntField(this, 1);
    }

    @Field(1)
    public IPLRenderingSettings frameSize(int frameSize) {
        this.io.setIntField(this, 1, frameSize);
        return this;
    }

    @Field(2)
    public IntValuedEnum<IPLConvolutionType> convolutionType() {
        return this.io.getEnumField(this, 2);
    }

    @Field(2)
    public IPLRenderingSettings convolutionType(IntValuedEnum<IPLConvolutionType> convolutionType) {
        this.io.setEnumField(this, 2, convolutionType);
        return this;
    }
}
