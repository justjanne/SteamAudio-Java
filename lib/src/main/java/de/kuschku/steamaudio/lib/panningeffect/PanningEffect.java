package de.kuschku.steamaudio.lib.panningeffect;

import de.kuschku.steamaudio.lib.error.SteamAudioException;
import de.kuschku.steamaudio.lib.util.*;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioBuffer;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioFormat;
import de.kuschku.steamaudio.lib.binauralrenderer.BinauralRenderer;
import de.kuschku.steamaudio.lib.geometry.IPLVector3;

public class PanningEffect extends PointerHandle {
    /**
     * Creates a Panning Effect object. This can be used to render a point source on surround speakers, or using
     * Ambisonics.
     *
     * @param renderer     Handle to a Binaural Renderer object.
     * @param inputFormat  The format of the audio buffers that will be passed as input to this effect. All subsequent
     *                     calls to {@link #applyEffect} for this effect object must use {@link IPLAudioBuffer} objects
     *                     with the same format as specified here. The input format must not be Ambisonics.
     * @param outputFormat The format of the audio buffers which will be used to retrieve the output from this effect.
     *                     All subsequent calls to {@link #applyEffect} for this effect object must use {@link
     *                     IPLAudioBuffer} objects with the same f
     *
     * @throws SteamAudioException Describes what kind of error happened in native code.ormat as specified here. Any valid audio format may be
     *                     specified as the output format.
     */
    public PanningEffect(BinauralRenderer renderer, IPLAudioFormat inputFormat, IPLAudioFormat outputFormat)
            throws SteamAudioException {
        super(SteamAudioBridge.panningeffect::__iplCreatePanningEffect, renderer, inputFormat, outputFormat);
        setOnDelete(SteamAudio.panningeffect::iplDestroyPanningEffect);
    }

    /**
     * Applies 3D panning to a buffer of audio data, using the configuration of a Panning Effect object. The input
     * audio is treated as emanating from a single point. If the input audio buffer contains more than one channel,
     * it will automatically be downmixed to mono.
     *
     * @param inputAudio  Audio buffer containing the data to render using 3D panning. The format of this buffer must
     *                    match the {@code inputFormat} parameter passed to {@link #PanningEffect}.
     * @param direction   Unit vector from the listener to the point source, relative to the listener's coordinate
     *                    system.
     * @param outputAudio Audio buffer that should contain the rendered audio data. The format of this buffer must match
     *                    the {@code outputFormat} parameter passed to {@link #PanningEffect}.
     */
    public void applyEffect(IPLAudioBuffer inputAudio, IPLVector3 direction, IPLAudioBuffer outputAudio) {
        SteamAudioBridge.panningeffect.__iplApplyPanningEffect(this, inputAudio, direction, outputAudio);
    }
}
