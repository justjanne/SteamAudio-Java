package de.kuschku.steamaudio.lib.geometry;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import de.kuschku.steamaudio.lib.util.SmartStructure;

import java.util.Arrays;
import java.util.List;

/**
 * A point or vector in 3D space. Phonon uses a right-handed coordinate system, with the x-axis pointing right, the
 * y-axis pointing up, and the z-axis pointing ahead. Position and direction data obtained from a game engine or audio
 * engine must be properly transformed before being passed to any Phonon API function.
 */
public class IPLVector3 extends Structure implements SmartStructure<IPLVector3> {
    /**
     * The x-coordinate.
     */
    public float x;

    /**
     * The y-coordinate.
     */
    public float y;

    /**
     * The z-coordinate.
     */
    public float z;

    public IPLVector3() {
        super();
    }

    public IPLVector3(float x, float y, float z) {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public IPLVector3(Pointer peer) {
        super(peer);
    }

    protected List<String> getFieldOrder() {
        return Arrays.asList("x", "y", "z");
    }

    public static class ByReference extends IPLVector3 implements Structure.ByReference {
    }

    public static class ByValue extends IPLVector3 implements Structure.ByValue {
    }
}