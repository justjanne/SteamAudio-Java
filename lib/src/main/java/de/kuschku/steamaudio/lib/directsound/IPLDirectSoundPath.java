package de.kuschku.steamaudio.lib.directsound;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import de.kuschku.steamaudio.lib.geometry.IPLVector3;

import java.util.Arrays;
import java.util.List;

public class IPLDirectSoundPath extends Structure {
    public IPLVector3 direction;
    public float distanceAttenuation;
    public float[] airAbsorption = new float[3];
    public float propagationDelay;
    public float occlusionFactor;

    public IPLDirectSoundPath() {
        super();
    }

    public IPLDirectSoundPath(IPLVector3 direction, float distanceAttenuation, float airAbsorption[],
                              float propagationDelay, float occlusionFactor) {
        super();
        this.direction = direction;
        this.distanceAttenuation = distanceAttenuation;
        if ((airAbsorption.length != this.airAbsorption.length))
            throw new IllegalArgumentException("Wrong array size !");
        this.airAbsorption = airAbsorption;
        this.propagationDelay = propagationDelay;
        this.occlusionFactor = occlusionFactor;
    }

    public IPLDirectSoundPath(Pointer peer) {
        super(peer);
    }

    protected List<String> getFieldOrder() {
        return Arrays
                .asList("direction", "distanceAttenuation", "airAbsorption", "propagationDelay", "occlusionFactor");
    }

    public static class ByReference extends IPLDirectSoundPath implements Structure.ByReference {
    }

    public static class ByValue extends IPLDirectSoundPath implements Structure.ByValue {
    }
}