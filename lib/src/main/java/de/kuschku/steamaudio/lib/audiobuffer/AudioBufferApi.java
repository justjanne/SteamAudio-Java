package de.kuschku.steamaudio.lib.audiobuffer;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import de.kuschku.steamaudio.lib.geometry.IPLQuaternion;
import de.kuschku.steamaudio.lib.util.IPLerror;

@SuppressWarnings("unused")
public interface AudioBufferApi extends Library {
    static AudioBufferApi getInstance() {
        return Native.loadLibrary("steamaudio", AudioBufferApi.class);
    }

    /**
     * Mixes a set of audio buffers.  This is primarily useful for mixing the output of multiple Panning Effect objects,
     * before passing them to a single Virtual Surround Effect or a single Ambisonics Binaural Effect. This way,
     * applications can significantly accelerate 3D audio rendering for large numbers of sources.
     *
     * @param numBuffers  The number of input buffers to mix. Must be greater than 0.
     * @param inputAudio  Array of audio buffers to mix. All of these audio buffers must have identical formats.
     * @param outputAudio Audio buffer that will contain the mixed audio data. The format of this buffer must be
     *                    identical to all buffers contained in {@code inputAudio}.
     */
    void iplMixAudioBuffers(int numBuffers, IPLAudioBuffer inputAudio, IPLAudioBuffer.ByValue outputAudio);

    /**
     * Interleaves a deinterleaved audio buffer. The formats of {@code inputAudio} and {@code outputAudio} must be
     * identical except for the {@link IPLAudioFormat#channelOrder} field.
     *
     * @param inputAudio  The input audio buffer. This audio buffer must be deinterleaved.
     * @param outputAudio The output audio buffer. This audio buffer must be interleaved.
     */
    void iplInterleaveAudioBuffer(IPLAudioBuffer.ByValue inputAudio, IPLAudioBuffer.ByValue outputAudio);

    /**
     * Deinterleaves an interleaved audio buffer. The formats of {@code inputAudio} and {@code outputAudio} must be
     * identical except for the {@link IPLAudioFormat#channelOrder} field.
     *
     * @param inputAudio  The input audio buffer. This audio buffer must be interleaved.
     * @param outputAudio The output audio buffer. This audio buffer must be deinterleaved.
     */
    void iplDeinterleaveAudioBuffer(IPLAudioBuffer.ByValue inputAudio, IPLAudioBuffer.ByValue outputAudio);

    /**
     * Converts the format of an audio buffer into the format of the output audio buffer. This is primarily useful for
     * 360 video and audio authoring workflows. Both the input and output audio buffers must be deinterleaved. The
     * following format conversions are supported:
     * <ul>
     * <li>mono to multi-channel speaker-based formats (stereo, quadraphonic, 5.1, 7.1)</li>
     * <li>multi-channel speaker-based (stereo, quadraphonic, 5.1, 7.1) to mono</li>
     * <li>stereo to 5.1 or 7.1</li>
     * <li>Ambisonics to multi-channel speaker-based (mono, stereo, quadraphonic, 5.1, 7.1)</li>
     * </ul>
     *
     * @param inputAudio  The input audio buffer.
     * @param outputAudio The output audio buffer.
     */
    void iplConvertAudioBufferFormat(IPLAudioBuffer.ByValue inputAudio, IPLAudioBuffer.ByValue outputAudio);

    /**
     * Creates an Ambisonics Rotator object. An Ambisonics Rotator object is used to apply an arbitrary rotation to
     * audio data encoded in Ambisonics. This is primarily useful in the following situations:
     * <ul>
     * <li>If you have an Ambisonics audio buffer whose coefficients are defined relative to world space coordinates,
     * you can convert them to listener space using an Ambisonics Rotator object. This is necessary when using
     * Convolution Effect object, since its output is defined in world space, and will not change if the listener looks
     * around.</li>
     * <li>If your final mix is encoded in Ambisonics, and the user is using headphones with head tracking,
     * you can use the Ambisonics Rotator object to make the sound field stay "in place" as the user looks around in the
     * real world. This is achieved by using the Ambisonics Rotator object to apply the inverse of the user's rotation
     * to the final mix.</li>
     * </ul>
     *
     * @param order   The order of the Ambisonics data to rotate.
     * @param rotator [out] Handle to the created Ambisonics Rotator object.
     *
     * @return Status code indicating whether or not the operation succeeded.
     */
    IPLerror iplCreateAmbisonicsRotator(IPLAmbisonicsOrdering order, PointerByReference rotator);

    /**
     * Destroys an Ambisonics Rotator object.
     *
     * @param rotator [in, out] Address of a handle to the Ambisonics Rotator object to destroy.
     */
    void iplDestroyAmbisonicsRotator(PointerByReference rotator);

    /**
     * Specifies a rotation value. This function must be called before using {@link
     * AudioBufferApi#iplRotateAmbisonicsAudioBuffer} to rotate an Ambisonics-encoded audio buffer, or the resulting
     * audio
     * will be incorrect.
     *
     * @param rotator    Handle to an Ambisonics Rotator object.
     * @param quaternion A unit quaternion describing the 3D transformation from world space to listener
     *                   space coordinates.
     */
    void iplSetAmbisonicsRotation(Pointer rotator, IPLQuaternion.ByValue quaternion);

    /**
     * Rotates an Ambisonics-encoded audio buffer. The {@link AudioBufferApi#iplSetAmbisonicsRotation} function must
     * have
     * been called prior to calling this function, or the resulting audio will be incorrect. It is possible to pass the
     * same value for {@code inputAudio} and {@code outputAudio}. This results in in-place rotation of the Ambisonics
     * data.
     *
     * @param rotator     Handle to an Ambisonics Rotator object.
     * @param inputAudio  Audio buffer containing the Ambisonics-encoded data that is to be rotated. The format of this
     *                    buffer must be Ambisonics.
     * @param outputAudio Audio buffer containing the rotated Ambisonics-encoded data. The format of this buffer must
     *                    be Ambisonics.
     */
    void iplRotateAmbisonicsAudioBuffer(Pointer rotator, IPLAudioBuffer.ByValue inputAudio,
                                        IPLAudioBuffer.ByValue outputAudio);
}
