package de.kuschku.steamaudio.lib.rendersettings;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import de.kuschku.steamaudio.lib.util.SmartStructure;

import java.util.Arrays;
import java.util.List;

/**
 * Describes various properties of the audio processing pipeline. Many Phonon API objects that are used by the audio
 * engine need to know how the audio processing pipeline (i.e., your audio engine) applies DSP effects to audio data.
 * This structure describes the key parameters.
 */
public class IPLRenderingSettings extends Structure implements SmartStructure<IPLRenderingSettings> {
    /**
     * The sampling rate (in Hz) of any audio to be processed by Phonon. <b>All audio that is passed to Phonon must use
     * the same sampling rate.</b> Phonon will output audio at the same sampling rate as its input; no sampling rate
     * conversion will be performed. Supported sampling rates are 24000 Hz, 44100 Hz, and 48000 Hz.
     */
    public int samplingRate;

    /**
     * The number of samples in a single frame of audio. The value of this parameter should be obtained from your audio
     * engine.
     */
    public int frameSize;

    /**
     * The convolution algorithm to use for any Convolution Effect objects created for this audio processing pipeline.
     */
    public IPLConvolutionType convolutionType;

    public IPLRenderingSettings() {
        super();
    }

    public IPLRenderingSettings(int samplingRate, int frameSize, IPLConvolutionType convolutionType) {
        super();
        this.samplingRate = samplingRate;
        this.frameSize = frameSize;
        this.convolutionType = convolutionType;
    }

    public IPLRenderingSettings(Pointer peer) {
        super(peer);
    }

    protected List<String> getFieldOrder() {
        return Arrays.asList("samplingRate", "frameSize", "convolutionType");
    }

    public static class ByReference extends IPLRenderingSettings implements Structure.ByReference {
    }

    public static class ByValue extends IPLRenderingSettings implements Structure.ByValue {
    }
}