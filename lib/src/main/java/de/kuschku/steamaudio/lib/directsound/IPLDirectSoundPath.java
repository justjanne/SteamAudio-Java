package de.kuschku.steamaudio.lib.directsound;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import de.kuschku.steamaudio.lib.geometry.IPLVector3;
import de.kuschku.steamaudio.lib.util.SmartStructure;

import java.util.Arrays;
import java.util.List;

/**
 * Parameters describing a direct sound path. The audio engine must decide how to use the information provided by Phonon
 * via this structure.
 */
public class IPLDirectSoundPath extends Structure implements SmartStructure<IPLDirectSoundPath> {
    /**
     * Unit vector from the listener to the source.
     */
    public IPLVector3 direction;

    /**
     * Scaling factor to apply to direct sound, that arises due to the spherical attenuation of sound with distance from
     * the source. Linear scale from 0.0 to 1.0.
     */
    public float distanceAttenuation;

    /**
     * Scaling factors to apply to direct sound, for low, middle, and high frequencies, that arise due to the scattering
     * of sound waves as they travel through the air. Linear scale from 0.0 to 1.0.
     */
    public float[] airAbsorption = new float[3];

    /**
     * Time delay (in seconds) due to propagation from the source to the listener.
     */
    public float propagationDelay;

    /**
     * Scaling factor to apply to direct sound, that arises due to occlusion by scene geometry. Linear scale from 0.0 to
     * 1.0.
     */
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