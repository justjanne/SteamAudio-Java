package de.kuschku.steamaudio.lib.geometry;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

/**
 * A point or vector in 3D space. Phonon uses a right-handed coordinate system, with the x-axis pointing right, the
 * y-axis pointing up, and the z-axis pointing ahead. Position and direction data obtained from a game engine or audio
 * engine must be properly transformed before being passed to any Phonon API function.
 */
@Library("steamaudio")
public class IPLVector3 extends StructObject {
    static {
        BridJ.register();
    }

    public IPLVector3() {
        super();
    }

    public IPLVector3(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    /**
     * @return The x-coordinate.
     */
    @Field(0)
    public float x() {
        return this.io.getFloatField(this, 0);
    }

    /**
     * @param x The x-coordinate.
     *
     * @return this
     */
    @Field(0)
    public IPLVector3 x(float x) {
        this.io.setFloatField(this, 0, x);
        return this;
    }

    /**
     * @return The y-coordinate.
     */
    @Field(1)
    public float y() {
        return this.io.getFloatField(this, 1);
    }

    /**
     * @param y The y-coordinate.
     *
     * @return this
     */
    @Field(1)
    public IPLVector3 y(float y) {
        this.io.setFloatField(this, 1, y);
        return this;
    }

    /**
     * @return The z-coordinate.
     */
    @Field(2)
    public float z() {
        return this.io.getFloatField(this, 2);
    }

    /**
     * @param z The z-coordinate.
     *
     * @return this
     */
    @Field(2)
    public IPLVector3 z(float z) {
        this.io.setFloatField(this, 2, z);
        return this;
    }
}
