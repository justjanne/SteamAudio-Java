package de.kuschku.steamaudio.lib.scene;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library("steamaudio")
public class IPLMaterial extends StructObject {
    static {
        BridJ.register();
    }

    public IPLMaterial() {
        super();
    }

    public IPLMaterial(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    @Field(0)
    public float lowFreqAbsorption() {
        return this.io.getFloatField(this, 0);
    }

    @Field(0)
    public IPLMaterial lowFreqAbsorption(float lowFreqAbsorption) {
        this.io.setFloatField(this, 0, lowFreqAbsorption);
        return this;
    }

    @Field(1)
    public float midFreqAbsorption() {
        return this.io.getFloatField(this, 1);
    }

    @Field(1)
    public IPLMaterial midFreqAbsorption(float midFreqAbsorption) {
        this.io.setFloatField(this, 1, midFreqAbsorption);
        return this;
    }

    @Field(2)
    public float highFreqAbsorption() {
        return this.io.getFloatField(this, 2);
    }

    @Field(2)
    public IPLMaterial highFreqAbsorption(float highFreqAbsorption) {
        this.io.setFloatField(this, 2, highFreqAbsorption);
        return this;
    }

    @Field(3)
    public float scattering() {
        return this.io.getFloatField(this, 3);
    }

    @Field(3)
    public IPLMaterial scattering(float scattering) {
        this.io.setFloatField(this, 3, scattering);
        return this;
    }
}
