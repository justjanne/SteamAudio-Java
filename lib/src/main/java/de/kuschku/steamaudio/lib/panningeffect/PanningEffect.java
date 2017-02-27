package de.kuschku.steamaudio.lib.panningeffect;

import de.kuschku.steamaudio.lib.SteamAudio;
import de.kuschku.steamaudio.lib.SteamAudioBridge;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioBuffer;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioFormat;
import de.kuschku.steamaudio.lib.binauralrenderer.BinauralRenderer;
import de.kuschku.steamaudio.lib.geometry.IPLVector3;
import de.kuschku.steamaudio.lib.util.ErrorUtil;
import de.kuschku.steamaudio.lib.util.PointerHandle;

public class PanningEffect extends PointerHandle {
    public PanningEffect(BinauralRenderer renderer, IPLAudioFormat inputFormat, IPLAudioFormat outputFormat)
            throws ErrorUtil.SteamAudioException {
        super(SteamAudioBridge.panningeffect::__iplCreatePanningEffect, renderer, inputFormat, outputFormat);
        setOnDelete(SteamAudio.panningeffect::iplDestroyPanningEffect);
    }

    public void applyEffect(IPLAudioBuffer in, IPLVector3 direction, IPLAudioBuffer out) {
        SteamAudioBridge.panningeffect.__iplApplyPanningEffect(this, in, direction, out);
    }
}
