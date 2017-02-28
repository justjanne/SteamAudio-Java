package de.kuschku.steamaudio.lib.rendersettings;

import org.bridj.BridJ;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

/**
 * Describes various properties of the audio processing pipeline. Many Phonon API objects that are used by the audio
 * engine need to know how the audio processing pipeline (i.e., your audio engine) applies DSP effects to audio data.
 * This structure describes the key parameters.
 */
@Library("steamaudio")
public class IPLRenderingSettings extends StructObject {
    static {
        BridJ.register();
    }

    public IPLRenderingSettings() {
        super();
    }

    public IPLRenderingSettings(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    /**
     * @return The sampling rate (in Hz) of any audio to be processed by Phonon. <b>All audio that is passed to Phonon
     * must use the same sampling rate.</b> Phonon will output audio at the same sampling rate as its input; no sampling
     * rate conversion will be performed. Supported sampling rates are 24000 Hz, 44100 Hz, and 48000 Hz.
     */
    @Field(0)
    public int samplingRate() {
        return this.io.getIntField(this, 0);
    }

    /**
     * @param samplingRate The sampling rate (in Hz) of any audio to be processed by Phonon. <b>All audio that is passed
     *                     to Phonon must use the same sampling rate.</b> Phonon will output audio at the same sampling
     *                     rate as its input; no sampling rate conversion will be performed. Supported sampling rates
     *                     are 24000 Hz, 44100 Hz, and 48000 Hz.
     */
    @Field(0)
    public IPLRenderingSettings samplingRate(int samplingRate) {
        this.io.setIntField(this, 0, samplingRate);
        return this;
    }

    /**
     * @return The number of samples in a single frame of audio. The value of this parameter should be obtained from
     * your audio engine.
     */
    @Field(1)
    public int frameSize() {
        return this.io.getIntField(this, 1);
    }

    /**
     * @param frameSize The number of samples in a single frame of audio. The value of this parameter should be obtained
     *                  from your audio engine.
     */
    @Field(1)
    public IPLRenderingSettings frameSize(int frameSize) {
        this.io.setIntField(this, 1, frameSize);
        return this;
    }

    /**
     * @return The convolution algorithm to use for any Convolution Effect objects created for this audio processing
     * pipeline.
     */
    @Field(2)
    public IntValuedEnum<IPLConvolutionType> convolutionType() {
        return this.io.getEnumField(this, 2);
    }

    /**
     * @param convolutionType The convolution algorithm to use for any Convolution Effect objects created for this audio
     *                        processing pipeline.
     */
    @Field(2)
    public IPLRenderingSettings convolutionType(IntValuedEnum<IPLConvolutionType> convolutionType) {
        this.io.setEnumField(this, 2, convolutionType);
        return this;
    }
}
