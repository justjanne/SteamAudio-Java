package de.kuschku.steamaudio.lib.environment;

import de.kuschku.steamaudio.lib.SteamAudio;
import de.kuschku.steamaudio.lib.SteamAudioBridge;
import de.kuschku.steamaudio.lib.compute.ComputeDevice;
import de.kuschku.steamaudio.lib.context.IPLContext;
import de.kuschku.steamaudio.lib.probes.ProbeManager;
import de.kuschku.steamaudio.lib.scene.Scene;
import de.kuschku.steamaudio.lib.simulation.IPLSimulationSettings;
import de.kuschku.steamaudio.lib.util.ErrorUtil;
import de.kuschku.steamaudio.lib.util.PointerHandle;

public class Environment extends PointerHandle {
    public Environment(IPLContext context, ComputeDevice computeDevice, IPLSimulationSettings simulationSettings,
                       Scene scene, ProbeManager probeManager) throws ErrorUtil.SteamAudioException {
        super(SteamAudioBridge.environment::__iplCreateEnvironment, context, computeDevice, simulationSettings, scene,
                probeManager);
        setOnDelete(SteamAudio.environment::iplDestroyEnvironment);
    }

    public void setNumBounces(int numBounces) {
        SteamAudio.environment.iplSetNumBounces(this, numBounces);
    }
}
