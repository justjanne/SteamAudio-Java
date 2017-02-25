package de.kuschku.steamaudio.lib.ambisonicspanning;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioBuffer;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioFormat;
import de.kuschku.steamaudio.lib.util.IPLerror;

@SuppressWarnings("unused")
public interface AmbisonicsPanningApi extends Library {
    static AmbisonicsPanningApi getInstance() {
        return Native.loadLibrary("steamaudio", AmbisonicsPanningApi.class);
    }

    /**
     * Creates an Ambisonics Panning Effect object. This can be used to render higher-order Ambisonics data using
     * standard panning algorithms.
     *
     * @param renderer     Handle to a Binaural Renderer object.
     * @param inputFormat  The format of the audio buffers that will be passed as input to this effect. All subsequent
     *                     calls to {@link AmbisonicsPanningApi#iplApplyAmbisonicsPanningEffect} for this effect object
     *                     must use
     *                     {@link IPLAudioBuffer} objects with the same format as specified here. The input format must
     *                     be Ambisonics.
     * @param outputFormat The format of the audio buffers which will be used to retrieve the output from this effect.
     *                     All subsequent calls to {@link AmbisonicsPanningApi#iplApplyAmbisonicsPanningEffect} for
     *                     this
     *                     effect
     *                     object must use {@link IPLAudioBuffer} objects with the same format as specified here.
     * @param effect       [out] Handle to the created Ambisonics Panning Effect object.
     *
     * @return Status code indicating whether or not the operation succeeded.
     */
    IPLerror iplCreateAmbisonicsPanningEffect(Pointer renderer, IPLAudioFormat.ByValue inputFormat,
                                              IPLAudioFormat.ByValue outputFormat, PointerByReference effect);

    /**
     * Destroys an Ambisonics Panning Effect object.
     *
     * @param effect [in, out] Address of a handle to the Ambisonics Panning Effect object to destroy.
     */
    void iplDestroyAmbisonicsPanningEffect(PointerByReference effect);

    /**
     * Applies a panning-based rendering algorithm to a buffer of Ambisonics audio data. Ambisonics encoders and
     * decoders use many different conventions to store the multiple Ambisonics channels, as well as different
     * normalization schemes. Make sure that you correctly specify these settings when creating the Ambisonics Panning
     * Effect object, otherwise the rendered audio will be incorrect.
     *
     * @param effect      Handle to an Ambisonics Panning Effect object.
     * @param inputAudio  Audio buffer containing the data to render. The format of this buffer must match the {@code
     *                    inputFormat} parameter passed to {@link AmbisonicsPanningApi#iplCreateAmbisonicsPanningEffect}.
     * @param outputAudio Audio buffer that should contain the rendered audio data. The format of this buffer must
     *                    match
     *                    the {@code outputFormat} parameter passed to {@link AmbisonicsPanningApi#iplCreateAmbisonicsPanningEffect}.
     */
    void iplApplyAmbisonicsPanningEffect(Pointer effect, IPLAudioBuffer.ByValue inputAudio,
                                         IPLAudioBuffer.ByValue outputAudio);
}
