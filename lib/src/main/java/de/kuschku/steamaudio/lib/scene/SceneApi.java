package de.kuschku.steamaudio.lib.scene;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import de.kuschku.steamaudio.lib.context.IPLContext;
import de.kuschku.steamaudio.lib.geometry.IPLVector3;
import de.kuschku.steamaudio.lib.simsettings.IPLSimulationSettings;
import de.kuschku.steamaudio.lib.util.IPLerror;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

@SuppressWarnings("unused")
public interface SceneApi extends Library {
    static SceneApi getInstance() {
        return Native.loadLibrary("steamaudio", SceneApi.class);
    }

    /**
     * Creates a Scene object. A Scene object does not store any geometry information on its own; for that you need to
     * create one or more Static Mesh objects and add them to the Scene object. The Scene object does contain an array
     * of materials; all triangles in all Static Mesh objects refer to this array in order to specify their material
     * properties.
     *
     * @param context            The Context object used by the game engine.
     * @param computeDevice      Handle to a Compute Device object. Only required if using Radeon Rays for ray tracing,
     *                           may be {@code null} otherwise.
     * @param simulationSettings The settings to use for simulation.
     * @param numMaterials       The number of materials that are used to describe the various surfaces in the scene.
     *                           Materials may not be added or removed once the Scene object is created.
     * @param scene              [out] Handle to the created Scene object.
     *
     * @return Status code indicating whether or not the operation succeeded.
     */
    IPLerror iplCreateScene(IPLContext.ByValue context, Pointer computeDevice,
                            IPLSimulationSettings.ByValue simulationSettings, int numMaterials,
                            PointerByReference scene);

    /**
     * Destroys a Scene object. If any other API objects are still referencing the Scene object, it will not be
     * destroyed; destruction occurs when the object's reference count reaches zero.
     *
     * @param scene [in, out] Address of a handle to the Scene object to destroy.
     */
    void iplDestroyScene(PointerByReference scene);

    /**
     * Specifies a single material used by a Scene object. All materials must be completely specified before simulation
     * occurs, otherwise simulation results will be incorrect.
     *
     * @param scene         Handle to the Scene object.
     * @param materialIndex Index of the material to set. Between 0 and N-1, where N is the value of {@code
     *                      numMaterials} passed to {@link SceneApi#iplCreateScene}.
     * @param material      The material properties to use.
     */
    void iplSetSceneMaterial(Pointer scene, int materialIndex, IPLMaterial.ByValue material);

    /**
     * Specifies callbacks that allow a Scene object to call into a user-specified custom ray tracer. This function
     * should only be called if using a custom ray tracer, or else undefined behavior will occur. When using a custom
     * ray tracer, this function must be called before any simulation occurs, otherwise undefined behavior will occur.
     *
     * @param scene              Handle to the Scene object.
     * @param closestHitCallback Pointer to a function that returns the closest hit along a ray.
     * @param anyHitCallback     Pointer to a function that returns whether a ray hits anything.
     * @param userData           Pointer to a block of memory containing arbitrary data for use by the closest hit and
     *                           any hit callbacks.
     */
    void iplSetRayTracerCallbacks(Pointer scene, IPLClosestHitCallback closestHitCallback,
                                  IPLAnyHitCallback anyHitCallback, Pointer userData);

    /**
     * Creates a Static Mesh object. A Static Mesh object represents a triangle mesh that does not change after it is
     * created. A Static Mesh object also contains a mapping between each of its triangles and their acoustic material
     * properties. Static Mesh objects should be used for scene geometry that is guaranteed to never change, such as
     * rooms, buildings, or triangulated terrain. A Scene object may contain multiple Static Mesh objects, although
     * typically one is sufficient.
     *
     * @param scene        Handle to the Scene object to which to add the Static Mesh object.
     * @param numVertices  Number of vertices in the triangle mesh.
     * @param numTriangles Number of triangles in the triangle mesh.
     * @param staticMesh   [out] Handle to the created Static Mesh object.
     *
     * @return Status code indicating whether or not the operation succeeded.
     */
    int iplCreateStaticMesh(Pointer scene, int numVertices, int numTriangles, PointerByReference staticMesh);

    /**
     * Destroys a Static Mesh object. If any other API objects are still referencing the Static Mesh object, it will not
     * be destroyed; destruction occurs when the object's reference count reaches zero. Since the Scene object maintains
     * an internal reference to the Static Mesh object, you may call this function at any point after fully specifying
     * the Static Mesh object using {@link SceneApi#iplSetStaticMeshVertices}, {@link
     * SceneApi#iplSetStaticMeshTriangles}, and {@link SceneApi#iplSetStaticMeshMaterials}.
     *
     * @param staticMesh [in, out] Address of a handle to the Static Mesh object to destroy.
     */
    void iplDestroyStaticMesh(PointerByReference staticMesh);

