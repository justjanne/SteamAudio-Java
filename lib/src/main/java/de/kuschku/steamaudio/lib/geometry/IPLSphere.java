package de.kuschku.steamaudio.lib.geometry;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library("steamaudio")
public class IPLSphere extends StructObject {
    static {
        BridJ.register();
    }

    public IPLSphere() {
        super();
    }

    public IPLSphere(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    @Field(0)
    public IPLVector3 center() {
        return this.io.getNativeObjectField(this, 0);
    }

    @Field(0)
    public IPLSphere center(IPLVector3 center) {
        this.io.setNativeObjectField(this, 0, center);
        return this;
    }

    @Field(1)
    public float radius() {
        return this.io.getFloatField(this, 1);
    }

    @Field(1)
    public IPLSphere radius(float radius) {
        this.io.setFloatField(this, 1, radius);
        return this;
    }
}
