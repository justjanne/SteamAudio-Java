package de.kuschku.steamaudio.lib.context;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import de.kuschku.steamaudio.lib.util.SmartStructure;

import java.util.Arrays;
import java.util.List;

/**
 * The Context object. Any of the data members may be {@code null}, in which case Phonon will use a built-in default
 * behavior.
 */
public class IPLContext extends Structure implements SmartStructure<IPLContext> {
    /**
     * Callback for logging messages.
     */
    public IPLLogFunction logCallback;

    /**
     * Callback for allocating memory.
     */
    public IPLAllocateFunction allocateCallback;

    /**
     * Callback for freeing memory.
     */
    public IPLFreeFunction freeCallback;

    public IPLContext() {
        super();
    }

    public IPLContext(IPLLogFunction logCallback, IPLAllocateFunction allocateCallback, IPLFreeFunction freeCallback) {
        super();
        this.logCallback = logCallback;
        this.allocateCallback = allocateCallback;
        this.freeCallback = freeCallback;
    }

    public IPLContext(Pointer peer) {
        super(peer);
    }

    protected List<String> getFieldOrder() {
        return Arrays.asList("logCallback", "allocateCallback", "freeCallback");
    }

    public static class ByReference extends IPLContext implements Structure.ByReference {
    }

    public static class ByValue extends IPLContext implements Structure.ByValue {
    }
}