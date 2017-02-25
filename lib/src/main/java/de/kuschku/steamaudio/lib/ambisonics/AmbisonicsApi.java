package de.kuschku.steamaudio.lib.ambisonics;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioBuffer;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioFormat;
import de.kuschku.steamaudio.lib.util.IPLerror;

@SuppressWarnings("unused")
public interface AmbisonicsApi extends Library {
    static AmbisonicsApi getInstance() {
        return Native.loadLibrary("steamaudio", AmbisonicsApi.class);
    }

    /**
     * Creates an Ambisonics Binaural Effect object. This can be used to render higher-order Ambisonics data using
     * HRTF-based binaural rendering.
     *
     * @param renderer     Handle to a Binaural Renderer object.
     * @param inputFormat  The format of the audio buffers that will be passed as input to this effect. All subsequent
     *                     calls to {@link AmbisonicsApi#iplApplyAmbisonicsBinauralEffect} for this effect object must
     *                     use
     *                     {@link IPLAudioBuffer} objects with the same format as specified here. The input format must
     *                     be Ambisonics.
     * @param outputFormat The format of the audio buffers which will be used to retrieve the output from this effect.
     *                     All subsequent calls to {@link AmbisonicsApi#iplApplyAmbisonicsBinauralEffect} for this
     *                     effect
     *                     object must use {@link IPLAudioBuffer} objects with the same format as specified here. The
     *                     output format must be stereo (2 channels).
     * @param effect       [out] Handle to the created Ambisonics Binaural Effect object.
     *
     * @return Status code indicating whether or not the operation succeeded.
     */
    IPLerror iplCreateAmbisonicsBinauralEffect(Pointer renderer, IPLAudioFormat.ByValue inputFormat,
                                               IPLAudioFormat.ByValue outputFormat, PointerByReference effect);

    /**
     * Destroys an Ambisonics Binaural Effect object.
     *
     * @param effect [in, out] Address of a handle to the Ambisonics Binaural Effect object to destroy.
     */
    void iplDestroyAmbisonicsBinauralEffect(PointerByReference effect);

    /**
     * Applies HRTF-based binaural rendering to a buffer of Ambisonics audio data. Ambisonics encoders and decoders use
     * many different conventions to store the multiple Ambisonics channels, as well as different normalization schemes.
     * Make sure that you correctly specify these settings when creating the Ambisonics Binaural Effect object,
     * otherwise the rendered audio will be incorrect.
     *
     * @param effect      Handle to an Ambisonics Binaural Effect object.
     * @param inputAudio  Audio buffer containing the data to render using binaural rendering. The format of this
     *                    buffer
     *                    must match the {@code inputFormat} parameter passed to {@link AmbisonicsApi#iplCreateAmbisonicsBinauralEffect}.
     * @param outputAudio Audio buffer that should contain the rendered audio data. The format of this buffer must
     *                    match
     *                    the {@code outputFormat} parameter passed to {@link AmbisonicsApi#iplCreateAmbisonicsBinauralEffect}.
     */
    void iplApplyAmbisonicsBinauralEffect(Pointer effect, IPLAudioBuffer.ByValue inputAudio,
                                          IPLAudioBuffer.ByValue outputAudio);
}
