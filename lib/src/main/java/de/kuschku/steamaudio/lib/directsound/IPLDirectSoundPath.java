package de.kuschku.steamaudio.lib.directsound;

import de.kuschku.steamaudio.lib.geometry.IPLVector3;
import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Array;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

/**
 * Parameters describing a direct sound path. The audio engine must decide how to use the information provided by Phonon
 * via this structure.
 */
@Library("steamaudio")
public class IPLDirectSoundPath extends StructObject {
    static {
        BridJ.register();
    }

    public IPLDirectSoundPath() {
        super();
    }

    public IPLDirectSoundPath(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    /**
     * @return Unit vector from the listener to the source.
     */
    @Field(0)
    public IPLVector3 direction() {
        return this.io.getNativeObjectField(this, 0);
    }

    /**
     * @param direction Unit vector from the listener to the source.
     *
     * @return this
     */
    @Field(0)
    public IPLDirectSoundPath direction(IPLVector3 direction) {
        this.io.setNativeObjectField(this, 0, direction);
        return this;
    }

    /**
     * @return Scaling factor to apply to direct sound, that arises due to the spherical attenuation of sound with
     * distance from the source. Linear scale from 0.0 to 1.0.
     */
    @Field(1)
    public float distanceAttenuation() {
        return this.io.getFloatField(this, 1);
    }

    /**
     * @param distanceAttenuation Scaling factor to apply to direct sound, that arises due to the spherical attenuation
     *                            of sound with distance from the source. Linear scale from 0.0 to 1.0.
     *
     * @return this
     */
    @Field(1)
    public IPLDirectSoundPath distanceAttenuation(float distanceAttenuation) {
        this.io.setFloatField(this, 1, distanceAttenuation);
        return this;
    }

    /**
     * @return Scaling factors to apply to direct sound, for low, middle, and high frequencies, that arise due to the
     * scattering of sound waves as they travel through the air. Linear scale from 0.0 to 1.0.
     */
    @Array({3})
    @Field(2)
    public Pointer<Float> airAbsorption() {
        return this.io.getPointerField(this, 2);
    }

    /**
     * @return Time delay (in seconds) due to propagation from the source to the listener.
     */
    @Field(3)
    public float propagationDelay() {
        return this.io.getFloatField(this, 3);
    }

    /**
     * @param propagationDelay Time delay (in seconds) due to propagation from the source to the listener.
     *
     * @return this
     */
    @Field(3)
    public IPLDirectSoundPath propagationDelay(float propagationDelay) {
        this.io.setFloatField(this, 3, propagationDelay);
        return this;
    }

    /**
     * @return Scaling factor to apply to direct sound, that arises due to occlusion by scene geometry. Linear scale
     * from 0.0 to 1.0.
     */
    @Field(4)
    public float occlusionFactor() {
        return this.io.getFloatField(this, 4);
    }

    /**
     * @param occlusionFactor Scaling factor to apply to direct sound, that arises due to occlusion by scene geometry.
     *                        Linear scale from 0.0 to 1.0.
     *
     * @return this
     */
    @Field(4)
    public IPLDirectSoundPath occlusionFactor(float occlusionFactor) {
        this.io.setFloatField(this, 4, occlusionFactor);
        return this;
    }
}
