package de.kuschku.steamaudio.lib.scene;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Array;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library("steamaudio")
public class IPLTriangle extends StructObject {
    static {
        BridJ.register();
    }

    public IPLTriangle() {
        super();
    }

    public IPLTriangle(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    @Array({3})
    @Field(0)
    public Pointer<Integer> indices() {
        return this.io.getPointerField(this, 0);
    }
}
