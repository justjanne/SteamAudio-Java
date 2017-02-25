package de.kuschku.steamaudio.lib.audiobuffer;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import de.kuschku.steamaudio.lib.geometry.IPLVector3;

import java.util.Arrays;
import java.util.List;

/**
 * The format of an audio buffer. Whenever you pass audio data to or from Phonon, you must describe the format in which
 * the audio is encoded. **Phonon only supports uncompressed PCM wave data, stored in 32-bit floating point format**.
 * However, Phonon supports many different multi-channel and Ambisonics formats, and the {@link IPLAudioFormat} tells
 * Phonon how to interpret a buffer of audio data.
 */
public class IPLAudioFormat extends Structure {
    /**
     * Indicates whether or not the audio should be interpreted as Ambisonics data.
     */
    public IPLChannelLayoutType channelLayoutType;

    /**
     * Specifies the speaker configuration used for multi-channel, speaker-based audio data. Ignored if {@link
     * #channelLayoutType} is {@link IPLChannelLayoutType.Enum#IPL_CHANNELLAYOUTTYPE_AMBISONICS}.
     */
    public IPLChannelLayout channelLayout;

    /**
     * The number of channels in the audio data. Must be specified regardless of the value of {@link
     * #channelLayoutType}.
     */
    public int numSpeakers;

    /**
     * An array of {@link IPLVector3} objects indicating the direction of each speaker relative to the user. Can be
     * {@code null}. Only used if {@link #channelLayoutType} is {@link IPLChannelLayoutType.Enum#IPL_CHANNELLAYOUTTYPE_SPEAKERS}
     * and {@link #channelLayout} is {@link IPLChannelLayout.Enum#IPL_CHANNELLAYOUT_CUSTOM}.
     */
    public IPLVector3.ByReference speakerDirections;

    /**
     * The order of Ambisonics to use. Must be 0 or greater. Ignored if {@link #channelLayoutType} is {@link
     * IPLChannelLayoutType.Enum#IPL_CHANNELLAYOUTTYPE_SPEAKERS}.
     */
    public int ambisonicsOrder;

    /**
     * The ordering of Ambisonics channels within the data. Ignored if {@link #channelLayoutType} is {@link
     * IPLChannelLayoutType.Enum#IPL_CHANNELLAYOUTTYPE_SPEAKERS}.
     */
    public IPLAmbisonicsOrdering ambisonicsOrdering;

    /**
     * The normalization scheme used for Ambisonics data. Ignored if {@link #channelLayoutType} is {@link
     * IPLChannelLayoutType.Enum#IPL_CHANNELLAYOUTTYPE_SPEAKERS}.
     */
    public IPLAmbisonicsNormalization ambisonicsNormalization;

    /**
     * Whether the audio data is interleaved or deinterleaved.
     */
    public IPLChannelOrder channelOrder;

    public IPLAudioFormat() {
        super();
    }

    public IPLAudioFormat(IPLChannelLayoutType channelLayoutType, IPLChannelLayout channelLayout, int numSpeakers,
                          IPLVector3.ByReference speakerDirections, int ambisonicsOrder,
                          IPLAmbisonicsOrdering ambisonicsOrdering, IPLAmbisonicsNormalization ambisonicsNormalization,
                          IPLChannelOrder channelOrder) {
        super();
        this.channelLayoutType = channelLayoutType;
        this.channelLayout = channelLayout;
        this.numSpeakers = numSpeakers;
        this.speakerDirections = speakerDirections;
        this.ambisonicsOrder = ambisonicsOrder;
        this.ambisonicsOrdering = ambisonicsOrdering;
        this.ambisonicsNormalization = ambisonicsNormalization;
        this.channelOrder = channelOrder;
    }

    public IPLAudioFormat(Pointer peer) {
        super(peer);
    }

    protected List<String> getFieldOrder() {
        return Arrays
                .asList("channelLayoutType", "channelLayout", "numSpeakers", "speakerDirections", "ambisonicsOrder",
                        "ambisonicsOrdering", "ambisonicsNormalization", "channelOrder");
    }

    public static class ByReference extends IPLAudioFormat implements Structure.ByReference {
    }

    public static class ByValue extends IPLAudioFormat implements Structure.ByValue {
    }
}