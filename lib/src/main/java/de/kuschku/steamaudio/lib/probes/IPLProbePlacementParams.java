package de.kuschku.steamaudio.lib.probes;

import org.bridj.BridJ;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

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

    @Field(0)
    public IntValuedEnum<IPLProbePlacement> placement() {
        return this.io.getEnumField(this, 0);
    }

    @Field(0)
    public IPLProbePlacementParams placement(IntValuedEnum<IPLProbePlacement> placement) {
        this.io.setEnumField(this, 0, placement);
        return this;
    }

    @Field(1)
    public float spacing() {
        return this.io.getFloatField(this, 1);
    }

    @Field(1)
    public IPLProbePlacementParams spacing(float spacing) {
        this.io.setFloatField(this, 1, spacing);
        return this;
    }

    @Field(2)
    public float heightAboveFloor() {
        return this.io.getFloatField(this, 2);
    }

    @Field(2)
    public IPLProbePlacementParams heightAboveFloor(float heightAboveFloor) {
        this.io.setFloatField(this, 2, heightAboveFloor);
        return this;
    }

    @Field(3)
    public int maxOctreeTriangles() {
        return this.io.getIntField(this, 3);
    }

    @Field(3)
    public IPLProbePlacementParams maxOctreeTriangles(int maxOctreeTriangles) {
        this.io.setIntField(this, 3, maxOctreeTriangles);
        return this;
    }

    @Field(4)
    public int maxOctreeDepth() {
        return this.io.getIntField(this, 4);
    }

    @Field(4)
    public IPLProbePlacementParams maxOctreeDepth(int maxOctreeDepth) {
        this.io.setIntField(this, 4, maxOctreeDepth);
        return this;
    }
}
