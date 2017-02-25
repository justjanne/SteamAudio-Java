package de.kuschku.steamaudio.lib.audiobuffer;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.PointerByReference;
import de.kuschku.steamaudio.lib.util.SmartStructure;

import java.util.Arrays;
import java.util.List;

/**
 * A buffer containing audio data. All audio data passed to or from Phonon must be packaged in {@link #IPLAudioBuffer}
 * objects, which describe the format and size of the audio data.
 */
public class IPLAudioBuffer extends Structure implements SmartStructure<IPLAudioBuffer> {
    /**
     * The format of the audio data.
     */
    public IPLAudioFormat format;

    /**
     * The number of samples in the audio buffer. The total number of elements in the audio buffer is equal to \c
     * numSamples * {@link #format}.numSpeakers.
     */
    public int numSamples;

    /**
     * A pointer to a contiguous block of memory containing interleaved audio data in the format described by {@link
     * #format}.
     * Can be {@code null} if {@link #format}.channelOrder is {@link IPLChannelOrder.Enum#IPL_CHANNELORDER_DEINTERLEAVED}.
     */
    public FloatByReference interleavedBuffer;

    /**
     * A pointer to an array of pointers, each of which points to a block of memory containing audio data for a single
     * channel of audio data in the format described by {@link #format}. In other words, deinterleaved audio data
     * doesn't have
     * to be stored contiguously in memory. Can be {@code null} if {@link IPLAudioFormat#channelOrder} is {@link
     * IPLChannelOrder.Enum#IPL_CHANNELORDER_INTERLEAVED}.
     */
    public PointerByReference deinterleavedBuffer;

    public IPLAudioBuffer() {
        super();
    }

    public IPLAudioBuffer(IPLAudioFormat format, int numSamples, FloatByReference interleavedBuffer,
                          PointerByReference deinterleavedBuffer) {
        super();
        this.format = format;
        this.numSamples = numSamples;
        this.interleavedBuffer = interleavedBuffer;
        this.deinterleavedBuffer = deinterleavedBuffer;
    }

    public IPLAudioBuffer(Pointer peer) {
        super(peer);
    }

    protected List<String> getFieldOrder() {
        return Arrays.asList("format", "numSamples", "interleavedBuffer", "deinterleavedBuffer");
    }

    public static class ByReference extends IPLAudioBuffer implements Structure.ByReference {
    }

    public static class ByValue extends IPLAudioBuffer implements Structure.ByValue {
    }
}