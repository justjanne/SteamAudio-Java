package de.kuschku.steamaudio.lib.audiobuffer;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library("steamaudio")
public class IPLAudioBuffer extends StructObject {
    static {
        BridJ.register();
    }

    public IPLAudioBuffer() {
        super();
    }

    public IPLAudioBuffer(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    @Field(0)
    public IPLAudioFormat format() {
        return this.io.getNativeObjectField(this, 0);
    }

    @Field(0)
    public IPLAudioBuffer format(IPLAudioFormat format) {
        this.io.setNativeObjectField(this, 0, format);
        return this;
    }

    @Field(1)
    public int numSamples() {
        return this.io.getIntField(this, 1);
    }

    @Field(1)
    public IPLAudioBuffer numSamples(int numSamples) {
        this.io.setIntField(this, 1, numSamples);
        return this;
    }

    @Field(2)
    public Pointer<Float> interleavedBuffer() {
        return this.io.getPointerField(this, 2);
    }

    @Field(2)
    public IPLAudioBuffer interleavedBuffer(Pointer<Float> interleavedBuffer) {
        this.io.setPointerField(this, 2, interleavedBuffer);
        return this;
    }

    @Field(3)
    public Pointer<Pointer<Float>> deinterleavedBuffer() {
        return this.io.getPointerField(this, 3);
    }

    @Field(3)
    public IPLAudioBuffer deinterleavedBuffer(Pointer<Pointer<Float>> deinterleavedBuffer) {
        this.io.setPointerField(this, 3, deinterleavedBuffer);
        return this;
    }
}
