package de.kuschku.steamaudio.lib.probes;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * Parameters that specify how probes should be created by {@link ProbesApi#iplCreateProbeBox}.
 */
public class IPLProbePlacementParams extends Structure {
    /**
     * The placement algorithm to use for creating probes.
     */
    public IPLProbePlacement placement;

    /**
     * Spacing between probes along the horizontal plane. Only used if {@link #placement} is {@link
     * IPLProbePlacement.Enum#IPL_PLACEMENT_UNIFORMFLOOR}.
     */
    public float spacing;

    /**
     * Height of the probes above the closest floor or terrain surfaces. Only used if {@link #placement} is {@link
     * IPLProbePlacement.Enum#IPL_PLACEMENT_UNIFORMFLOOR}.
     */
    public float heightAboveFloor;

    /**
     * The maximum number of triangles to store in an octree leaf node. Only used if {@link #placement} is {@link
     * IPLProbePlacement.Enum#IPL_PLACEMENT_OCTREE}.
     */
    public int maxOctreeTriangles;

    /**
     * The maximum depth of the octree. Increasing this value increases density of the generated probes. Only used if
     * {@link #placement} is {@link IPLProbePlacement.Enum#IPL_PLACEMENT_OCTREE}.
     */
    public int maxOctreeDepth;

    public IPLProbePlacementParams() {
        super();
    }

    public IPLProbePlacementParams(IPLProbePlacement placement, float spacing, float heightAboveFloor,
                                   int maxOctreeTriangles, int maxOctreeDepth) {
        super();
        this.placement = placement;
        this.spacing = spacing;
        this.heightAboveFloor = heightAboveFloor;
        this.maxOctreeTriangles = maxOctreeTriangles;
        this.maxOctreeDepth = maxOctreeDepth;
    }

    public IPLProbePlacementParams(Pointer peer) {
        super(peer);
    }

    protected List<String> getFieldOrder() {
        return Arrays.asList("placement", "spacing", "heightAboveFloor", "maxOctreeTriangles", "maxOctreeDepth");
    }

    public static class ByReference extends IPLProbePlacementParams implements Structure.ByReference {
    }

    public static class ByValue extends IPLProbePlacementParams implements Structure.ByValue {
    }
}