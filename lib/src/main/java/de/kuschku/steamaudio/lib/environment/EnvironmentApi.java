package de.kuschku.steamaudio.lib.environment;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import de.kuschku.steamaudio.lib.context.IPLContext;
import de.kuschku.steamaudio.lib.scene.SceneApi;
import de.kuschku.steamaudio.lib.simsettings.IPLSimulationSettings;
import de.kuschku.steamaudio.lib.util.IPLerror;

@SuppressWarnings("unused")
public interface EnvironmentApi extends Library {
    static EnvironmentApi getInstance() {
        return Native.loadLibrary("steamaudio", EnvironmentApi.class);
    }

    /**
     * Creates an Environment object. It is necessary to call this function even if you are not using the sound
     * propagation features of Phonon.
     *
     * @param context            The Context object used by the game engine.
     * @param computeDevice      Handle to a Compute Device object. Only required if using Radeon Rays for ray tracing,
     *                           or if using TrueAudio Next for convolution, may be {@code null} otherwise.
     * @param simulationSettings The settings to use for simulation. This must be the same settings passed to {@link
     *                           SceneApi#iplCreateScene} or {@link SceneApi#iplLoadFinalizedScene},
     *                           whichever was
     *                           used to create the Scene object passed in the {@code scene} parameter to this
     *                           function.
     * @param scene              The Scene object. If created using {@link SceneApi#iplCreateScene}, then {@link
     *                           SceneApi#iplFinalizeScene} must have been called on the Scene object before
     *                           passing
     *                           it to this function. May be {@code null}, in which case only direct sound will be
     *                           simulated, without occlusion or any other indirect sound propagation.
     * @param probeManager       The Probe Manager object. May be {@code null} if not using baked data.
     * @param environment        [out] Handle to the created Environment object.
     *
     * @return Status code indicating whether or not the operation succeeded.
     */
    IPLerror iplCreateEnvironment(IPLContext.ByValue context, Pointer computeDevice,
                                  IPLSimulationSettings.ByValue simulationSettings, Pointer scene, Pointer probeManager,
                                  PointerByReference environment);

    /**
     * Destroys an Environment object. If any other API objects are still referencing the Environment object, it will
     * not be destroyed; destruction occurs when the object's reference count reaches zero.
     *
     * @param environment [in, out] Address of a handle to the Environment object to destroy.
     */
    void iplDestroyEnvironment(PointerByReference environment);

    /**
     * Sets the number of bounces to use for real-time simulations that use an Environment object. Calling this function
     * overrides the value of {@link IPLSimulationSettings#numBounces} passed when calling {@link
     * EnvironmentApi#iplCreateEnvironment} to create this Environment object.
     *
     * @param environment Handle to an Environment object.
     * @param numBounces  The number of bounces to use for all subsequent simulations in the Environment.
     */
    void iplSetNumBounces(Pointer environment, int numBounces);
}
