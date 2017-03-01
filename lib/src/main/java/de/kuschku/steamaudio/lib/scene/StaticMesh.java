package de.kuschku.steamaudio.lib.scene;

import de.kuschku.steamaudio.lib.util.SteamAudio;
import de.kuschku.steamaudio.lib.geometry.IPLVector3;
import de.kuschku.steamaudio.lib.util.PointerHandle;
import de.kuschku.steamaudio.lib.error.SteamAudioException;
import org.bridj.Pointer;

public class StaticMesh extends PointerHandle {
    private final Scene scene;

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
     *
     * @throws SteamAudioException Describes what kind of error happened in native code.
     */
    public StaticMesh(Scene scene, int numVertices, int numTriangles) throws SteamAudioException {
        super(SteamAudio.scene::iplCreateStaticMesh, scene, numVertices, numTriangles);
        setOnDelete(SteamAudio.scene::iplDestroyStaticMesh);

        this.scene = scene;
    }

    /**
     * Specifies the vertices of a Static Mesh object. All vertices must be converted from the game engine's
     * coordinate system to Phonon's coordinate system before being passed to this function.
     *
     * @param vertices Array containing the coordinates of all vertices in the Static Mesh object. The number of {@link
     *                 IPLVector3} objects in the array must be equal to the value of {@code numVertices} passed to
     *                 {@link #StaticMesh}.
     */
    public void setVertices(Pointer<IPLVector3> vertices) {
        SteamAudio.scene.iplSetStaticMeshVertices(scene, this, vertices);
    }

    /**
     * Specifies the triangles of a Static Mesh object. Triangle indices passed using this function refer to
     * the vertex array passed using {@link #setVertices}.
     *
     * @param triangles Array containing all triangles in the Static Mesh object. The number of {@link IPLTriangle}
     *                  objects in the array must be equal to the value of {@code numTriangles} passed to {@link
     *                  #StaticMesh}.
     */
    public void setTriangles(Pointer<IPLTriangle> triangles) {
        SteamAudio.scene.iplSetStaticMeshTriangles(scene, this, triangles);
    }

    /**
     * Specifies the materials associated with each triangle in a Static Mesh object. Material indices passed
     * using this function refer to the array containing material data passed to {@link Scene#setMaterial}.
     *
     * @param materialIndices Array containing material indices for all triangles in the Static Mesh object. The number
     *                        of material indices in the array must be equal to the value of {@code numTriangles} passed
     *                        to {@link #StaticMesh}.
     */
    public void setMaterials(Pointer<Integer> materialIndices) {
        SteamAudio.scene.iplSetStaticMeshMaterials(scene, this, materialIndices);
    }
}
