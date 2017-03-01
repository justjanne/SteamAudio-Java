package de.kuschku.steamaudio.lib.environment;

import de.kuschku.steamaudio.lib.error.SteamAudioException;
import de.kuschku.steamaudio.lib.util.*;
import de.kuschku.steamaudio.lib.compute.ComputeDevice;
import de.kuschku.steamaudio.lib.context.IPLContext;
import de.kuschku.steamaudio.lib.probes.ProbeManager;
import de.kuschku.steamaudio.lib.scene.IPLFinalizeSceneProgressCallback;
import de.kuschku.steamaudio.lib.scene.Scene;
import de.kuschku.steamaudio.lib.simulation.IPLSimulationSettings;

public class Environment extends PointerHandle {
    /**
     * Creates an Environment object. It is necessary to call this function even if you are not using the sound
     * propagation features of Phonon.
     *
     * @param context            The Context object used by the game engine.
     * @param computeDevice      Handle to a Compute Device object. Only required if using Radeon Rays for ray tracing,
     *                           or if using TrueAudio Next for convolution, may be {@code NULL} otherwise.
     * @param simulationSettings The settings to use for simulation. This must be the same settings passed to {@link
     *                           Scene#Scene}, whichever was used to create the Scene object passed in the {@code scene}
     *                           parameter to this function.
     * @param scene              The Scene object. Unless loaded from file, {@link Scene#finalize(IPLFinalizeSceneProgressCallback)}
     *                           must have been called on the Scene object before passing it to this function. May be
     *                           {@code NULL}, in which case only direct sound will be simulated, without occlusion or
     *                           any other indirect sound propagation.
     * @param probeManager       The Probe Manager object. May be {@code NULL} if not using baked data.
     *
     * @throws SteamAudioException Describes what kind of error happened in native code.
     */
    public Environment(IPLContext context, ComputeDevice computeDevice, IPLSimulationSettings simulationSettings,
                       Scene scene, ProbeManager probeManager) throws SteamAudioException {
        super(SteamAudioBridge.environment::__iplCreateEnvironment, context, computeDevice, simulationSettings, scene,
                probeManager);
        setOnDelete(SteamAudio.environment::iplDestroyEnvironment);
    }

    /**
     * Sets the number of bounces to use for real-time simulations that use an Environment object. Calling this
     * function overrides the value of {@code bounces} set on the {@link IPLSimulationSettings} structure passed when
     * calling {@link #Environment} to create this Environment object.
     *
     * @param numBounces The number of bounces to use for all subsequent simulations in the Environment.
     */
    public void setNumBounces(int numBounces) {
        SteamAudio.environment.iplSetNumBounces(this, numBounces);
    }
}
