package de.kuschku.steamaudio.lib.geometry;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library("steamaudio")
public class IPLVector3 extends StructObject {
    static {
        BridJ.register();
    }

    public IPLVector3() {
        super();
    }

    public IPLVector3(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    @Field(0)
    public float x() {
        return this.io.getFloatField(this, 0);
    }

    @Field(0)
    public IPLVector3 x(float x) {
        this.io.setFloatField(this, 0, x);
        return this;
    }

    @Field(1)
    public float y() {
        return this.io.getFloatField(this, 1);
    }

    @Field(1)
    public IPLVector3 y(float y) {
        this.io.setFloatField(this, 1, y);
        return this;
    }

    @Field(2)
    public float z() {
        return this.io.getFloatField(this, 2);
    }

    @Field(2)
    public IPLVector3 z(float z) {
        this.io.setFloatField(this, 2, z);
        return this;
    }
}
