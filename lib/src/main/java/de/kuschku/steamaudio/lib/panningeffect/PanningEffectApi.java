package de.kuschku.steamaudio.lib.panningeffect;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioBuffer;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioFormat;
import de.kuschku.steamaudio.lib.geometry.IPLVector3;
import de.kuschku.steamaudio.lib.util.IPLerror;

@SuppressWarnings("unused")
public interface PanningEffectApi extends Library {
    static PanningEffectApi getInstance() {
        return Native.loadLibrary("steamaudio", PanningEffectApi.class);
    }

    /**
     * Creates a Panning Effect object. This can be used to render a point source on surround speakers, or using
     * Ambisonics.
     *
     * @param renderer     Handle to a Binaural Renderer object.
     * @param inputFormat  The format of the audio buffers that will be passed as input to this effect. All subsequent
     *                     calls to {@link PanningEffectApi#iplApplyPanningEffect} for this effect object must use
     *                     {@link
     *                     IPLAudioBuffer} objects with the same format as specified here. The input format must not be
     *                     Ambisonics.
     * @param outputFormat The format of the audio buffers which will be used to retrieve the output from this effect.
     *                     All subsequent calls to {@link PanningEffectApi#iplApplyPanningEffect} for this effect
     *                     object
     *                     must
     *                     use {@link IPLAudioBuffer} objects with the same format as specified here. Any valid audio
     *                     format may be specified as the output format.
     * @param effect       [out] Handle to the created Panning Effect object.
     *
     * @return Status code indicating whether or not the operation succeeded.
     */
    IPLerror iplCreatePanningEffect(Pointer renderer, IPLAudioFormat.ByValue inputFormat,
                                    IPLAudioFormat.ByValue outputFormat, PointerByReference effect);

    /**
     * Destroys a Panning Effect object.
     *
     * @param effect [in, out] Address of a handle to the Panning Effect object to destroy.
     */
    void iplDestroyPanningEffect(PointerByReference effect);

    /**
     * Applies 3D panning to a buffer of audio data, using the configuration of a Panning Effect object. The input audio
     * is treated as emanating from a single point. If the input audio buffer contains more than one channel, it will
     * automatically be downmixed to mono.
     *
     * @param effect      Handle to a Panning Effect object.
     * @param inputAudio  Audio buffer containing the data to render using 3D panning. The format of this buffer must
     *                    match the {@code inputFormat} parameter passed to {@link PanningEffectApi#iplCreatePanningEffect}.
     * @param direction   Unit vector from the listener to the point source, relative to the listener's coordinate
     *                    system.
     * @param outputAudio Audio buffer that should contain the rendered audio data. The format of this buffer must
     *                    match the {@code outputFormat} parameter passed to {@link PanningEffectApi#iplCreatePanningEffect}.
     */
    void iplApplyPanningEffect(Pointer effect, IPLAudioBuffer.ByValue inputAudio, IPLVector3.ByValue direction,
                               IPLAudioBuffer.ByValue outputAudio);

}
