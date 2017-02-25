package de.kuschku.steamaudio.lib.envrenderer;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioFormat;
import de.kuschku.steamaudio.lib.context.IPLContext;
import de.kuschku.steamaudio.lib.conveffect.ConvEffectApi;
import de.kuschku.steamaudio.lib.rendersettings.IPLRenderingSettings;
import de.kuschku.steamaudio.lib.util.IPLerror;

@SuppressWarnings("unused")
public interface EnvRendererApi extends Library {
    static EnvRendererApi getInstance() {
        return Native.loadLibrary("steamaudio", EnvRendererApi.class);
    }

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
     *                          ConvEffectApi#iplGetMixedEnvironmentalAudio}. This format must not be changed once it
     *                          is
     *                          set during the call to this function.
     * @param renderer          [out] Handle to the created Environmental Renderer object.
     *
     * @return Status code indicating whether or not the operation succeeded.
     */
    IPLerror iplCreateEnvironmentalRenderer(IPLContext.ByValue context, Pointer environment,
                                            IPLRenderingSettings.ByValue renderingSettings,
                                            IPLAudioFormat.ByValue outputFormat, PointerByReference renderer);

    /**
     * Destroys an Environmental Renderer object. If any other API objects are still referencing the Environmental
     * Renderer object, the object will not be destroyed; it will only be destroyed once its reference count reaches
     * zero.
     *
     * @param renderer [in, out] Address of a handle to the Environmental Renderer object to destroy.
     */
    void iplDestroyEnvironmentalRenderer(PointerByReference renderer);
}
