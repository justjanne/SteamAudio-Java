package de.kuschku.steamaudio.lib.simulation;

import org.bridj.BridJ;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library("steamaudio")
public class IPLSimulationSettings extends StructObject {
    static {
        BridJ.register();
    }

    public IPLSimulationSettings() {
        super();
    }

    public IPLSimulationSettings(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    @Field(0)
    public IntValuedEnum<IPLSceneType> sceneType() {
        return this.io.getEnumField(this, 0);
    }

    @Field(0)
    public IPLSimulationSettings sceneType(IntValuedEnum<IPLSceneType> sceneType) {
        this.io.setEnumField(this, 0, sceneType);
        return this;
    }

    @Field(1)
    public int numRays() {
        return this.io.getIntField(this, 1);
    }

    @Field(1)
    public IPLSimulationSettings numRays(int numRays) {
        this.io.setIntField(this, 1, numRays);
        return this;
    }

    @Field(2)
    public int numDiffuseSamples() {
        return this.io.getIntField(this, 2);
    }

    @Field(2)
    public IPLSimulationSettings numDiffuseSamples(int numDiffuseSamples) {
        this.io.setIntField(this, 2, numDiffuseSamples);
        return this;
    }

    @Field(3)
    public int numBounces() {
        return this.io.getIntField(this, 3);
    }

    @Field(3)
    public IPLSimulationSettings numBounces(int numBounces) {
        this.io.setIntField(this, 3, numBounces);
        return this;
    }

    @Field(4)
    public float irDuration() {
        return this.io.getFloatField(this, 4);
    }

    @Field(4)
    public IPLSimulationSettings irDuration(float irDuration) {
        this.io.setFloatField(this, 4, irDuration);
        return this;
    }

    @Field(5)
    public int ambisonicsOrder() {
        return this.io.getIntField(this, 5);
    }

    @Field(5)
    public IPLSimulationSettings ambisonicsOrder(int ambisonicsOrder) {
        this.io.setIntField(this, 5, ambisonicsOrder);
        return this;
    }

    @Field(6)
    public int maxConvolutionSources() {
        return this.io.getIntField(this, 6);
    }

    @Field(6)
    public IPLSimulationSettings maxConvolutionSources(int maxConvolutionSources) {
        this.io.setIntField(this, 6, maxConvolutionSources);
        return this;
    }
}
