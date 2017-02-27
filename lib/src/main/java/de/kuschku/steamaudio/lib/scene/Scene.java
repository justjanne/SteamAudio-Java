package de.kuschku.steamaudio.lib.scene;

import de.kuschku.steamaudio.lib.SteamAudio;
import de.kuschku.steamaudio.lib.SteamAudioBridge;
import de.kuschku.steamaudio.lib.compute.ComputeDevice;
import de.kuschku.steamaudio.lib.context.IPLContext;
import de.kuschku.steamaudio.lib.simulation.IPLSimulationSettings;
import de.kuschku.steamaudio.lib.util.ErrorUtil;
import de.kuschku.steamaudio.lib.util.PointerHandle;
import org.bridj.Pointer;

import java.util.function.Consumer;

public class Scene extends PointerHandle {
    public Scene(IPLContext context, IPLSimulationSettings simulationSettings, String fileName,
                 ComputeDevice computeDevice, IPLLoadSceneProgressCallback progressCallback)
            throws ErrorUtil.SteamAudioException {
        super(SteamAudioBridge.scene::__iplLoadFinalizedScene, context, simulationSettings, fileName, computeDevice,
                progressCallback);
        setOnDelete(SteamAudio.scene::iplDestroyScene);
    }

    public Scene(IPLContext context, ComputeDevice computeDevice, IPLSimulationSettings simulationSettings,
                 int numMaterials) throws ErrorUtil.SteamAudioException {
        super(SteamAudioBridge.scene::__iplCreateScene, context, computeDevice, simulationSettings, numMaterials);
        setOnDelete(SteamAudio.scene::iplDestroyScene);
    }

    public void setSceneMaterial(int materialIndex, IPLMaterial material) {
        SteamAudioBridge.scene.__iplSetSceneMaterial(this, materialIndex, material);
    }

    public void setRaytracerCallbacks(IPLClosestHitCallback closestHitCallback, IPLAnyHitCallback anyHitCallback,
                                      Pointer<?> userData) {
        SteamAudio.scene.iplSetRayTracerCallbacks(this, closestHitCallback, anyHitCallback, userData);
    }

    public void finalize(Consumer<Float> callback) {
        finalize(new IPLFinalizeSceneProgressCallback() {
            @Override
            public void apply(float progress) {
                callback.accept(progress);
            }
        });
    }

    public void finalize(IPLFinalizeSceneProgressCallback callback) {
        SteamAudio.scene.iplFinalizeScene(this, callback);
    }

    public void save(String filename) {
        SteamAudio.scene.iplSaveFinalizedScene(this, filename);
    }

    public void dumpToObjFile(String filename) {
        SteamAudio.scene.iplDumpSceneToObjFile(this, filename);
    }
}
