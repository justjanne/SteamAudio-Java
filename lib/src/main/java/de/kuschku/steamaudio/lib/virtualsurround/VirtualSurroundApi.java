package de.kuschku.steamaudio.lib.virtualsurround;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioBuffer;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioFormat;
import de.kuschku.steamaudio.lib.util.IPLerror;

@SuppressWarnings("unused")
public interface VirtualSurroundApi extends Library {
    static VirtualSurroundApi getInstance() {
        return Native.loadLibrary("steamaudio", VirtualSurroundApi.class);
    }

    /**
     * Creates a Virtual Surround Effect object. This can be used to render a multichannel surround sound data using
     * HRTF-based binaural rendering.
     *
     * @param renderer     Handle to a Binaural Renderer object.
     * @param inputFormat  The format of the audio buffers that will be passed as input to this effect. All subsequent
     *                     calls to {@link VirtualSurroundApi#iplApplyVirtualSurroundEffect} for this effect object
     *                     must
     *                     use
     *                     {@link IPLAudioBuffer} objects with the same format as specified here. The input format must
     *                     not be Ambisonics.
     * @param outputFormat The format of the audio buffers which will be used to retrieve the output from this effect.
     *                     All subsequent calls to {@link VirtualSurroundApi#iplApplyVirtualSurroundEffect} for this
     *                     effect
     *                     object must use {@link IPLAudioBuffer} objects with the same format as specified here. The
     *                     output format must be stereo (2 channels).
     * @param effect       [out] Handle to the created Virtual Surround Effect object.
     *
     * @return Status code indicating whether or not the operation succeeded.
     */
    IPLerror iplCreateVirtualSurroundEffect(Pointer renderer, IPLAudioFormat.ByValue inputFormat,
                                            IPLAudioFormat.ByValue outputFormat, PointerByReference effect);

    /**
     * Destroys a Virtual Surround Effect object.
     *
     * @param effect [in, out] Address of a handle to the Virtual Surround Effect object to destroy.
     */
    void iplDestroyVirtualSurroundEffect(PointerByReference effect);

    /**
     * Applies HRTF-based binaural rendering to a buffer of multichannel audio data.
     *
     * @param effect      Handle to a Virtual Surround Effect.
     * @param inputAudio  Audio buffer containing the data to render using binaural rendering. The format of this
     *                    buffer
     *                    must match the {@code inputFormat} parameter passed to {@link VirtualSurroundApi#iplCreateVirtualSurroundEffect}.
     * @param outputAudio Audio buffer that should contain the rendered audio data. The format of this buffer must
     *                    match
     *                    the {@code outputFormat} parameter passed to {@link VirtualSurroundApi#iplCreateVirtualSurroundEffect}.
     */
    void iplApplyVirtualSurroundEffect(Pointer effect, IPLAudioBuffer.ByValue inputAudio,
                                       IPLAudioBuffer.ByValue outputAudio);
}
