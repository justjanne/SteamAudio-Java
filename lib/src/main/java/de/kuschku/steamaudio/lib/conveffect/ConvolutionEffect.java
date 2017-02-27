package de.kuschku.steamaudio.lib.conveffect;

import de.kuschku.steamaudio.lib.SteamAudio;
import de.kuschku.steamaudio.lib.SteamAudioBridge;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioBuffer;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioFormat;
import de.kuschku.steamaudio.lib.envrenderer.EnvironmentalRenderer;
import de.kuschku.steamaudio.lib.geometry.IPLVector3;
import de.kuschku.steamaudio.lib.simulation.IPLSimulationType;
import de.kuschku.steamaudio.lib.util.ErrorUtil;
import de.kuschku.steamaudio.lib.util.PointerHandle;

public class ConvolutionEffect extends PointerHandle {
    private final EnvironmentalRenderer renderer;

    public ConvolutionEffect(EnvironmentalRenderer renderer, String name, IPLSimulationType simulationType,
                             IPLAudioFormat inputFormat, IPLAudioFormat outputFormat)
            throws ErrorUtil.SteamAudioException {
        super(SteamAudioBridge.conveffect::__iplCreateConvolutionEffect, renderer, name, simulationType, inputFormat,
                outputFormat);

        this.renderer = renderer;

        setOnDelete(SteamAudio.conveffect::iplDestroyConvolutionEffect);
    }

    public void setName(String name) {
        SteamAudio.conveffect.iplSetConvolutionEffectName(this, name);
    }

    public void setDryAudio(IPLVector3 sourcePosition, IPLAudioBuffer dryAudio) {
        SteamAudioBridge.conveffect.__iplSetDryAudioForConvolutionEffect(this, sourcePosition, dryAudio);
    }

    public void getWetAudio(IPLVector3 listenerPosition, IPLVector3 listenerAhead, IPLVector3 listenerUp,
                            IPLAudioBuffer wetAudio) {
        SteamAudioBridge.conveffect
                .__iplGetWetAudioForConvolutionEffect(this, listenerPosition, listenerAhead, listenerUp, wetAudio);
    }

    public void getMixedEnvironmentalAudio(IPLVector3 listenerPosition, IPLVector3 listenerAhead, IPLVector3 listenerUp,
                                           IPLAudioBuffer mixedWetAudio) {
        SteamAudioBridge.conveffect
                .__iplGetMixedEnvironmentalAudio(renderer, listenerPosition, listenerAhead, listenerUp, mixedWetAudio);
    }
}
