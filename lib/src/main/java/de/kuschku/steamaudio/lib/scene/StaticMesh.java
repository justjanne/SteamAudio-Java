package de.kuschku.steamaudio.lib.scene;

import de.kuschku.steamaudio.lib.SteamAudio;
import de.kuschku.steamaudio.lib.geometry.IPLVector3;
import de.kuschku.steamaudio.lib.util.ErrorUtil;
import de.kuschku.steamaudio.lib.util.PointerHandle;
import org.bridj.Pointer;

public class StaticMesh extends PointerHandle {
    private final Scene scene;

    public StaticMesh(Scene scene, int numVertices, int numTriangles) throws ErrorUtil.SteamAudioException {
        super(SteamAudio.scene::iplCreateStaticMesh, scene, numVertices, numTriangles);
        setOnDelete(SteamAudio.scene::iplDestroyStaticMesh);

        this.scene = scene;
    }

    public void setVertices(Pointer<IPLVector3> vertices) {
        SteamAudio.scene.iplSetStaticMeshVertices(scene, this, vertices);
    }

    public void setTriangles(Pointer<IPLTriangle> triangles) {
        SteamAudio.scene.iplSetStaticMeshTriangles(scene, this, triangles);
    }

    public void setMaterials(Pointer<Integer> materialIndices) {
        SteamAudio.scene.iplSetStaticMeshMaterials(scene, this, materialIndices);
    }
}
