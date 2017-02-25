package de.kuschku.steamaudio.lib.scene;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * A triangle in 3D space. Triangles are specified by their three vertices, which are in turn specified using indices
 * into a vertex array. See {@link SceneApi#iplSetStaticMeshVertices} for how to specify the vertex array. Phonon uses
 * a counter-clockwise winding order. This means that when looking at the triangle such that the normal is pointing
 * towards you, the vertices are specified in counter-clockwise order.
 */
public class IPLTriangle extends Structure {
    /**
     * Indices of the three vertices of this triangle. Each triangle must be specified using three vertices; triangle
     * strip or fan representations are not supported.
     */
    public int[] indices = new int[3];

    public IPLTriangle() {
        super();
    }

    public IPLTriangle(int indices[]) {
        super();
        if ((indices.length != this.indices.length)) throw new IllegalArgumentException("Wrong array size !");
        this.indices = indices;
    }

    public IPLTriangle(Pointer peer) {
        super(peer);
    }

    protected List<String> getFieldOrder() {
        return Arrays.asList("indices");
    }

    public static class ByReference extends IPLTriangle implements Structure.ByReference {
    }

    public static class ByValue extends IPLTriangle implements Structure.ByValue {
    }
}