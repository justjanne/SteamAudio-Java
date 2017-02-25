package de.kuschku.steamaudio.lib.simsettings;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * Configures the complexity of the simulation. You can fine-tune these values to arrive at a suitable balance between
 * performance, memory usage, and acoustic detail.
 */
public class IPLSimulationSettings extends Structure {
    /**
     * The ray tracer to use for simulation. {@link IPLSceneType}.
     */
    public IPLSceneType sceneType;

    /**
     * The number of rays to trace from the listener. Increasing this number increases the accuracy of the simulation,
     * but also increases CPU usage. Any positive integer may be specified, but typical values are in the range of 1024
     * to 131072.
     */
    public int numRays;

    /**
     * The number of directions to consider when a ray bounces off a diffuse (or partly diffuse) surface. Increasing
     * this number increases the accuracy of diffuse reflections, and does not significantly impact CPU usage. Any
     * positive integer may be specified, but typical values are in the range of 32 to 4096.
     */
    public int numDiffuseSamples;

    /**
     * The maximum number of times any ray can bounce within the scene. Increasing this number allows the simulation to
     * more accurately model reverberant spaces, at the cost of increased CPU usage. Any positive integer may be
     * specified, but typical values are in the range of 1 to 32.
     */
    public int numBounces;

    /**
     * The time delay between a sound being emitted and the last audible reflection. Echoes and reverberation longer
     * than this amount will not be modeled by the simulation. Any positive number may be specified, but typical values
     * are in the range of 0.5 to 4.0.
     */
    public float irDuration;

    /**
     * The amount of directional detail in the simulation results. Phonon encodes the simulation results using
     * Ambisonics. Increasing this number increases the amount of directional detail in the simulated acoustics, but at
     * the cost of increased CPU usage and memory consumption. Any non-negative integer may be specified, but typical
     * values are between 0 and 3. A value of 0 results in no directional variation in the simulation results. Values of
     * 4 or higher incur a significant performance penalty.
     */
    public int ambisonicsOrder;

    /**
     * The maximum number of sound sources that can be simulated and rendered using a Convolution Effect object at any
     * point in time. If you attempt to create more than this many Convolution Effect objects, creation will fail.
     * Increasing this number allows more sound sources to be rendered with sound propagation effects, but at the cost
     * of increased memory consumption.
     */
    public int maxConvolutionSources;

    public IPLSimulationSettings() {
        super();
    }

    public IPLSimulationSettings(IPLSceneType sceneType, int numRays, int numDiffuseSamples, int numBounces,
                                 float irDuration, int ambisonicsOrder, int maxConvolutionSources) {
        super();
        this.sceneType = sceneType;
        this.numRays = numRays;
        this.numDiffuseSamples = numDiffuseSamples;
        this.numBounces = numBounces;
        this.irDuration = irDuration;
        this.ambisonicsOrder = ambisonicsOrder;
        this.maxConvolutionSources = maxConvolutionSources;
    }

    public IPLSimulationSettings(Pointer peer) {
        super(peer);
    }

    protected List<String> getFieldOrder() {
        return Arrays.asList("sceneType", "numRays", "numDiffuseSamples", "numBounces", "irDuration", "ambisonicsOrder",
                "maxConvolutionSources");
    }

    public static class ByReference extends IPLSimulationSettings implements Structure.ByReference {
    }

    public static class ByValue extends IPLSimulationSettings implements Structure.ByValue {
    }
}