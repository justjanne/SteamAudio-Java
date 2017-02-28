package de.kuschku.steamaudio.lib.ambisonics;

import de.kuschku.steamaudio.lib.SteamAudio;
import de.kuschku.steamaudio.lib.SteamAudioBridge;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioBuffer;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioFormat;
import de.kuschku.steamaudio.lib.binauralrenderer.BinauralRenderer;
import de.kuschku.steamaudio.lib.util.AudioEffect;
import de.kuschku.steamaudio.lib.util.ErrorUtil;
import de.kuschku.steamaudio.lib.util.PointerHandle;

public class AmbisonicsBinauralEffect extends PointerHandle implements AudioEffect {
    /**
     * Creates an Ambisonics Binaural Effect object. This can be used to render higher-order Ambisonics data using
     * HRTF-based binaural rendering.
     *
     * @param renderer Handle to a Binaural Renderer object.  @param inputFormat  The format of the audio buffers that
     *                 will be passed as input to this effect. All subsequent  calls to {@link
     *                 SteamAudioBridge.ambisonics#__iplApplyAmbisonicsBinauralEffect} for this  effect object must use
     *                 {@link IPLAudioBuffer} objects with the same format as specified here.  The input format must be
     *                 Ambisonics.  @param outputFormat The format of the audio buffers which will be used to retrieve
     *                 the output from this effect.  All subsequent calls to {@link SteamAudioBridge.ambisonics#__iplApplyAmbisonicsBinauralEffect}
     *                 for this effect object must use {@link IPLAudioBuffer} objects with the same format as specified
     *                 here. The output format must be stereo (2 channels).
     */
    public AmbisonicsBinauralEffect(BinauralRenderer renderer, IPLAudioFormat inputFormat, IPLAudioFormat outputFormat)
            throws ErrorUtil.SteamAudioException {
        super(SteamAudioBridge.ambisonics::__iplCreateAmbisonicsBinauralEffect, renderer, inputFormat, outputFormat);
        setOnDelete(SteamAudio.ambisonics::iplDestroyAmbisonicsBinauralEffect);
    }

    /**
     * Applies HRTF-based binaural rendering to a buffer of Ambisonics audio data. Ambisonics encoders and decoders use
     * many different conventions to store the multiple Ambisonics channels, as well as different normalization schemes.
     * Make sure that you correctly specify these settings when creating the Ambisonics Binaural Effect object,
     * otherwise the rendered audio will be incorrect.
     *
     * @param inputAudio  Audio buffer containing the data to render using binaural rendering. The format of this buffer
     *                    must match the {@code inputFormat} parameter passed to {@link SteamAudioBridge.ambisonics#__iplCreateAmbisonicsBinauralEffect}.
     * @param outputAudio Audio buffer that should contain the rendered audio data. The format of this buffer must match
     *                    the {@code outputFormat} parameter passed to {@link SteamAudioBridge.ambisonics#__iplCreateAmbisonicsBinauralEffect}.
     */
    @Override
    public void applyEffect(IPLAudioBuffer inputAudio, IPLAudioBuffer outputAudio) {
        SteamAudioBridge.ambisonics.__iplApplyAmbisonicsBinauralEffect(this, inputAudio, outputAudio);
    }
}
