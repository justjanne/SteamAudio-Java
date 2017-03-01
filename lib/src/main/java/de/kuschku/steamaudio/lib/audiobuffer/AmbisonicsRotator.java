package de.kuschku.steamaudio.lib.audiobuffer;

import de.kuschku.steamaudio.lib.error.SteamAudioException;
import de.kuschku.steamaudio.lib.util.*;
import de.kuschku.steamaudio.lib.geometry.IPLQuaternion;

public class AmbisonicsRotator extends PointerHandle {
    /**
     * Creates an Ambisonics Rotator object. An Ambisonics Rotator object is used to apply an arbitrary rotation to
     * audio data encoded in Ambisonics. This is primarily useful in the following situations: <ul> <li>If you have an
     * Ambisonics audio buffer whose coefficients are defined relative to world space coordinates, you can convert them
     * to listener space using an Ambisonics Rotator object. This is necessary when using a Convolution Effect object,
     * since its output is defined in world space, and will not change if the listener looks around. <li>If your final
     * mix is encoded in Ambisonics, and the user is using headphones with head tracking, you can use the Ambisonics
     * Rotator object to make the sound field stay "in place" as the user looks around in the real world. This is
     * achieved by using the Ambisonics Rotator object to apply the inverse of the user's rotation to the final mix.
     * </ul>
     *
     * @param order The order of the Ambisonics data to rotate.
     *
     * @throws SteamAudioException Describes what kind of error happened in native code.
     */
    public AmbisonicsRotator(IPLAmbisonicsOrdering order) throws SteamAudioException {
        super(SteamAudio.audiobuffer::iplCreateAmbisonicsRotator, order);
        setOnDelete(SteamAudio.audiobuffer::iplDestroyAmbisonicsRotator);
    }

    /**
     * Specifies a rotation value. This function must be called before using {@link #rotateBuffer} to rotate an
     * Ambisonics-encoded audio buffer, or the resulting audio will be incorrect.
     *
     * @param quaternion A unit quaternion describing the 3D transformation from world space to listener space
     *                   coordinates.
     */
    public void setRotation(IPLQuaternion quaternion) {
        SteamAudioBridge.audiobuffer.__iplSetAmbisonicsRotation(this, quaternion);
    }

    /**
     * Rotates an Ambisonics-encoded audio buffer. The {@link #setRotation} function must have been called prior to
     * calling this function, or the resulting audio will be incorrect. It is possible to pass the same value for
     * {@code inputAudio} and {@code outputAudio}. This results in in-place rotation of the Ambisonics data.
     *
     * @param inputAudio  Audio buffer containing the Ambisonics-encoded data that is to be rotated. The format of this
     *                    buffer must be Ambisonics.
     * @param outputAudio Audio buffer containing the rotated Ambisonics-encoded data. The format of this buffer must be
     *                    Ambisonics.
     */
    public void rotateBuffer(IPLAudioBuffer inputAudio, IPLAudioBuffer outputAudio) {
        SteamAudioBridge.audiobuffer.__iplRotateAmbisonicsAudioBuffer(this, inputAudio, outputAudio);
    }
}
