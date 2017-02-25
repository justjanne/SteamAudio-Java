package de.kuschku.steamaudio.lib.conveffect;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioBuffer;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioFormat;
import de.kuschku.steamaudio.lib.geometry.IPLVector3;
import de.kuschku.steamaudio.lib.simsettings.IPLSimulationType;
import de.kuschku.steamaudio.lib.util.IPLerror;

import java.nio.ByteBuffer;

@SuppressWarnings("unused")
public interface ConvEffectApi extends Library {
    static ConvEffectApi getInstance() {
        return Native.loadLibrary("steamaudio", ConvEffectApi.class);
    }

    /**
     * Creates a Convolution Effect object.
     *
     * @param renderer       Handle to an Environmental Renderer object.
     * @param name           Name of the corresponding source, as defined in the baked data exported by the game
     *                       engine.
     *                       Each Convolution Effect object may have a name, which is used only if the Environment
     *                       object provided by the game engine uses baked data for sound propagation. If so, the name
     *                       of the Convolution Effect is used to look up the appropriate information from the baked
     *                       data. Multiple Convolution Effect objects may be created with the same name; in that case
     *                       they will use the same baked data. If you want this Convolution Effect to be used to
     *                       render
     *                       baked reverb, pass \c "__reverb__" as the name.
     * @param simulationType Whether this Convolution Effect object should use baked data or real-time simulation.
     * @param inputFormat    Format of all audio buffers passed as input to {@link ConvEffectApi#iplSetDryAudioForConvolutionEffect}.
     * @param outputFormat   Format of all output audio buffers passed to {@link ConvEffectApi#iplGetWetAudioForConvolutionEffect}.
     * @param effect         [out] Handle to the created Convolution Effect object.
     *
     * @return Status code indicating whether or not the operation succeeded.
     */
    IPLerror iplCreateConvolutionEffect(Pointer renderer, ByteBuffer name, IPLSimulationType simulationType,
                                        IPLAudioFormat.ByValue inputFormat, IPLAudioFormat.ByValue outputFormat,
                                        PointerByReference effect);

    /**
     * Destroys a Convolution Effect object.
     *
     * @param effect [in, out] Address of a handle to the Convolution Effect object to destroy.
     */
    void iplDestroyConvolutionEffect(PointerByReference effect);

    /**
     * Changes the name associated with a Convolution Effect object. This is useful when using a static listener bake,
     * where you may want to teleport the listener between two or more locations for which baked data has been
     * generated.
     *
     * @param effect Handle to a Convolution Effect object.
     * @param name   The new name of the Convolution Effect object.
     */
    void iplSetConvolutionEffectName(Pointer effect, ByteBuffer name);

    /**
     * Specifies a frame of dry audio for a Convolution Effect object. This is the audio data to which sound propagation
     * effects should be applied.
     *
     * @param effect         Handle to a Convolution Effect object.
     * @param sourcePosition World-space position of the sound source emitting the dry audio.
     * @param dryAudio       Audio buffer containing the dry audio data.
     */
    void iplSetDryAudioForConvolutionEffect(Pointer effect, IPLVector3.ByValue sourcePosition,
                                            IPLAudioBuffer.ByValue dryAudio);

    /**
     * Retrieves a frame of wet audio from a Convolution Effect object. This is the result of applying sound propagation
     * effects to the dry audio previously specified using {@link ConvEffectApi#iplSetDryAudioForConvolutionEffect}.
     *
     * @param effect           Handle to a Convolution Effect object.
     * @param listenerPosition World-space position of the listener.
     * @param listenerAhead    Unit vector in the direction in which the listener is looking.
     * @param listenerUp       Unit vector pointing upwards from the listener.
     * @param wetAudio         Audio buffer which will be populated with the wet audio data.
     */
    void iplGetWetAudioForConvolutionEffect(Pointer effect, IPLVector3.ByValue listenerPosition,
                                            IPLVector3.ByValue listenerAhead, IPLVector3.ByValue listenerUp,
                                            IPLAudioBuffer.ByValue wetAudio);

    /**
     * Retrieves a mixed frame of wet audio. This is the sum of all wet audio data from all Convolution Effect objects
     * that were created using the given Environmental Renderer object. Unless using TrueAudio Next for convolution,
     * this is likely to provide a significant performance boost to the audio thread as compared to calling {@link
     * ConvEffectApi#iplGetWetAudioForConvolutionEffect} for each Convolution Effect separately. On the other hand,
     * doing
     * so makes it impossible to apply additional DSP effects for specific sources before mixing.
     *
     * @param renderer         Handle to an Environmental Renderer object.
     * @param listenerPosition World-space position of the listener.
     * @param listenerAhead    Unit vector in the direction in which the listener is looking.
     * @param listenerUp       Unit vector pointing upwards from the listener.
     * @param mixedWetAudio    Audio buffer which will be populated with the wet audio data.
     */
    void iplGetMixedEnvironmentalAudio(Pointer renderer, IPLVector3.ByValue listenerPosition,
                                       IPLVector3.ByValue listenerAhead, IPLVector3.ByValue listenerUp,
                                       IPLAudioBuffer.ByValue mixedWetAudio);
}
