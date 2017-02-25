package de.kuschku.steamaudio.lib.geometry;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * A unit-length quaternion. Quaternions are used to represent a rotation or orientation.
 */
public class IPLQuaternion extends Structure {
    /**
     * The x-coordinate of the vector part.
     */
    public float x;

    /**
     * The y-coordinate of the vector part.
     */
    public float y;

    /**
     * The z-coordinate of the vector part.
     */
    public float z;

    /**
     * The scalar part.
     */
    public float w;

    public IPLQuaternion() {
        super();
    }

    public IPLQuaternion(float x, float y, float z, float w) {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public IPLQuaternion(Pointer peer) {
        super(peer);
    }

    protected List<String> getFieldOrder() {
        return Arrays.asList("x", "y", "z", "w");
    }

    public static class ByReference extends IPLQuaternion implements Structure.ByReference {
    }

    public static class ByValue extends IPLQuaternion implements Structure.ByValue {
    }
}