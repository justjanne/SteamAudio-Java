package de.kuschku.steamaudio.lib;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import de.kuschku.steamaudio.lib.geometry.IPLVector3;
import de.kuschku.steamaudio.lib.rendersettings.IPLRenderingSettings;
import de.kuschku.steamaudio.lib.simsettings.IPLSimulationSettings;

import java.nio.FloatBuffer;


@SuppressWarnings("unused")
public interface SteamAudio extends Library {
    static SteamAudio getInstance() {
        return Native.loadLibrary("steamaudio", SteamAudio.class);
    }

    int iplCreateSimulationData(IPLSimulationSettings.ByValue simulationSettings,
                                IPLRenderingSettings.ByValue renderingSettings, PointerByReference simulationData);

    void iplDestroySimulationData(PointerByReference simulationData);

    int iplGetNumIrSamples(Pointer simulationData);

    int iplGetNumIrChannels(Pointer simulationData);

    void iplGenerateSimulationData(Pointer simulationData, Pointer environment, IPLVector3.ByValue listenerPosition,
                                   IPLVector3.ByValue listenerAhead, IPLVector3.ByValue listenerUp, IPLVector3 sources);

    void iplGetSimulationResult(Pointer simulationData, int sourceIndex, int channel, FloatBuffer buffer);
}
