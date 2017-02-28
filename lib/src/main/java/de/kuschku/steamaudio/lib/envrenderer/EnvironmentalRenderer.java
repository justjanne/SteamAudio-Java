package de.kuschku.steamaudio.lib.envrenderer;

import de.kuschku.steamaudio.lib.SteamAudio;
import de.kuschku.steamaudio.lib.SteamAudioBridge;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioFormat;
import de.kuschku.steamaudio.lib.context.IPLContext;
import de.kuschku.steamaudio.lib.conveffect.ConvolutionEffect;
import de.kuschku.steamaudio.lib.environment.Environment;
import de.kuschku.steamaudio.lib.rendersettings.IPLRenderingSettings;
import de.kuschku.steamaudio.lib.util.ErrorUtil;
import de.kuschku.steamaudio.lib.util.PointerHandle;

public class EnvironmentalRenderer extends PointerHandle {
    /**
     * Creates an Environmental Renderer object.
     *
     * @param context           The Context object used by the audio engine.
     * @param environment       Handle to an Environment object provided by the game engine. It is up to your
     *                          application to pass this handle from the game engine to the audio engine.
     * @param renderingSettings An {@link IPLRenderingSettings} object describing the audio pipeline's DSP processing
     *                          parameters. These properties must remain constant throughout the lifetime of your
     *                          application.
     * @param outputFormat      The audio format of the output buffers passed to any subsequent call to {@link
     *                          ConvolutionEffect#getMixedEnvironmentalAudio}. This format must not be changed once it
     *                          is set during the call to this function.
     */
    public EnvironmentalRenderer(IPLContext context, Environment environment, IPLRenderingSettings renderingSettings,
                                 IPLAudioFormat outputFormat) throws ErrorUtil.SteamAudioException {
        super(SteamAudioBridge.envrenderer::__iplCreateEnvironmentalRenderer, context, environment, renderingSettings,
                outputFormat);

        setOnDelete(SteamAudio.envrenderer::iplDestroyEnvironmentalRenderer);
    }
}
