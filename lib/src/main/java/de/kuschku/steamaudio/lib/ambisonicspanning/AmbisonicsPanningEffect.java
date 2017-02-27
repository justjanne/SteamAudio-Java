package de.kuschku.steamaudio.lib.ambisonicspanning;


import de.kuschku.steamaudio.lib.SteamAudio;
import de.kuschku.steamaudio.lib.SteamAudioBridge;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioBuffer;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioFormat;
import de.kuschku.steamaudio.lib.binauralrenderer.BinauralRenderer;
import de.kuschku.steamaudio.lib.util.AudioEffect;
import de.kuschku.steamaudio.lib.util.ErrorUtil;
import de.kuschku.steamaudio.lib.util.PointerHandle;

public class AmbisonicsPanningEffect extends PointerHandle implements AudioEffect {
    public AmbisonicsPanningEffect(BinauralRenderer renderer, IPLAudioFormat inputFormat, IPLAudioFormat outputFormat)
            throws ErrorUtil.SteamAudioException {
        super(SteamAudioBridge.ambisonicspanning::__iplCreateAmbisonicsPanningEffect, renderer, inputFormat,
                outputFormat);
        setOnDelete(SteamAudio.ambisonicspanning::iplDestroyAmbisonicsPanningEffect);
    }

    @Override
    public void applyEffect(IPLAudioBuffer in, IPLAudioBuffer out) {
        SteamAudioBridge.ambisonicspanning.__iplApplyAmbisonicsPanningEffect(this, in, out);
    }
}
