package de.kuschku.steamaudio.lib.directsound;

import de.kuschku.steamaudio.lib.geometry.IPLVector3;
import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Array;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library("steamaudio")
public class IPLDirectSoundPath extends StructObject {
    static {
        BridJ.register();
    }

    public IPLDirectSoundPath() {
        super();
    }

    public IPLDirectSoundPath(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    @Field(0)
    public IPLVector3 direction() {
        return this.io.getNativeObjectField(this, 0);
    }

    @Field(0)
    public IPLDirectSoundPath direction(IPLVector3 direction) {
        this.io.setNativeObjectField(this, 0, direction);
        return this;
    }

    @Field(1)
    public float distanceAttenuation() {
        return this.io.getFloatField(this, 1);
    }

    @Field(1)
    public IPLDirectSoundPath distanceAttenuation(float distanceAttenuation) {
        this.io.setFloatField(this, 1, distanceAttenuation);
        return this;
    }

    @Array({3})
    @Field(2)
    public Pointer<Float> airAbsorption() {
        return this.io.getPointerField(this, 2);
    }

    @Field(3)
    public float propagationDelay() {
        return this.io.getFloatField(this, 3);
    }

    @Field(3)
    public IPLDirectSoundPath propagationDelay(float propagationDelay) {
        this.io.setFloatField(this, 3, propagationDelay);
        return this;
    }

    @Field(4)
    public float occlusionFactor() {
        return this.io.getFloatField(this, 4);
    }

    @Field(4)
    public IPLDirectSoundPath occlusionFactor(float occlusionFactor) {
        this.io.setFloatField(this, 4, occlusionFactor);
        return this;
    }
}
