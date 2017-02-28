package de.kuschku.steamaudio.lib.simulation;

import org.bridj.BridJ;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

/**
 * Configures the complexity of the simulation. You can fine-tune these values to arrive at a suitable balance between
 * performance, memory usage, and acoustic detail.
 */
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

    /**
     * @return The ray tracer to use for simulation.
     * @see IPLSceneType
     */
    @Field(0)
    public IntValuedEnum<IPLSceneType> sceneType() {
        return this.io.getEnumField(this, 0);
    }

    /**
     * @param sceneType The ray tracer to use for simulation.
     *
     * @see IPLSceneType
     */
    @Field(0)
    public IPLSimulationSettings sceneType(IntValuedEnum<IPLSceneType> sceneType) {
        this.io.setEnumField(this, 0, sceneType);
        return this;
    }

    /**
     * @return The number of rays to trace from the listener. Increasing this number increases the accuracy of the
     * simulation, but also increases CPU usage. Any positive integer may be specified, but typical values are in the
     * range of 1024 to 131072.
     */
    @Field(1)
    public int numRays() {
        return this.io.getIntField(this, 1);
    }

    /**
     * @param numRays The number of rays to trace from the listener. Increasing this number increases the accuracy of
     *                the simulation, but also increases CPU usage. Any positive integer may be specified, but typical
     *                values are in the range of 1024 to 131072.
     */
    @Field(1)
    public IPLSimulationSettings numRays(int numRays) {
        this.io.setIntField(this, 1, numRays);
        return this;
    }

    /**
     * @return The number of directions to consider when a ray bounces off a diffuse (or partly diffuse) surface.
     * Increasing this number increases the accuracy of diffuse reflections, and does not significantly impact CPU
     * usage. Any positive integer may be specified, but typical values are in the range of 32 to 4096.
     */
    @Field(2)
    public int numDiffuseSamples() {
        return this.io.getIntField(this, 2);
    }

    /**
     * @param numDiffuseSamples The number of directions to consider when a ray bounces off a diffuse (or partly
     *                          diffuse) surface. Increasing this number increases the accuracy of diffuse reflections,
     *                          and does not significantly impact CPU usage. Any positive integer may be specified, but
     *                          typical values are in the range of 32 to 4096.
     */
    @Field(2)
    public IPLSimulationSettings numDiffuseSamples(int numDiffuseSamples) {
        this.io.setIntField(this, 2, numDiffuseSamples);
        return this;
    }

    /**
     * @return The maximum number of times any ray can bounce within the scene. Increasing this number allows the
     * simulation to more accurately model reverberant spaces, at the cost of increased CPU usage. Any positive integer
     * may be specified, but typical values are in the range of 1 to 32.
     */
    @Field(3)
    public int numBounces() {
        return this.io.getIntField(this, 3);
    }

    /**
     * @param numBounces The maximum number of times any ray can bounce within the scene. Increasing this number allows
     *                   the simulation to more accurately model reverberant spaces, at the cost of increased CPU usage.
     *                   Any positive integer may be specified, but typical values are in the range of 1 to 32.
     */
    @Field(3)
    public IPLSimulationSettings numBounces(int numBounces) {
        this.io.setIntField(this, 3, numBounces);
        return this;
    }

    /**
     * @return The time delay between a sound being emitted and the last audible reflection. Echoes and reverberation
     * longer than this amount will not be modeled by the simulation. Any positive number may be specified, but typical
     * values are in the range of 0.5 to 4.0.
     */
    @Field(4)
    public float irDuration() {
        return this.io.getFloatField(this, 4);
    }

    /**
     * @param irDuration The time delay between a sound being emitted and the last audible reflection. Echoes and
     *                   reverberation longer than this amount will not be modeled by the simulation. Any positive
     *                   number may be specified, but typical values are in the range of 0.5 to 4.0.
     */
    @Field(4)
    public IPLSimulationSettings irDuration(float irDuration) {
        this.io.setFloatField(this, 4, irDuration);
        return this;
    }

    /**
     * @return The amount of directional detail in the simulation results. Phonon encodes the simulation results using
     * Ambisonics. Increasing this number increases the amount of directional detail in the simulated acoustics, but at
     * the cost of increased CPU usage and memory consumption. Any non-negative integer may be specified, but typical
     * values are between 0 and 3. A value of 0 results in no directional variation in the simulation results. Values of
     * 4 or higher incur a significant performance penalty.
     */
    @Field(5)
    public int ambisonicsOrder() {
        return this.io.getIntField(this, 5);
    }

    /**
     * @param ambisonicsOrder The amount of directional detail in the simulation results. Phonon encodes the simulation
     *                        results using Ambisonics. Increasing this number increases the amount of directional
     *                        detail in the simulated acoustics, but at the cost of increased CPU usage and memory
     *                        consumption. Any non-negative integer may be specified, but typical values are between 0
     *                        and 3. A value of 0 results in no directional variation in the simulation results. Values
     *                        of 4 or higher incur a significant performance penalty.
     */
    @Field(5)
    public IPLSimulationSettings ambisonicsOrder(int ambisonicsOrder) {
        this.io.setIntField(this, 5, ambisonicsOrder);
        return this;
    }

    /**
     * @return The maximum number of sound sources that can be simulated and rendered using a Convolution Effect object
     * at any point in time. If you attempt to create more than this many Convolution Effect objects, creation will
     * fail. Increasing this number allows more sound sources to be rendered with sound propagation effects, but at the
     * cost of increased memory consumption.
     */
    @Field(6)
    public int maxConvolutionSources() {
        return this.io.getIntField(this, 6);
    }

    /**
     * @param maxConvolutionSources The maximum number of sound sources that can be simulated and rendered using a
     *                              Convolution Effect object at any point in time. If you attempt to create more than
     *                              this many Convolution Effect objects, creation will fail. Increasing this number
     *                              allows more sound sources to be rendered with sound propagation effects, but at the
     *                              cost of increased memory consumption.
     */
    @Field(6)
    public IPLSimulationSettings maxConvolutionSources(int maxConvolutionSources) {
        this.io.setIntField(this, 6, maxConvolutionSources);
        return this;
    }
}
