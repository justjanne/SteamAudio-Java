package de.kuschku.steamaudio.lib.geometry;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

/**
 * A sphere. Spheres are used to define a region of influence around a point.
 */
@Library("steamaudio")
public class IPLSphere extends StructObject {
    static {
        BridJ.register();
    }

    public IPLSphere() {
        super();
    }

    public IPLSphere(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    /**
     * @return The center.
     */
    @Field(0)
    public IPLVector3 center() {
        return this.io.getNativeObjectField(this, 0);
    }

    /**
     * @param center The center.
     *
     * @return this
     */
    @Field(0)
    public IPLSphere center(IPLVector3 center) {
        this.io.setNativeObjectField(this, 0, center);
        return this;
    }

    /**
     * @return The radius.
     */
    @Field(1)
    public float radius() {
        return this.io.getFloatField(this, 1);
    }

    /**
     * @param radius The radius.
     *
     * @return this
     */
    @Field(1)
    public IPLSphere radius(float radius) {
        this.io.setFloatField(this, 1, radius);
        return this;
    }
}
