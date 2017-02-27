package de.kuschku.steamaudio.lib.geometry;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library("steamaudio")
public class IPLBox extends StructObject {
    static {
        BridJ.register();
    }

    public IPLBox() {
        super();
    }

    public IPLBox(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    @Field(0)
    public IPLVector3 minCoordinates() {
        return this.io.getNativeObjectField(this, 0);
    }

    @Field(0)
    public IPLBox minCoordinates(IPLVector3 minCoordinates) {
        this.io.setNativeObjectField(this, 0, minCoordinates);
        return this;
    }

    @Field(1)
    public IPLVector3 maxCoordinates() {
        return this.io.getNativeObjectField(this, 1);
    }

    @Field(1)
    public IPLBox maxCoordinates(IPLVector3 maxCoordinates) {
        this.io.setNativeObjectField(this, 1, maxCoordinates);
        return this;
    }
}
