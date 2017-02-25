package de.kuschku.steamaudio.lib.binauralrenderer;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;
import de.kuschku.steamaudio.lib.context.IPLContext;
import de.kuschku.steamaudio.lib.rendersettings.IPLRenderingSettings;
import de.kuschku.steamaudio.lib.util.IPLerror;

import java.nio.ByteBuffer;

@SuppressWarnings("unused")
public interface BinauralRendererApi extends Library {
    static BinauralRendererApi getInstance() {
        return Native.loadLibrary("steamaudio", BinauralRendererApi.class);
    }

    /**
     * Creates a Binaural Renderer object. This function must be called before creating any Panning Effect objects,
     * Object-Based Binaural Effect object, Virtual Surround Effect objects, or Ambisonics Binaural Effect objects.
     * Calling this function for the first time is somewhat expensive; avoid creating Binaural Renderer objects in your
     * audio thread if at all possible. **This function is not thread-safe. It cannot be simultaneously called from
     * multiple threads.**
     *
     * @param context           The Context object used by the audio engine.
     * @param renderingSettings An {@link IPLRenderingSettings} object describing the audio pipeline's DSP processing
     *                          parameters. These properties must remain constant throughout the lifetime of your
     *                          application.
     * @param hrtfData          Pointer to a byte array containing HRTF data. For most situations, set this parameter
     *                          to {@code null}; Phonon will use its built-in HRTF data. If you want to use customized
     *                          or personalized HRTF data, contact Impulsonic for further information.
     * @param renderer          [out] Handle to the created Binaural Renderer object.
     *
     * @return Status code indicating whether or not the operation succeeded.
     */
    IPLerror iplCreateBinauralRenderer(IPLContext.ByValue context, IPLRenderingSettings.ByValue renderingSettings,
                                       ByteBuffer hrtfData, PointerByReference renderer);

    /**
     * Destroys a Binaural Renderer object. If any other API objects are still referencing the Binaural Renderer object,
     * it will not be destroyed; destruction occurs when the object's reference count reaches zero.
     *
     * @param renderer [in, out] Address of a handle to the Binaural Renderer object to destroy.
     */
    void iplDestroyBinauralRenderer(PointerByReference renderer);
}
