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
    /**
     * Creates a Scene object based on data stored in a file on disk. After this function is called, it is not necessary
     * to call {@link #finalize} on the resulting Scene object.
     *
     * @param context            The Context object used by the game engine.
     * @param simulationSettings The settings to use for the simulation. This must exactly match the settings that were
     *                           used to create the original Scene object that was passed to {@link #save}, except for
     *                           the {@code sceneType} and {@code simulationType} data members. This allows you to use
     *                           the same file to create a Scene object that uses any ray tracer you prefer.
     * @param fileName           Absolute or relative path to the file from which to load the Scene object.
     * @param computeDevice      Handle to a Compute Device object. Only required if using Radeon Rays for ray tracing,
     *                           may be {@code NULL} otherwise.
     * @param progressCallback   Pointer to a function that reports the percentage of this function's work that has been
     *                           completed. May be {@code NULL}.
     */
    public Scene(IPLContext context, IPLSimulationSettings simulationSettings, String fileName,
                 ComputeDevice computeDevice, IPLLoadSceneProgressCallback progressCallback)
            throws ErrorUtil.SteamAudioException {
        super(SteamAudioBridge.scene::__iplLoadFinalizedScene, context, simulationSettings, fileName, computeDevice,
                progressCallback);
        setOnDelete(SteamAudio.scene::iplDestroyScene);
    }

    /**
     * Creates a Scene object. A Scene object does not store any geometry information on its own; for that you need to
     * create one or more Static Mesh objects and add them to the Scene object. The Scene object does contain an array
     * of materials; all triangles in all Static Mesh objects refer to this array in order to specify their material
     * properties.
     *
     * @param context            The Context object used by the game engine.
     * @param computeDevice      Handle to a Compute Device object. Only required if using Radeon Rays for ray tracing,
     *                           may be {@code NULL} otherwise.
     * @param simulationSettings The settings to use for simulation.
     * @param numMaterials       The number of materials that are used to describe the various surfaces in the scene.
     *                           Materials may not be added or removed once the Scene object is created.
     */
    public Scene(IPLContext context, ComputeDevice computeDevice, IPLSimulationSettings simulationSettings,
                 int numMaterials) throws ErrorUtil.SteamAudioException {
        super(SteamAudioBridge.scene::__iplCreateScene, context, computeDevice, simulationSettings, numMaterials);
        setOnDelete(SteamAudio.scene::iplDestroyScene);
    }

    /**
     * Specifies a single material used by a Scene object. All materials must be completely specified before simulation
     * occurs, otherwise simulation results will be incorrect.
     *
     * @param materialIndex Index of the material to set. Between 0 and N-1, where N is the value of {@code
     *                      numMaterials} passed to {@link #Scene}.
     * @param material      The material properties to use.
     */
    public void setMaterial(int materialIndex, IPLMaterial material) {
        SteamAudioBridge.scene.__iplSetSceneMaterial(this, materialIndex, material);
    }

    /**
     * Specifies callbacks that allow a Scene object to call into a user-specified custom ray tracer. This function
     * should only be called if using a custom ray tracer, or else undefined behavior will occur. When using a custom
     * ray tracer, this function must be called before any simulation occurs, otherwise undefined behavior will occur.
     *
     * @param closestHitCallback Pointer to a function that returns the closest hit along a ray.
     * @param anyHitCallback     Pointer to a function that returns whether a ray hits anything.
     * @param userData           Pointer to a block of memory containing arbitrary data for use by the closest hit and
     *                           any hit callbacks.
     */
    public void setRayTracerCallbacks(IPLClosestHitCallback closestHitCallback, IPLAnyHitCallback anyHitCallback,
                                      Pointer<?> userData) {
        SteamAudio.scene.iplSetRayTracerCallbacks(this, closestHitCallback, anyHitCallback, userData);
    }

    /**
     * Finalizes a scene and builds internal data structures. Once this function is called, you may not modify the Scene
     * object or any Static Mesh objects it contains in any way. This function results in various internal data
     * structures being generated; if using Radeon Rays, it results in scene data being uploaded to the GPU. This is a
     * time-consuming, blocking call, so do not call it from performance-sensitive code.
     *
     * @param progressCallback Function that reports the percentage of this function's work that has been completed. May
     *                         be {@code NULL}.
     */
    public void finalize(Consumer<Float> progressCallback) {
        finalize(new IPLFinalizeSceneProgressCallback() {
            @Override
            public void apply(float progress) {
                progressCallback.accept(progress);
            }
        });
    }

    /**
     * Finalizes a scene and builds internal data structures. Once this function is called, you may not modify the Scene
     * object or any Static Mesh objects it contains in any way. This function results in various internal data
     * structures being generated; if using Radeon Rays, it results in scene data being uploaded to the GPU. This is a
     * time-consuming, blocking call, so do not call it from performance-sensitive code.
     *
     * @param progressCallback Pointer to a function that reports the percentage of this function's work that has been
     *                         completed. May be {@code NULL}.
     */
    public void finalize(IPLFinalizeSceneProgressCallback progressCallback) {
        SteamAudio.scene.iplFinalizeScene(this, progressCallback);
    }

    /**
     * Serializes a Scene object to a file on disk. The {@link #finalize} function must have been called on the Scene
     * object before calling this function. This function can only be called on a Scene object that has been created
     * using the Phonon built-in ray tracer.
     *
     * @param fileName Absolute or relative path to the file into which to serialize the Scene object.
     */
    public void save(String fileName) {
        SteamAudio.scene.iplSaveFinalizedScene(this, fileName);
    }

    /**
     * Saves a Scene object to an OBJ file. An OBJ file is a widely-supported 3D model file format, that can be
     * displayed using a variety of software on most PC platforms. The OBJ file generated by this function can be
     * useful for detecting problems that occur when exporting scene data from the game engine to Phonon. The
     * {@link #finalize} function must have been called on the Scene object before calling this function.
     * This function can only be called on a Scene object that has been created using the Phonon built-in ray tracer.
     *
     * @param fileBaseName Absolute or relative path to the OBJ file to generate.
     */
    public void dumpToObjFile(String fileBaseName) {
        SteamAudio.scene.iplDumpSceneToObjFile(this, fileBaseName);
    }
}
