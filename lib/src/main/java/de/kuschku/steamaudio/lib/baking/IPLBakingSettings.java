package de.kuschku.steamaudio.lib.baking;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import de.kuschku.steamaudio.lib.util.SmartStructure;

import java.util.Arrays;
import java.util.List;

/**
 * Specifies the kind of acoustic responses to save in the baked data.
 */
public class IPLBakingSettings extends Structure implements SmartStructure<IPLBakingSettings> {
    /**
     * Enables the generation of I3DL2-compliant parametric reverb. This is most suited for calculating reverb in
     * relatively enclosed spaces. It is less suitable for open spaces, or source-to-listener propagation. It consumes
     * very little memory per probe.
     */
    public boolean bakeParametric;

    /**
     * Enables the generation of detailed impulse responses for convolution reverb. This is suited for all kinds of
     * spaces, and for reverb as well as source-to-listener propagation. However, it consumes significantly more memory
     * per probe.
     */
    public boolean bakeConvolution;

    public IPLBakingSettings() {
        super();
    }

    public IPLBakingSettings(boolean bakeParametric, boolean bakeConvolution) {
        super();
        this.bakeParametric = bakeParametric;
        this.bakeConvolution = bakeConvolution;
    }

    public IPLBakingSettings(Pointer peer) {
        super(peer);
    }

    protected List<String> getFieldOrder() {
        return Arrays.asList("bakeParametric", "bakeConvolution");
    }

    public static class ByReference extends IPLBakingSettings implements Structure.ByReference {
    }

    public static class ByValue extends IPLBakingSettings implements Structure.ByValue {
    }
}