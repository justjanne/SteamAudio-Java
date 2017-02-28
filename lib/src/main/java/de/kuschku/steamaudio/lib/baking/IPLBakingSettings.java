package de.kuschku.steamaudio.lib.baking;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

/**
 * Specifies the kind of acoustic responses to save in the baked data.
 */
@Library("steamaudio")
public class IPLBakingSettings extends StructObject {
    static {
        BridJ.register();
    }

    public IPLBakingSettings() {
        super();
    }

    public IPLBakingSettings(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    /**
     * @return Enables the generation of I3DL2-compliant parametric reverb. This is most suited for calculating reverb
     * in relatively enclosed spaces. It is less suitable for open spaces, or source-to-listener propagation. It
     * consumes very little memory per probe.
     */
    @Field(0)
    public boolean bakeParametric() {
        return this.io.getBooleanField(this, 0);
    }

    /**
     * @param bakeParametric Enables the generation of I3DL2-compliant parametric reverb. This is most suited for
     *                       calculating reverb in relatively enclosed spaces. It is less suitable for open spaces, or
     *                       source-to-listener propagation. It consumes very little memory per probe.
     */
    @Field(0)
    public IPLBakingSettings bakeParametric(boolean bakeParametric) {
        this.io.setBooleanField(this, 0, bakeParametric);
        return this;
    }

    /**
     * @return Enables the generation of detailed impulse responses for convolution reverb. This is suited for all kinds
     * of spaces, and for reverb as well as source-to-listener propagation. However, it consumes significantly more
     * memory per probe.
     */
    @Field(1)
    public boolean bakeConvolution() {
        return this.io.getBooleanField(this, 1);
    }

    /**
     * @param bakeConvolution Enables the generation of detailed impulse responses for convolution reverb. This is
     *                        suited for all kinds of spaces, and for reverb as well as source-to-listener propagation.
     *                        However, it consumes significantly more memory per probe.
     */
    @Field(1)
    public IPLBakingSettings bakeConvolution(boolean bakeConvolution) {
        this.io.setBooleanField(this, 1, bakeConvolution);
        return this;
    }
}
