package de.kuschku.steamaudio.lib.geometry;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

/**
 * A unit-length quaternion. Quaternions are used to represent a rotation or orientation.
 */
@Library("steamaudio")
public class IPLQuaternion extends StructObject {
    static {
        BridJ.register();
    }

    public IPLQuaternion() {
        super();
    }

    public IPLQuaternion(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    /**
     * @return The x-coordinate of the vector part.
     */
    @Field(0)
    public float x() {
        return this.io.getFloatField(this, 0);
    }

    /**
     * @param x The x-coordinate of the vector part.
     *
     * @return this
     */
    @Field(0)
    public IPLQuaternion x(float x) {
        this.io.setFloatField(this, 0, x);
        return this;
    }

    /**
     * @return The y-coordinate of the vector part.
     */
    @Field(1)
    public float y() {
        return this.io.getFloatField(this, 1);
    }

    /**
     * @param y The y-coordinate of the vector part.
     *
     * @return this
     */
    @Field(1)
    public IPLQuaternion y(float y) {
        this.io.setFloatField(this, 1, y);
        return this;
    }

    /**
     * @return The z-coordinate of the vector part.
     */
    @Field(2)
    public float z() {
        return this.io.getFloatField(this, 2);
    }

    /**
     * @param z The z-coordinate of the vector part.
     *
     * @return this
     */
    @Field(2)
    public IPLQuaternion z(float z) {
        this.io.setFloatField(this, 2, z);
        return this;
    }

    /**
     * @return The scalar part.
     */
    @Field(3)
    public float w() {
        return this.io.getFloatField(this, 3);
    }

    /**
     * @param w The scalar part.
     *
     * @return this
     */
    @Field(3)
    public IPLQuaternion w(float w) {
        this.io.setFloatField(this, 3, w);
        return this;
    }
}
