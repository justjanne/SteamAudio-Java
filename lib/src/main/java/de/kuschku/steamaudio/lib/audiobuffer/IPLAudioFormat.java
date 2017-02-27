package de.kuschku.steamaudio.lib.audiobuffer;

import de.kuschku.steamaudio.lib.geometry.IPLVector3;
import org.bridj.BridJ;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library("steamaudio")
public class IPLAudioFormat extends StructObject {
    static {
        BridJ.register();
    }

    public IPLAudioFormat() {
        super();
    }

    public IPLAudioFormat(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    @Field(0)
    public IntValuedEnum<IPLChannelLayoutType> channelLayoutType() {
        return this.io.getEnumField(this, 0);
    }

    @Field(0)
    public IPLAudioFormat channelLayoutType(IntValuedEnum<IPLChannelLayoutType> channelLayoutType) {
        this.io.setEnumField(this, 0, channelLayoutType);
        return this;
    }

    @Field(1)
    public IntValuedEnum<IPLChannelLayout> channelLayout() {
        return this.io.getEnumField(this, 1);
    }

    @Field(1)
    public IPLAudioFormat channelLayout(IntValuedEnum<IPLChannelLayout> channelLayout) {
        this.io.setEnumField(this, 1, channelLayout);
        return this;
    }

    @Field(2)
    public int numSpeakers() {
        return this.io.getIntField(this, 2);
    }

    @Field(2)
    public IPLAudioFormat numSpeakers(int numSpeakers) {
        this.io.setIntField(this, 2, numSpeakers);
        return this;
    }

    @Field(3)
    public Pointer<IPLVector3> speakerDirections() {
        return this.io.getPointerField(this, 3);
    }

    @Field(3)
    public IPLAudioFormat speakerDirections(Pointer<IPLVector3> speakerDirections) {
        this.io.setPointerField(this, 3, speakerDirections);
        return this;
    }

    @Field(4)
    public int ambisonicsOrder() {
        return this.io.getIntField(this, 4);
    }

    @Field(4)
    public IPLAudioFormat ambisonicsOrder(int ambisonicsOrder) {
        this.io.setIntField(this, 4, ambisonicsOrder);
        return this;
    }

    @Field(5)
    public IntValuedEnum<IPLAmbisonicsOrdering> ambisonicsOrdering() {
        return this.io.getEnumField(this, 5);
    }

    @Field(5)
    public IPLAudioFormat ambisonicsOrdering(IntValuedEnum<IPLAmbisonicsOrdering> ambisonicsOrdering) {
        this.io.setEnumField(this, 5, ambisonicsOrdering);
        return this;
    }

    @Field(6)
    public IntValuedEnum<IPLAmbisonicsNormalization> ambisonicsNormalization() {
        return this.io.getEnumField(this, 6);
    }

    @Field(6)
    public IPLAudioFormat ambisonicsNormalization(IntValuedEnum<IPLAmbisonicsNormalization> ambisonicsNormalization) {
        this.io.setEnumField(this, 6, ambisonicsNormalization);
        return this;
    }

    @Field(7)
    public IntValuedEnum<IPLChannelOrder> channelOrder() {
        return this.io.getEnumField(this, 7);
    }

    @Field(7)
    public IPLAudioFormat channelOrder(IntValuedEnum<IPLChannelOrder> channelOrder) {
        this.io.setEnumField(this, 7, channelOrder);
        return this;
    }
}
