package de.kuschku.steamaudio.lib.binauralrenderer;

import de.kuschku.steamaudio.lib.SteamAudio;
import de.kuschku.steamaudio.lib.SteamAudioBridge;
import de.kuschku.steamaudio.lib.context.IPLContext;
import de.kuschku.steamaudio.lib.rendersettings.IPLRenderingSettings;
import de.kuschku.steamaudio.lib.util.ErrorUtil;
import de.kuschku.steamaudio.lib.util.PointerHandle;

import java.nio.ByteBuffer;

public class BinauralRenderer extends PointerHandle {
    /**
     * Creates a Binaural Renderer object. This function must be called before creating any Panning Effect objects,
     * Object-Based Binaural Effect object, Virtual Surround Effect objects, or Ambisonics Binaural Effect objects.
     * Calling this function for the first time is somewhat expensive; avoid creating Binaural Renderer objects in
     * your audio thread if at all possible.
     * <p>
     * <b>This function is not thread-safe. It cannot be simultaneously called from multiple threads.</b>
     *
     * @param context           The Context object used by the audio engine.
     * @param renderingSettings An {@link IPLRenderingSettings} object describing the audio pipeline's DSP processing
     *                          parameters. These properties must remain constant throughout the lifetime of your
     *                          application.
     */
    public BinauralRenderer(IPLContext context, IPLRenderingSettings renderingSettings)
            throws ErrorUtil.SteamAudioException {
        this(context, renderingSettings, null);
    }

    /**
     * Creates a Binaural Renderer object. This function must be called before creating any Panning Effect objects,
     * Object-Based Binaural Effect object, Virtual Surround Effect objects, or Ambisonics Binaural Effect objects.
     * Calling this function for the first time is somewhat expensive; avoid creating Binaural Renderer objects in
     * your audio thread if at all possible.
     * <p>
     * <b>This function is not thread-safe. It cannot be simultaneously called from multiple threads.</b>
     *
     * @param context           The Context object used by the audio engine.
     * @param renderingSettings An {@link IPLRenderingSettings} object describing the audio pipeline's DSP processing
     *                          parameters. These properties must remain constant throughout the lifetime of your
     *                          application.
     * @param hrtfData          Pointer to a byte array containing HRTF data. For most situations, set this parameter to
     *                          {@code NULL}; Phonon will use its built-in HRTF data. If you want to use customized or
     *                          personalized HRTF data, contact Impulsonic for further information.
     */
    public BinauralRenderer(IPLContext context, IPLRenderingSettings renderingSettings, ByteBuffer hrtfData)
            throws ErrorUtil.SteamAudioException {
        super(SteamAudioBridge.binauralrenderer::__iplCreateBinauralRenderer, context, renderingSettings, hrtfData);
        setOnDelete(SteamAudio.binauralrenderer::iplDestroyBinauralRenderer);
    }
}
