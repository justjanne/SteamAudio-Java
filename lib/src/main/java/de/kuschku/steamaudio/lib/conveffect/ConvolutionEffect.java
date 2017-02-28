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

    /**
     * Creates a Convolution Effect object.
     *
     * @param renderer       Handle to an Environmental Renderer object.
     * @param name           Name of the corresponding source, as defined in the baked data exported by the game engine.
     *                       Each Convolution Effect object may have a name, which is used only if the Environment
     *                       object provided by the game engine uses baked data for sound propagation. If so, the name
     *                       of the Convolution Effect is used to look up the appropriate information from the baked
     *                       data. Multiple Convolution Effect objects may be created with the same name; in that case
     *                       they will use the same baked data. If you want this Convolution Effect to be used to render
     *                       baked reverb, pass {@code "__reverb__"} as the name.
     * @param simulationType Whether this Convolution Effect object should use baked data or real-time simulation.
     * @param inputFormat    Format of all audio buffers passed as input to {@link #setDryAudio}.
     * @param outputFormat   Format of all output audio buffers passed to {@link #getWetAudio}.
     *
     * @throws ErrorUtil.SteamAudioException Describes what kind of error happened in native code.
     */
    public ConvolutionEffect(EnvironmentalRenderer renderer, String name, IPLSimulationType simulationType,
                             IPLAudioFormat inputFormat, IPLAudioFormat outputFormat)
            throws ErrorUtil.SteamAudioException {
        super(SteamAudioBridge.conveffect::__iplCreateConvolutionEffect, renderer, name, simulationType, inputFormat,
                outputFormat);

        this.renderer = renderer;

        setOnDelete(SteamAudio.conveffect::iplDestroyConvolutionEffect);
    }

    /**
     * Changes the name associated with a Convolution Effect object. This is useful when using a static listener bake,
     * where you may want to teleport the listener between two or more locations for which baked data has been
     * generated.
     *
     * @param name The new name of the Convolution Effect object.
     */
    public void setName(String name) {
        SteamAudio.conveffect.iplSetConvolutionEffectName(this, name);
    }

    /**
     * Specifies a frame of dry audio for a Convolution Effect object. This is the audio data to which sound propagation
     * effects should be applied.
     *
     * @param sourcePosition World-space position of the sound source emitting the dry audio.
     * @param dryAudio       Audio buffer containing the dry audio data.
     */
    public void setDryAudio(IPLVector3 sourcePosition, IPLAudioBuffer dryAudio) {
        SteamAudioBridge.conveffect.__iplSetDryAudioForConvolutionEffect(this, sourcePosition, dryAudio);
    }

    /**
     * Retrieves a frame of wet audio from a Convolution Effect object. This is the result of applying sound propagation
     * effects to the dry audio previously specified using {@link #setDryAudio}.
     *
     * @param listenerPosition World-space position of the listener.
     * @param listenerAhead    Unit vector in the direction in which the listener is looking.
     * @param listenerUp       Unit vector pointing upwards from the listener.
     * @param wetAudio         Audio buffer which will be populated with the wet audio data.
     */
    public void getWetAudio(IPLVector3 listenerPosition, IPLVector3 listenerAhead, IPLVector3 listenerUp,
                            IPLAudioBuffer wetAudio) {
        SteamAudioBridge.conveffect
                .__iplGetWetAudioForConvolutionEffect(this, listenerPosition, listenerAhead, listenerUp, wetAudio);
    }

    /**
     * Retrieves a mixed frame of wet audio. This is the sum of all wet audio data from all Convolution Effect objects
     * that were created using the given Environmental Renderer object. Unless using TrueAudio Next for convolution,
     * this is likely to provide a significant performance boost to the audio thread as compared to calling {@link
     * #getWetAudio} for each Convolution Effect separately. On the other hand, doing so makes it impossible to apply
     * additional DSP effects for specific sources before mixing.
     *
     * @param listenerPosition World-space position of the listener.
     * @param listenerAhead    Unit vector in the direction in which the listener is looking.
     * @param listenerUp       Unit vector pointing upwards from the listener.
     * @param mixedWetAudio    Audio buffer which will be populated with the wet audio data.
     */
    public void getMixedEnvironmentalAudio(IPLVector3 listenerPosition, IPLVector3 listenerAhead, IPLVector3 listenerUp,
                                           IPLAudioBuffer mixedWetAudio) {
        SteamAudioBridge.conveffect
                .__iplGetMixedEnvironmentalAudio(renderer, listenerPosition, listenerAhead, listenerUp, mixedWetAudio);
    }
}
