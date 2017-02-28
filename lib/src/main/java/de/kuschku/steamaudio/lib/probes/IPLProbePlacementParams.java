package de.kuschku.steamaudio.lib.probes;

import org.bridj.BridJ;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

/**
 * Parameters that specify how probes should be created by {@link ProbeBox}.
 */
@Library("steamaudio")
public class IPLProbePlacementParams extends StructObject {
    static {
        BridJ.register();
    }

    public IPLProbePlacementParams() {
        super();
    }

    public IPLProbePlacementParams(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    /**
     * @return The placement algorithm to use for creating probes.
     */
    @Field(0)
    public IntValuedEnum<IPLProbePlacement> placement() {
        return this.io.getEnumField(this, 0);
    }

    /**
     * @param placement The placement algorithm to use for creating probes.
     */
    @Field(0)
    public IPLProbePlacementParams placement(IntValuedEnum<IPLProbePlacement> placement) {
        this.io.setEnumField(this, 0, placement);
        return this;
    }

    /**
     * @return Spacing between probes along the horizontal plane. Only used if {@code placement} is {@link
     * IPLProbePlacement#IPL_PLACEMENT_UNIFORMFLOOR}.
     */
    @Field(1)
    public float spacing() {
        return this.io.getFloatField(this, 1);
    }

    /**
     * @param spacing Spacing between probes along the horizontal plane. Only used if {@code placement} is {@link
     *                IPLProbePlacement#IPL_PLACEMENT_UNIFORMFLOOR}.
     */
    @Field(1)
    public IPLProbePlacementParams spacing(float spacing) {
        this.io.setFloatField(this, 1, spacing);
        return this;
    }

    /**
     * @return Height of the probes above the closest floor or terrain surfaces. Only used if {@code placement} is
     * {@link IPLProbePlacement#IPL_PLACEMENT_UNIFORMFLOOR}.
     */
    @Field(2)
    public float heightAboveFloor() {
        return this.io.getFloatField(this, 2);
    }

    /**
     * @param heightAboveFloor Height of the probes above the closest floor or terrain surfaces. Only used if {@code
     *                         placement} is {@link IPLProbePlacement#IPL_PLACEMENT_UNIFORMFLOOR}.
     */
    @Field(2)
    public IPLProbePlacementParams heightAboveFloor(float heightAboveFloor) {
        this.io.setFloatField(this, 2, heightAboveFloor);
        return this;
    }

    /**
     * @return The maximum number of triangles to store in an octree leaf node. Only used if {@code placement} is {@link
     * IPLProbePlacement#IPL_PLACEMENT_OCTREE}.
     */
    @Field(3)
    public int maxOctreeTriangles() {
        return this.io.getIntField(this, 3);
    }

    /**
     * @param maxOctreeTriangles The maximum number of triangles to store in an octree leaf node. Only used if {@code
     *                           placement} is {@link IPLProbePlacement#IPL_PLACEMENT_OCTREE}.
     */
    @Field(3)
    public IPLProbePlacementParams maxOctreeTriangles(int maxOctreeTriangles) {
        this.io.setIntField(this, 3, maxOctreeTriangles);
        return this;
    }

    /**
     * @return The maximum depth of the octree. Increasing this value increases density of the generated probes. Only
     * used if {@code placement} is {@link IPLProbePlacement#IPL_PLACEMENT_OCTREE}.
     */
    @Field(4)
    public int maxOctreeDepth() {
        return this.io.getIntField(this, 4);
    }

    /**
     * @param maxOctreeDepth The maximum depth of the octree. Increasing this value increases density of the generated
     *                       probes. Only used if {@code placement} is {@link IPLProbePlacement#IPL_PLACEMENT_OCTREE}.
     */
    @Field(4)
    public IPLProbePlacementParams maxOctreeDepth(int maxOctreeDepth) {
        this.io.setIntField(this, 4, maxOctreeDepth);
        return this;
    }
}
