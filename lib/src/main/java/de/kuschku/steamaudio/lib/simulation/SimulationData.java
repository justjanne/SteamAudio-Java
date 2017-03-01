package de.kuschku.steamaudio.lib.simulation;

import de.kuschku.steamaudio.lib.error.SteamAudioException;
import de.kuschku.steamaudio.lib.util.*;
import de.kuschku.steamaudio.lib.environment.Environment;
import de.kuschku.steamaudio.lib.geometry.IPLVector3;
import de.kuschku.steamaudio.lib.rendersettings.IPLRenderingSettings;
import org.bridj.Pointer;

public class SimulationData extends PointerHandle {
    public SimulationData(IPLSimulationSettings simulationSettings, IPLRenderingSettings renderingSettings)
            throws SteamAudioException {
        super(SteamAudioBridge.simulation::__iplCreateSimulationData, simulationSettings, renderingSettings);
        setOnDelete(SteamAudio.simulation::iplDestroySimulationData);
    }

    public void generateSimulationData(Environment environment, IPLVector3 listenerPosition, IPLVector3 listenerAhead,
                                       IPLVector3 listenerUp, Pointer<IPLVector3> sources) {
        SteamAudioBridge.simulation
                .__iplGenerateSimulationData(this, environment, listenerPosition, listenerAhead, listenerUp, sources);
    }

    public void getSimulationResult(int sourceIndex, int channel, Pointer<Float> buffer) {
        SteamAudio.simulation.iplGetSimulationResult(this, sourceIndex, channel, buffer);
    }

    public int getNumIrSamples() {
        return SteamAudio.simulation.iplGetNumIrSamples(this);
    }

    public int getNumIrChannels() {
        return SteamAudio.simulation.iplGetNumIrChannels(this);
    }
}
