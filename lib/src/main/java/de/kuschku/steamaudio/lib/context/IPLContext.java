package de.kuschku.steamaudio.lib.context;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library("steamaudio")
public class IPLContext extends StructObject {
    static {
        BridJ.register();
    }

    public IPLContext() {
        super();
    }

    public IPLContext(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    @Field(0)
    public Pointer<IPLLogFunction> logCallback() {
        return this.io.getPointerField(this, 0);
    }

    @Field(0)
    public IPLContext logCallback(Pointer<IPLLogFunction> logCallback) {
        this.io.setPointerField(this, 0, logCallback);
        return this;
    }

    @Field(1)
    public Pointer<IPLAllocateFunction> allocateCallback() {
        return this.io.getPointerField(this, 1);
    }

    @Field(1)
    public IPLContext allocateCallback(Pointer<IPLAllocateFunction> allocateCallback) {
        this.io.setPointerField(this, 1, allocateCallback);
        return this;
    }

    @Field(2)
    public Pointer<IPLFreeFunction> freeCallback() {
        return this.io.getPointerField(this, 2);
    }

    @Field(2)
    public IPLContext freeCallback(Pointer<IPLFreeFunction> freeCallback) {
        this.io.setPointerField(this, 2, freeCallback);
        return this;
    }
}
