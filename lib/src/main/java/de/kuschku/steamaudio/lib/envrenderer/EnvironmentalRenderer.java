package de.kuschku.steamaudio.lib.envrenderer;

import de.kuschku.steamaudio.lib.SteamAudio;
import de.kuschku.steamaudio.lib.SteamAudioBridge;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioFormat;
import de.kuschku.steamaudio.lib.context.IPLContext;
import de.kuschku.steamaudio.lib.environment.Environment;
import de.kuschku.steamaudio.lib.rendersettings.IPLRenderingSettings;
import de.kuschku.steamaudio.lib.util.ErrorUtil;
import de.kuschku.steamaudio.lib.util.PointerHandle;

public class EnvironmentalRenderer extends PointerHandle {
    public EnvironmentalRenderer(IPLContext context, Environment environment, IPLRenderingSettings renderingSettings,
                                 IPLAudioFormat outputFormat) throws ErrorUtil.SteamAudioException {
        super(SteamAudioBridge.envrenderer::__iplCreateEnvironmentalRenderer, context, environment, renderingSettings,
                outputFormat);

        setOnDelete(SteamAudio.envrenderer::iplDestroyEnvironmentalRenderer);
    }
}
