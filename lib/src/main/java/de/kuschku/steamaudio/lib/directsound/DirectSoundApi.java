package de.kuschku.steamaudio.lib.directsound;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import de.kuschku.steamaudio.lib.geometry.IPLVector3;

@SuppressWarnings("unused")
public interface DirectSoundApi extends Library {
    static DirectSoundApi getInstance() {
        return Native.loadLibrary("steamaudio", DirectSoundApi.class);
    }

    /**
     * Calculates direct sound path parameters for a single source. It is up to the audio engine to perform audio
     * processing that uses the information returned by this function.
     *
     * @param renderer         Handle to an Environmental Renderer object.
     * @param listenerPosition World-space position of the listener.
     * @param listenerAhead    Unit vector pointing in the direction in which the listener is looking.
     * @param listenerUp       Unit vector pointing upwards from the listener.
     * @param sourcePosition   World-space position of the source.
     * @param sourceRadius     Radius of the sphere defined around the source, for use with {@link
     *                         IPLDirectOcclusionMethod.Enum#IPL_DIRECTOCCLUSION_VOLUMETRIC} only.
     * @param occlusionMethod  Algorithm to use for checking for direct path occlusion.
     *
     * @return Parameters of the direct path from the source to the listener.
     */
    IPLDirectSoundPath.ByValue iplGetDirectSoundPath(Pointer renderer, IPLVector3.ByValue listenerPosition,
                                                     IPLVector3.ByValue listenerAhead, IPLVector3.ByValue listenerUp,
                                                     IPLVector3.ByValue sourcePosition, float sourceRadius,
                                                     IPLDirectOcclusionMethod occlusionMethod);
}
