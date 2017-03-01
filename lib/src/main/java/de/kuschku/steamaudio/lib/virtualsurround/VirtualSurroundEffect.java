package de.kuschku.steamaudio.lib.virtualsurround;

import de.kuschku.steamaudio.lib.error.SteamAudioException;
import de.kuschku.steamaudio.lib.util.*;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioBuffer;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioFormat;
import de.kuschku.steamaudio.lib.binauralrenderer.BinauralRenderer;

public class VirtualSurroundEffect extends PointerHandle implements AudioEffect {
    /**
     * Creates a Virtual Surround Effect object. This can be used to render a multichannel surround sound data using
     * HRTF-based binaural rendering.
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
     * @throws SteamAudioException Describes what kind of error happened in native code.
     */
    public VirtualSurroundEffect(BinauralRenderer renderer, IPLAudioFormat inputFormat, IPLAudioFormat outputFormat)
            throws SteamAudioException {
        super(SteamAudioBridge.virtualsurround::__iplCreateVirtualSurroundEffect, renderer, inputFormat, outputFormat);
        setOnDelete(SteamAudio.virtualsurround::iplDestroyVirtualSurroundEffect);
    }


    /**
     * Applies HRTF-based binaural rendering to a buffer of multichannel audio data.
     *
     * @param inputAudio  Audio buffer containing the data to render using binaural rendering. The format of this buffer
     *                    must match the {@code inputFormat} parameter passed to {@link #VirtualSurroundEffect}.
     * @param outputAudio Audio buffer that should contain the rendered audio data. The format of this buffer must match
     *                    the {@code outputFormat} parameter passed to {@link #VirtualSurroundEffect}.
     */
    public void applyEffect(IPLAudioBuffer inputAudio, IPLAudioBuffer outputAudio) {
        SteamAudioBridge.virtualsurround.__iplApplyVirtualSurroundEffect(this, inputAudio, outputAudio);
    }
}
