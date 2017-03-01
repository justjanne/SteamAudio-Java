package de.kuschku.steamaudio.lib.ambisonicspanning;


import de.kuschku.steamaudio.lib.error.SteamAudioException;
import de.kuschku.steamaudio.lib.util.*;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioBuffer;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioFormat;
import de.kuschku.steamaudio.lib.binauralrenderer.BinauralRenderer;

public class AmbisonicsPanningEffect extends PointerHandle implements AudioEffect {
    /**
     * Creates an Ambisonics Panning Effect object. This can be used to render higher-order Ambisonics data using
     * standard panning algorithms.
     *
     * @param renderer     Handle to a Binaural Renderer object.
     * @param inputFormat  The format of the audio buffers that will be passed as input to this effect. All subsequent
     *                     calls to {@link #applyEffect} for this effect object must use {@link IPLAudioBuffer} objects
     *                     with the same format as specified here. The input format must be Ambisonics.
     * @param outputFormat The format of the audio buffers which will be used to retrieve the output from this effect.
     *                     All subsequent calls to {@link #applyEffect} for this effect object must use {@link
     *                     IPLAudioBuffer} objects with the same format as specified here.
     *
     * @throws SteamAudioException Describes what kind of error happened in native code.
     */
    public AmbisonicsPanningEffect(BinauralRenderer renderer, IPLAudioFormat inputFormat, IPLAudioFormat outputFormat)
            throws SteamAudioException {
        super(SteamAudioBridge.ambisonicspanning::__iplCreateAmbisonicsPanningEffect, renderer, inputFormat,
                outputFormat);
        setOnDelete(SteamAudio.ambisonicspanning::iplDestroyAmbisonicsPanningEffect);
    }

    /**
     * Applies a panning-based rendering algorithm to a buffer of Ambisonics audio data. Ambisonics encoders and
     * decoders use many different conventions to store the multiple Ambisonics channels, as well as different
     * normalization schemes. Make sure that you correctly specify these settings when creating the Ambisonics Panning
     * Effect object, otherwise the rendered audio will be incorrect.
     *
     * @param inputAudio  Audio buffer containing the data to render. The format of this buffer must match the {@code
     *                    inputFormat} parameter passed to {@link #AmbisonicsPanningEffect}.
     * @param outputAudio Audio buffer that should contain the rendered audio data. The format of this buffer must match
     *                    the {@code outputFormat} parameter passed to {@link #AmbisonicsPanningEffect}.
     */
    @Override
    public void applyEffect(IPLAudioBuffer inputAudio, IPLAudioBuffer outputAudio) {
        SteamAudioBridge.ambisonicspanning.__iplApplyAmbisonicsPanningEffect(this, inputAudio, outputAudio);
    }
}
