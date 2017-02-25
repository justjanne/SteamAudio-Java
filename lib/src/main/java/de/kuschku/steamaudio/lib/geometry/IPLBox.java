package de.kuschku.steamaudio.lib.geometry;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * An axis-aligned box. Axis-aligned boxes are used to specify a volume of 3D space.
 */
public class IPLBox extends Structure {

    /**
     * The minimum coordinates of any vertex.
     */
    public IPLVector3 minCoordinates;

    /**
     * The maximum coordinates of any vertex.
     */
    public IPLVector3 maxCoordinates;

    public IPLBox() {
        super();
    }

    public IPLBox(IPLVector3 minCoordinates, IPLVector3 maxCoordinates) {
        super();
        this.minCoordinates = minCoordinates;
        this.maxCoordinates = maxCoordinates;
    }

    public IPLBox(Pointer peer) {
        super(peer);
    }

    protected List<String> getFieldOrder() {
        return Arrays.asList("minCoordinates", "maxCoordinates");
    }

    public static class ByReference extends IPLBox implements Structure.ByReference {
    }

    public static class ByValue extends IPLBox implements Structure.ByValue {
    }
}