    /**
     * Specifies the vertices of a Static Mesh object. All vertices must be converted from the game engine's coordinate
     * system to Phonon's coordinate system before being passed to this function.
     *
     * @param scene      Handle to the Scene object containing the Static Mesh object.
     * @param staticMesh Handle to the Static Mesh object.
     * @param vertices   Array containing the coordinates of all vertices in the Static Mesh object. The number of
     *                   {@link IPLVector3} objects in the array must be equal to the value of {@code numVertices}
     *                   passed to {@link SceneApi#iplCreateStaticMesh}.
     */
    void iplSetStaticMeshVertices(Pointer scene, Pointer staticMesh, IPLVector3 vertices);

    /**
     * Specifies the triangles of a Static Mesh object. Triangle indices passed using this function refer to the vertex
     * array passed using {@link SceneApi#iplSetStaticMeshVertices}.
     *
     * @param scene      Handle to the Scene object containing the Static Mesh object.
     * @param staticMesh Handle to the Static Mesh object.
     * @param triangles  Array containing all triangles in the Static Mesh object. The number of {@link IPLTriangle}
     *                   objects in the array must be equal to the value of {@code numTriangles} passed to {@link
     *                   SceneApi#iplCreateStaticMesh}.
     */
    void iplSetStaticMeshTriangles(Pointer scene, Pointer staticMesh, IPLTriangle triangles);

    /**
     * Specifies the materials associated with each triangle in a Static Mesh object. Material indices passed using this
     * function refer to the array containing material data passed to {@link SceneApi#iplSetSceneMaterial}.
     *
     * @param scene           Handle to the Scene object containing the Static Mesh object.
     * @param staticMesh      Handle to the Static Mesh object.
     * @param materialIndices Array containing material indices for all triangles in the Static Mesh object. The number
     *                        of material indices in the array must be equal to the value of {@code numTriangles}
     *                        passed
     *                        to {@link SceneApi#iplCreateStaticMesh}.
     */
    void iplSetStaticMeshMaterials(Pointer scene, Pointer staticMesh, IntBuffer materialIndices);

    /**
     * Finalizes a scene and builds internal data structures. Once this function is called, you may not modify the Scene
     * object or any Static Mesh objects it contains in any way. This function results in various internal data
     * structures being generated; if using Radeon Rays, it results in scene data being uploaded to the GPU. This is a
     * time-consuming, blocking call, so do not call it from performance-sensitive code.
     *
     * @param scene            Handle to the Scene object.
     * @param progressCallback Pointer to a function that reports the percentage of this function's work that has been
     *                         completed. May be {@code null}.
     */
    void iplFinalizeScene(Pointer scene, IPLFinalizeSceneProgressCallback progressCallback);

    /**
     * Serializes a Scene object to a file on disk. The {@link SceneApi#iplFinalizeScene} function must have been
     * called on the Scene object before calling this function. This function can only be called on a Scene object that
     * has been created using the Phonon built-in ray tracer.
     *
     * @param scene    Handle to the Scene object.
     * @param fileName Absolute or relative path to the file into which to serialize the Scene object.
     *
     * @return Status code indicating whether or not the operation succeeded.
     */
    IPLerror iplSaveFinalizedScene(Pointer scene, ByteBuffer fileName);

    /**
     * Creates a Scene object based on data stored in a file on disk. After this function is called, it is not necessary
     * to call {@link SceneApi#iplFinalizeScene} on the resulting Scene object.
     *
     * @param context            The Context object used by the game engine.
     * @param simulationSettings The settings to use for the simulation. This must exactly match the settings that were
     *                           used to create the original Scene object that was passed to {@link
     *                           SceneApi#iplSaveFinalizedScene}, except for the {@link IPLSimulationSettings#sceneType}
     *                           and {@link IPLSimulationSettings#sceneType} data members. This allows you to use the
     *                           same file to create a Scene object that uses any ray tracer you prefer.
     * @param fileName           Absolute or relative path to the file from which to load the Scene object.
     * @param computeDevice      Handle to a Compute Device object. Only required if using Radeon Rays for ray tracing,
     *                           may be {@code null} otherwise.
     * @param progressCallback   Pointer to a function that reports the percentage of this function's work that has
     *                           been
     *                           completed. May be {@code null}.
     * @param scene              [out] Handle to the created Scene object.
     *
     * @return Status code indicating whether or not the operation succeeded.
     */
    IPLerror iplLoadFinalizedScene(IPLContext.ByValue context, IPLSimulationSettings.ByValue simulationSettings,
                                   ByteBuffer fileName, Pointer computeDevice,
                                   IPLLoadSceneProgressCallback progressCallback, PointerByReference scene);

    /**
     * Saves a Scene object to an OBJ file. An OBJ file is a widely-supported 3D model file format, that can be
     * displayed using a variety of software on most PC platforms. The OBJ file generated by this function can be useful
     * for detecting problems that occur when exporting scene data from the game engine to Phonon. The {@link
     * SceneApi#iplFinalizeScene} function must have been called on the Scene object before calling this function.
     * This function can only be called on a Scene object that has been created using the Phonon built-in ray tracer.
     *
     * @param scene        Handle to the Scene object.
     * @param fileBaseName Absolute or relative path to the OBJ file to generate.
     */
    void iplDumpSceneToObjFile(Pointer scene, ByteBuffer fileBaseName);
}
