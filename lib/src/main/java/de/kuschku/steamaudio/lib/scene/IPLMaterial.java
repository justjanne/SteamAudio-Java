package de.kuschku.steamaudio.lib.scene;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

/**
 * The acoustic properties of a surface. You can specify the acoustic material properties of each triangle, although
 * typically many triangles will share a common material.
 */
@Library("steamaudio")
public class IPLMaterial extends StructObject {
    static {
        BridJ.register();
    }

    public IPLMaterial() {
        super();
    }

    public IPLMaterial(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    /**
     * @return Fraction of sound energy absorbed at low frequencies. Between 0.0 and 1.0.
     */
    @Field(0)
    public float lowFreqAbsorption() {
        return this.io.getFloatField(this, 0);
    }

    /**
     * @param lowFreqAbsorption Fraction of sound energy absorbed at low frequencies. Between 0.0 and 1.0.
     *
     * @return this
     */
    @Field(0)
    public IPLMaterial lowFreqAbsorption(float lowFreqAbsorption) {
        this.io.setFloatField(this, 0, lowFreqAbsorption);
        return this;
    }

    /**
     * @return Fraction of sound energy absorbed at middle frequencies. Between 0.0 and 1.0.
     */
    @Field(1)
    public float midFreqAbsorption() {
        return this.io.getFloatField(this, 1);
    }

    /**
     * @param midFreqAbsorption Fraction of sound energy absorbed at middle frequencies. Between 0.0 and 1.0.
     *
     * @return this
     */
    @Field(1)
    public IPLMaterial midFreqAbsorption(float midFreqAbsorption) {
        this.io.setFloatField(this, 1, midFreqAbsorption);
        return this;
    }

    /**
     * @return Fraction of sound energy absorbed at high frequencies. Between 0.0 and 1.0.
     */
    @Field(2)
    public float highFreqAbsorption() {
        return this.io.getFloatField(this, 2);
    }

    /**
     * @param highFreqAbsorption Fraction of sound energy absorbed at high frequencies. Between 0.0 and 1.0.
     *
     * @return this
     */
    @Field(2)
    public IPLMaterial highFreqAbsorption(float highFreqAbsorption) {
        this.io.setFloatField(this, 2, highFreqAbsorption);
        return this;
    }

    /**
     * @return Fraction of sound energy that is scattered in a random direction when it reaches the surface. Between 0.0
     * and 1.0. A value of 0.0 describes a smooth surface with mirror-like reflection properties; a value of 1.0
     * describes rough surface with diffuse reflection properties.
     */
    @Field(3)
    public float scattering() {
        return this.io.getFloatField(this, 3);
    }

    /**
     * @param scattering Fraction of sound energy that is scattered in a random direction when it reaches the surface.
     *                   Between 0.0 and 1.0. A value of 0.0 describes a smooth surface with mirror-like reflection
     *                   properties; a value of 1.0 describes rough surface with diffuse reflection properties.
     *
     * @return this
     */
    @Field(3)
    public IPLMaterial scattering(float scattering) {
        this.io.setFloatField(this, 3, scattering);
        return this;
    }
}
