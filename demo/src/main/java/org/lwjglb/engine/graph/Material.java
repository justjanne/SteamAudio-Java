package org.lwjglb.engine.graph;

import de.kuschku.steamaudio.lib.scene.IPLMaterial;
import org.joml.Vector3f;

public class Material {

    private static final Vector3f DEFAULT_COLOUR = new Vector3f(1.0f, 1.0f, 1.0f);

    private Vector3f colour;

    private float reflectance;

    private IPLMaterial audioMaterial = new IPLMaterial();

    private Texture texture;

    private Texture normalMap;

    public Material() {
        colour = DEFAULT_COLOUR;
        reflectance = 0;
    }

    public Material(Vector3f colour, float reflectance) {
        this();
        this.colour = colour;
        this.reflectance = reflectance;
    }

    public Material(Texture texture) {
        this();
        this.texture = texture;
    }

    public Material(Texture texture, float reflectance) {
        this();
        this.texture = texture;
        this.reflectance = reflectance;
    }

    public Vector3f getColour() {
        return colour;
    }

    public void setColour(Vector3f colour) {
        this.colour = colour;
    }

    public float getReflectance() {
        return reflectance;
    }

    public void setReflectance(float reflectance) {
        this.reflectance = reflectance;
    }

    public boolean isTextured() {
        return this.texture != null;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public boolean hasNormalMap() {
        return this.normalMap != null;
    }

    public Texture getNormalMap() {
        return normalMap;
    }

    public void setNormalMap(Texture normalMap) {
        this.normalMap = normalMap;
    }

    public float lowFreqAbsorption() {
        return audioMaterial.lowFreqAbsorption();
    }

    public IPLMaterial lowFreqAbsorption(float lowFreqAbsorption) {
        return audioMaterial.lowFreqAbsorption(lowFreqAbsorption);
    }

    public float midFreqAbsorption() {
        return audioMaterial.midFreqAbsorption();
    }

    public IPLMaterial midFreqAbsorption(float midFreqAbsorption) {
        return audioMaterial.midFreqAbsorption(midFreqAbsorption);
    }

    public float highFreqAbsorption() {
        return audioMaterial.highFreqAbsorption();
    }

    public IPLMaterial highFreqAbsorption(float highFreqAbsorption) {
        return audioMaterial.highFreqAbsorption(highFreqAbsorption);
    }

    public float scattering() {
        return audioMaterial.scattering();
    }

    public IPLMaterial scattering(float scattering) {
        return audioMaterial.scattering(scattering);
    }

    public IPLMaterial getAudioMaterial() {
        return audioMaterial;
    }
}