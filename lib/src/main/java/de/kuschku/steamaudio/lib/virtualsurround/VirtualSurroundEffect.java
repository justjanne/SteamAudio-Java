package de.kuschku.steamaudio.lib.virtualsurround;

import de.kuschku.steamaudio.lib.SteamAudio;
import de.kuschku.steamaudio.lib.SteamAudioBridge;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioBuffer;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioFormat;
import de.kuschku.steamaudio.lib.binauralrenderer.BinauralRenderer;
import de.kuschku.steamaudio.lib.util.AudioEffect;
import de.kuschku.steamaudio.lib.util.ErrorUtil;
import de.kuschku.steamaudio.lib.util.PointerHandle;

public class VirtualSurroundEffect extends PointerHandle implements AudioEffect {
    public VirtualSurroundEffect(BinauralRenderer renderer, IPLAudioFormat inputFormat, IPLAudioFormat outputFormat)
            throws ErrorUtil.SteamAudioException {
        super(SteamAudioBridge.virtualsurround::__iplCreateVirtualSurroundEffect, renderer, inputFormat, outputFormat);
        setOnDelete(SteamAudio.virtualsurround::iplDestroyVirtualSurroundEffect);
    }


    @Override
    public void applyEffect(IPLAudioBuffer in, IPLAudioBuffer out) {
        SteamAudioBridge.virtualsurround.__iplApplyVirtualSurroundEffect(this, in, out);
    }
}
