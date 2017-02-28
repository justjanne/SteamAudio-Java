package de.kuschku.steamaudio.lib.binauraleffect;

import de.kuschku.steamaudio.lib.SteamAudio;
import de.kuschku.steamaudio.lib.SteamAudioBridge;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioBuffer;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioFormat;
import de.kuschku.steamaudio.lib.binauralrenderer.BinauralRenderer;
import de.kuschku.steamaudio.lib.geometry.IPLVector3;
import de.kuschku.steamaudio.lib.util.ErrorUtil;
import de.kuschku.steamaudio.lib.util.PointerHandle;

public class BinauralEffect extends PointerHandle {
    /**
     * Creates an Object-Based Binaural Effect object. This can be used to render a point source using HRTF-based
     * binaural rendering.
     *
     * @param renderer     Handle to a Binaural Renderer object.
     * @param inputFormat  The format of the audio buffers that will be passed as input to this effect. All subsequent
     *                     calls to {@link #applyEffect} for this effect object must use {@link IPLAudioBuffer} objects
     *                     with the same format as specified here. The input format must not be Ambisonics.
     * @param outputFormat The format of the audio buffers which will be used to retrieve the output from this effect.
     *                     All subsequent calls to {@link #applyEffect} for this effect object must use {@link
     *                     IPLAudioBuffer} objects with the same format as specified here. The output format must be
     *                     stereo (2 channels).
     *
     * @throws ErrorUtil.SteamAudioException Describes what kind of error happened in native code.
     */
    public BinauralEffect(BinauralRenderer renderer, IPLAudioFormat inputFormat, IPLAudioFormat outputFormat)
            throws ErrorUtil.SteamAudioException {
        super(SteamAudioBridge.binauraleffect::__iplCreateBinauralEffect, renderer, inputFormat, outputFormat);
        setOnDelete(SteamAudio.binauraleffect::iplDestroyBinauralEffect);
    }

    /**
     * Applies HRTF-based binaural rendering to a buffer of audio data. The input audio is treated as emanating from a
     * single point. If the input audio buffer contains more than one channel, it will automatically be downmixed to
     * mono. Using bilinear interpolation (by setting {@code interpolation} to {@link
     * IPLHrtfInterpolation#IPL_HRTFINTERPOLATION_BILINEAR}) can incur a relatively high CPU cost. Use it only on
     * sources where nearest-neighbor filtering ({@link IPLHrtfInterpolation#IPL_HRTFINTERPOLATION_NEAREST}) produces
     * suboptimal results. Typically, bilinear filtering is most useful for wide-band noise-like sounds, such as radio
     * static, mechanical noise, fire, etc.
     *
     * @param inputAudio    Audio buffer containing the data to render using binaural rendering. The format of this
     *                      buffer must match the {@code inputFormat} parameter passed to {@link #BinauralEffect}.
     * @param direction     Unit vector from the listener to the point source, relative to the listener's coordinate
     *                      system.
     * @param interpolation The interpolation technique to use when rendering a point source at a location that is not
     *                      contained in the measured HRTF data used by Phonon.
     * @param outputAudio   Audio buffer that should contain the rendered audio data. The format of this buffer must
     *                      match the {@code outputFormat} parameter passed to {@link #BinauralEffect}.
     */
    public void applyEffect(IPLAudioBuffer inputAudio, IPLVector3 direction, IPLHrtfInterpolation interpolation,
                            IPLAudioBuffer outputAudio) {
        SteamAudioBridge.binauraleffect
                .__iplApplyBinauralEffect(this, inputAudio, direction, interpolation, outputAudio);
    }
}
