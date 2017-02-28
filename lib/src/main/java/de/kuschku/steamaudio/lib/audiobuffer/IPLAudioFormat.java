package de.kuschku.steamaudio.lib.audiobuffer;

import de.kuschku.steamaudio.lib.geometry.IPLVector3;
import org.bridj.BridJ;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

/**
 * The format of an audio buffer. Whenever you pass audio data to or from Phonon, you must describe the format in which
 * the audio is encoded. <b>Phonon only supports uncompressed PCM wave data, stored in 32-bit floating point format</b>.
 * However, Phonon supports many different multi-channel and Ambisonics formats, and the {@link IPLAudioFormat} tells
 * Phonon how to interpret a buffer of audio data.
 */
@Library("steamaudio")
public class IPLAudioFormat extends StructObject {
    static {
        BridJ.register();
    }

    public IPLAudioFormat() {
        super();
    }

    public IPLAudioFormat(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    /**
     * @return Indicates whether or not the audio should be interpreted as Ambisonics data.
     */
    @Field(0)
    public IntValuedEnum<IPLChannelLayoutType> channelLayoutType() {
        return this.io.getEnumField(this, 0);
    }

    /**
     * @param channelLayoutType Indicates whether or not the audio should be interpreted as Ambisonics data.
     *
     * @return this
     */
    @Field(0)
    public IPLAudioFormat channelLayoutType(IntValuedEnum<IPLChannelLayoutType> channelLayoutType) {
        this.io.setEnumField(this, 0, channelLayoutType);
        return this;
    }

    /**
     * @return Specifies the speaker configuration used for multi-channel, speaker-based audio data. Ignored if {@link
     * #channelLayoutType} is {@link IPLChannelLayoutType#IPL_CHANNELLAYOUTTYPE_AMBISONICS}.
     */
    @Field(1)
    public IntValuedEnum<IPLChannelLayout> channelLayout() {
        return this.io.getEnumField(this, 1);
    }

    /**
     * @param channelLayout Specifies the speaker configuration used for multi-channel, speaker-based audio data.
     *                      Ignored if {@link #channelLayoutType} is {@link IPLChannelLayoutType#IPL_CHANNELLAYOUTTYPE_AMBISONICS}.
     *
     * @return this
     */
    @Field(1)
    public IPLAudioFormat channelLayout(IntValuedEnum<IPLChannelLayout> channelLayout) {
        this.io.setEnumField(this, 1, channelLayout);
        return this;
    }

    /**
     * @return The number of channels in the audio data. Must be specified regardless of the value of {@link
     * #channelLayoutType}.
     */
    @Field(2)
    public int numSpeakers() {
        return this.io.getIntField(this, 2);
    }

    /**
     * @param numSpeakers The number of channels in the audio data. Must be specified regardless of the value of {@link
     *                    #channelLayoutType}.
     *
     * @return this
     */
    @Field(2)
    public IPLAudioFormat numSpeakers(int numSpeakers) {
        this.io.setIntField(this, 2, numSpeakers);
        return this;
    }

    /**
     * @return An array of {@code IPLVector3} objects indicating the direction of each speaker relative to the user. Can
     * be {@code NULL}. Only used if {@link #channelLayoutType} is {@link IPLChannelLayoutType#IPL_CHANNELLAYOUTTYPE_SPEAKERS}
     * and {@link #channelLayout} is {@link IPLChannelLayout#IPL_CHANNELLAYOUT_CUSTOM}.
     */
    @Field(3)
    public Pointer<IPLVector3> speakerDirections() {
        return this.io.getPointerField(this, 3);
    }

    /**
     * @param speakerDirections An array of {@code IPLVector3} objects indicating the direction of each speaker relative
     *                          to the user. Can be {@code NULL}. Only used if {@link #channelLayoutType} is {@link
     *                          IPLChannelLayoutType#IPL_CHANNELLAYOUTTYPE_SPEAKERS} and {@link #channelLayout} is
     *                          {@link IPLChannelLayout#IPL_CHANNELLAYOUT_CUSTOM}.
     *
     * @return this
     */
    @Field(3)
    public IPLAudioFormat speakerDirections(Pointer<IPLVector3> speakerDirections) {
        this.io.setPointerField(this, 3, speakerDirections);
        return this;
    }

    /**
     * @return The order of Ambisonics to use. Must be 0 or greater. Ignored if {@link #channelLayoutType} is {@link
     * IPLChannelLayoutType#IPL_CHANNELLAYOUTTYPE_SPEAKERS}.
     */
    @Field(4)
    public int ambisonicsOrder() {
        return this.io.getIntField(this, 4);
    }

    /**
     * @param ambisonicsOrder The order of Ambisonics to use. Must be 0 or greater. Ignored if {@link
     *                        #channelLayoutType} is {@link IPLChannelLayoutType#IPL_CHANNELLAYOUTTYPE_SPEAKERS}.
     *
     * @return this
     */
    @Field(4)
    public IPLAudioFormat ambisonicsOrder(int ambisonicsOrder) {
        this.io.setIntField(this, 4, ambisonicsOrder);
        return this;
    }

    /**
     * @return The ordering of Ambisonics channels within the data. Ignored if {@link #channelLayoutType} is {@link
     * IPLChannelLayoutType#IPL_CHANNELLAYOUTTYPE_SPEAKERS}.
     */
    @Field(5)
    public IntValuedEnum<IPLAmbisonicsOrdering> ambisonicsOrdering() {
        return this.io.getEnumField(this, 5);
    }

    /**
     * @param ambisonicsOrdering The ordering of Ambisonics channels within the data. Ignored if {@link
     *                           #channelLayoutType} is {@link IPLChannelLayoutType#IPL_CHANNELLAYOUTTYPE_SPEAKERS}.
     *
     * @return this
     */
    @Field(5)
    public IPLAudioFormat ambisonicsOrdering(IntValuedEnum<IPLAmbisonicsOrdering> ambisonicsOrdering) {
        this.io.setEnumField(this, 5, ambisonicsOrdering);
        return this;
    }

    /**
     * @return The normalization scheme used for Ambisonics data. Ignored if {@link #channelLayoutType} is {@link
     * IPLChannelLayoutType#IPL_CHANNELLAYOUTTYPE_SPEAKERS}.
     */
    @Field(6)
    public IntValuedEnum<IPLAmbisonicsNormalization> ambisonicsNormalization() {
        return this.io.getEnumField(this, 6);
    }

    /**
     * @param ambisonicsNormalization The normalization scheme used for Ambisonics data. Ignored if {@link
     *                                #channelLayoutType} is {@link IPLChannelLayoutType#IPL_CHANNELLAYOUTTYPE_SPEAKERS}.
     *
     * @return this
     */
    @Field(6)
    public IPLAudioFormat ambisonicsNormalization(IntValuedEnum<IPLAmbisonicsNormalization> ambisonicsNormalization) {
        this.io.setEnumField(this, 6, ambisonicsNormalization);
        return this;
    }

    /**
     * @return Whether the audio data is interleaved or deinterleaved.
     */
    @Field(7)
    public IntValuedEnum<IPLChannelOrder> channelOrder() {
        return this.io.getEnumField(this, 7);
    }

    /**
     * @param channelOrder Whether the audio data is interleaved or deinterleaved.
     *
     * @return this
     */
    @Field(7)
    public IPLAudioFormat channelOrder(IntValuedEnum<IPLChannelOrder> channelOrder) {
        this.io.setEnumField(this, 7, channelOrder);
        return this;
    }
}
