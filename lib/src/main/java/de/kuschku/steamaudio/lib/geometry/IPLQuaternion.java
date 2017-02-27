package de.kuschku.steamaudio.lib.geometry;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library("steamaudio")
public class IPLQuaternion extends StructObject {
    static {
        BridJ.register();
    }

    public IPLQuaternion() {
        super();
    }

    public IPLQuaternion(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    @Field(0)
    public float x() {
        return this.io.getFloatField(this, 0);
    }

    @Field(0)
    public IPLQuaternion x(float x) {
        this.io.setFloatField(this, 0, x);
        return this;
    }

    @Field(1)
    public float y() {
        return this.io.getFloatField(this, 1);
    }

    @Field(1)
    public IPLQuaternion y(float y) {
        this.io.setFloatField(this, 1, y);
        return this;
    }

    @Field(2)
    public float z() {
        return this.io.getFloatField(this, 2);
    }

    @Field(2)
    public IPLQuaternion z(float z) {
        this.io.setFloatField(this, 2, z);
        return this;
    }

    @Field(3)
    public float w() {
        return this.io.getFloatField(this, 3);
    }

    @Field(3)
    public IPLQuaternion w(float w) {
        this.io.setFloatField(this, 3, w);
        return this;
    }
}
