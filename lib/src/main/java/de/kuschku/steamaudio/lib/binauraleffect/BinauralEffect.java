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
    public BinauralEffect(BinauralRenderer renderer, IPLAudioFormat inputFormat, IPLAudioFormat outputFormat)
            throws ErrorUtil.SteamAudioException {
        super(SteamAudioBridge.binauraleffect::__iplCreateBinauralEffect, renderer, inputFormat, outputFormat);
        setOnDelete(SteamAudio.binauraleffect::iplDestroyBinauralEffect);
    }

    public void applyEffect(IPLAudioBuffer inputAudio, IPLVector3 direction, IPLHrtfInterpolation interpolation,
                            IPLAudioBuffer outputAudio) {
        SteamAudioBridge.binauraleffect
                .__iplApplyBinauralEffect(this, inputAudio, direction, interpolation, outputAudio);
    }
}
