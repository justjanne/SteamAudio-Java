package de.kuschku.steamaudio.lib.scene;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Array;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

/**
 * A triangle in 3D space. Triangles are specified by their three vertices, which are in turn specified using indices
 * into a vertex array. See {@link StaticMesh#setTriangles} for how to specify the vertex array. Phonon uses a
 * counter-clockwise winding order. This means that when looking at the triangle such that the normal is pointing
 * towards you, the vertices are specified in counter-clockwise order.
 */
@Library("steamaudio")
public class IPLTriangle extends StructObject {
    static {
        BridJ.register();
    }

    public IPLTriangle() {
        super();
    }

    public IPLTriangle(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    /**
     * @return Indices of the three vertices of this triangle. Each triangle must be specified using three vertices;
     * triangle strip or fan representations are not supported.
     */
    @Array({3})
    @Field(0)
    public Pointer<Integer> indices() {
        return this.io.getPointerField(this, 0);
    }
}
