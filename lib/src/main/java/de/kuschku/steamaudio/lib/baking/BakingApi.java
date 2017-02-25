package de.kuschku.steamaudio.lib.baking;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import de.kuschku.steamaudio.lib.geometry.IPLSphere;

@SuppressWarnings("unused")
public interface BakingApi extends Library {
    static BakingApi getInstance() {
        return Native.loadLibrary("steamaudio", BakingApi.class);
    }

    /**
     * Bakes reverb at all probes in a Probe Box. Phonon defines reverb as the indirect sound received at a probe when a
     * source is placed at the probe's location. This is a time-consuming operation, and should typically be called from
     * the game engine's editor.
     *
     * @param environment      Handle to an Environment object.
     * @param probeBox         Handle to the Probe Box containing the probes for which to bake reverb.
     * @param bakingSettings   The kind of acoustic responses to bake.
     * @param progressCallback Pointer to a function that reports the percentage of this function's work that has been
     *                         completed. May be {@code null}.
     */
    void iplBakeReverb(Pointer environment, Pointer probeBox, IPLBakingSettings.ByValue bakingSettings,
                       IPLBakeProgressCallback progressCallback);

    /**
     * Bakes propagation effects from a specified source to all probes in a Probe Box. Sources are defined in terms of a
     * position and a sphere of influence; all probes in the Probe Box that lie within the sphere of influence are
     * processed by this function. This is a time-consuming operation, and should typically be called from the game
     * engine's editor.
     *
     * @param environment      Handle to an Environment object.
     * @param probeBox         Handle to the Probe Box containing the probes for which to bake reverb.
     * @param sourceInfluence  Sphere defined by the source position (at its center) and its radius of influence.
     * @param sourceName       Name of the source. At run-time, a Convolution Effect object can use this name to look
     *                         up
     *                         the correct impulse response information.
     * @param bakingSettings   The kind of acoustic responses to bake.
     * @param progressCallback Pointer to a function that reports the percentage of this function's work that has been
     *                         completed. May be {@code null}.
     */
    void iplBakePropagation(Pointer environment, Pointer probeBox, IPLSphere.ByValue sourceInfluence, String sourceName,
                            IPLBakingSettings.ByValue bakingSettings, IPLBakeProgressCallback progressCallback);

    /**
     * Bakes propagation effects from all probes in a Probe Box to a specified listener. Listeners are defined solely by
     * their position; their orientation may freely change at run-time. This is a time-consuming operation, and should
     * typically be called from the game engine's editor.
     *
     * @param environment       Handle to an Environment object.
     * @param probeBox          Handle to the Probe Box containing the probes for which to bake reverb.
     * @param listenerInfluence Position and influence radius of the listener.
     * @param listenerName      Name of the listener. At run-time, a Convolution Effect object can use this name
     *                          prefixed with \c __staticlistener__ to look up the correct impulse response
     *                          information.
     * @param bakingSettings    The kind of acoustic responses to bake.
     * @param progressCallback  Pointer to a function that reports the percentage of this function's work that has been
     *                          completed. May be {@code null}.
     */
    void iplBakeStaticListener(Pointer environment, Pointer probeBox, IPLSphere.ByValue listenerInfluence,
                               String listenerName, IPLBakingSettings.ByValue bakingSettings,
                               IPLBakeProgressCallback progressCallback);

    /**
     * Cancels any bake operations that may be in progress. Typically, an application will call {@link
     * BakingApi#iplBakeReverb} or {@link BakingApi#iplBakePropagation} in a separate thread from the editor's GUI
     * thread, to keep the GUI responsive. This function can be called from the GUI thread to safely and prematurely
     * terminate execution of any of these functions.
     */
    void iplCancelBake();

    /**
     * Deletes all baked data in a Probe Box that is associated with a given source. If no such baked data exists, this
     * function does nothing.
     *
     * @param probeBox   Handle to a Probe Box object.
     * @param sourceName Name of the source whose baked data is to be deleted.
     */
    void iplDeleteBakedDataByName(Pointer probeBox, String sourceName);

    /**
     * Returns the size (in bytes) of the baked data stored in a Probe Box corresponding to a given source. This is
     * useful for displaying statistics in the editor's GUI.
     *
     * @param probeBox   Handle to a Probe Box object.
     * @param sourceName Name of the source whose baked data size is to be returned.
     *
     * @return Size (in bytes) of the baked data stored in the Probe Box corresponding to the named source.
     */
    int iplGetBakedDataSizeByName(Pointer probeBox, String sourceName);
}
