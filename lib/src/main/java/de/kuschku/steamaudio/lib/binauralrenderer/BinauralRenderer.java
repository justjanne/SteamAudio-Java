package de.kuschku.steamaudio.lib.binauralrenderer;

import de.kuschku.steamaudio.lib.SteamAudio;
import de.kuschku.steamaudio.lib.SteamAudioBridge;
import de.kuschku.steamaudio.lib.context.IPLContext;
import de.kuschku.steamaudio.lib.rendersettings.IPLRenderingSettings;
import de.kuschku.steamaudio.lib.util.ErrorUtil;
import de.kuschku.steamaudio.lib.util.PointerHandle;

import java.nio.ByteBuffer;

public class BinauralRenderer extends PointerHandle {
    public BinauralRenderer(IPLContext context, IPLRenderingSettings renderingSettings)
            throws ErrorUtil.SteamAudioException {
        this(context, renderingSettings, null);
    }

    public BinauralRenderer(IPLContext context, IPLRenderingSettings renderingSettings, ByteBuffer hrtfdata)
            throws ErrorUtil.SteamAudioException {
        super(SteamAudioBridge.binauralrenderer::__iplCreateBinauralRenderer, context, renderingSettings, hrtfdata);
        setOnDelete(SteamAudio.binauralrenderer::iplDestroyBinauralRenderer);
    }
}
