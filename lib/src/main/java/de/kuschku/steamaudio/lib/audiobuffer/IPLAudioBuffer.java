package de.kuschku.steamaudio.lib.audiobuffer;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

/**
 * A buffer containing audio data. All audio data passed to or from Phonon must be packaged in {@code IPLAudioBuffer}
 * objects, which describe the format and size of the audio data.
 */
@Library("steamaudio")
public class IPLAudioBuffer extends StructObject {
    static {
        BridJ.register();
    }

    public IPLAudioBuffer() {
        super();
    }

    public IPLAudioBuffer(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    /**
     * @return The format of the audio data.
     */
    @Field(0)
    public IPLAudioFormat format() {
        return this.io.getNativeObjectField(this, 0);
    }

    /**
     * @param format The format of the audio data.
     *
     * @return this
     */
    @Field(0)
    public IPLAudioBuffer format(IPLAudioFormat format) {
        this.io.setNativeObjectField(this, 0, format);
        return this;
    }


    /**
     * @return The number of samples in the audio buffer. The total number of elements in the audio buffer is equal to
     * {@code numSamples} * {@code format.numSpeakers}.
     */
    @Field(1)
    public int numSamples() {
        return this.io.getIntField(this, 1);
    }

    /**
     * @param numSamples The number of samples in the audio buffer. The total number of elements in the audio buffer is
     *                   equal to {@code numSamples} * {@code format.numSpeakers}.
     *
     * @return this
     */
    @Field(1)
    public IPLAudioBuffer numSamples(int numSamples) {
        this.io.setIntField(this, 1, numSamples);
        return this;
    }

    /**
     * @return A pointer to a contiguous block of memory containing interleaved audio data in the format described by
     * {@code format}. Can be {@code NULL} if {@link IPLAudioFormat#channelOrder} is {@link
     * IPLChannelOrder#IPL_CHANNELORDER_DEINTERLEAVED}.
     */
    @Field(2)
    public Pointer<Float> interleavedBuffer() {
        return this.io.getPointerField(this, 2);
    }

    /**
     * @param interleavedBuffer A pointer to a contiguous block of memory containing interleaved audio data in the
     *                          format described by {@code format}. Can be {@code NULL} if {@link
     *                          IPLAudioFormat#channelOrder} is {@link IPLChannelOrder#IPL_CHANNELORDER_DEINTERLEAVED}.
     *
     * @return this
     */
    @Field(2)
    public IPLAudioBuffer interleavedBuffer(Pointer<Float> interleavedBuffer) {
        this.io.setPointerField(this, 2, interleavedBuffer);
        return this;
    }

    /**
     * @return A pointer to an array of pointers, each of which points to a block of memory containing audio data for a
     * single channel of audio data in the format described by {@code format}. In other words, deinterleaved audio data
     * doesn't have to be stored contiguously in memory. Can be {@code NULL} if {@link IPLAudioFormat#channelOrder} is
     * {@link IPLChannelOrder#IPL_CHANNELORDER_INTERLEAVED}.
     */
    @Field(3)
    public Pointer<Pointer<Float>> deinterleavedBuffer() {
        return this.io.getPointerField(this, 3);
    }

    /**
     * @param deinterleavedBuffer A pointer to an array of pointers, each of which points to a block of memory
     *                            containing audio data for a single channel of audio data in the format described by
     *                            {@code format}. In other words, deinterleaved audio data doesn't have to be stored
     *                            contiguously in memory. Can be {@code NULL} if {@link IPLAudioFormat#channelOrder} is
     *                            {@link IPLChannelOrder#IPL_CHANNELORDER_INTERLEAVED}.
     *
     * @return this
     */
    @Field(3)
    public IPLAudioBuffer deinterleavedBuffer(Pointer<Pointer<Float>> deinterleavedBuffer) {
        this.io.setPointerField(this, 3, deinterleavedBuffer);
        return this;
    }
}
