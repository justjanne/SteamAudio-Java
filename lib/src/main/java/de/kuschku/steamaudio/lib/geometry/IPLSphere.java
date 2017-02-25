package de.kuschku.steamaudio.lib.geometry;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import de.kuschku.steamaudio.lib.util.SmartStructure;

import java.util.Arrays;
import java.util.List;

/**
 * A sphere. Spheres are used to define a region of influence around a point.
 */
public class IPLSphere extends Structure implements SmartStructure<IPLSphere> {
    /**
     * The center.
     */
    public IPLVector3 center;

    /**
     * The radius.
     */
    public float radius;

    public IPLSphere() {
        super();
    }

    public IPLSphere(IPLVector3 center, float radius) {
        super();
        this.center = center;
        this.radius = radius;
    }

    public IPLSphere(Pointer peer) {
        super(peer);
    }

    protected List<String> getFieldOrder() {
        return Arrays.asList("center", "radius");
    }

    public static class ByReference extends IPLSphere implements Structure.ByReference {
    }

    public static class ByValue extends IPLSphere implements Structure.ByValue {
    }
}