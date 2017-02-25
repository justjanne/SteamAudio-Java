package de.kuschku.steamaudio.lib.scene;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * The acoustic properties of a surface. You can specify the acoustic material properties of each triangle, although
 * typically many triangles will share a common material.
 */
public class IPLMaterial extends Structure {
    /**
     * Fraction of sound energy absorbed at low frequencies. Between 0.0 and 1.0.
     */
    public float lowFreqAbsorption;

    /**
     * Fraction of sound energy absorbed at middle frequencies. Between 0.0 and 1.0.
     */
    public float midFreqAbsorption;

    /**
     * Fraction of sound energy absorbed at high frequencies. Between 0.0 and 1.0.
     */
    public float highFreqAbsorption;

    /**
     * Fraction of sound energy that is scattered in a random direction when it reaches the surface. Between 0.0 and
     * 1.0. A value of 0.0 describes a smooth surface with mirror-like reflection properties; a value of 1.0 describes
     * rough surface with diffuse reflection properties.
     */
    public float scattering;

    public IPLMaterial() {
        super();
    }

    public IPLMaterial(float lowFreqAbsorption, float midFreqAbsorption, float highFreqAbsorption, float scattering) {
        super();
        this.lowFreqAbsorption = lowFreqAbsorption;
        this.midFreqAbsorption = midFreqAbsorption;
        this.highFreqAbsorption = highFreqAbsorption;
        this.scattering = scattering;
    }

    public IPLMaterial(Pointer peer) {
        super(peer);
    }

    protected List<String> getFieldOrder() {
        return Arrays.asList("lowFreqAbsorption", "midFreqAbsorption", "highFreqAbsorption", "scattering");
    }

    public static class ByReference extends IPLMaterial implements Structure.ByReference {
    }

    public static class ByValue extends IPLMaterial implements Structure.ByValue {
    }
}