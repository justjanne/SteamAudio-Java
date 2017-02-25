package de.kuschku.steamaudio.lib.context;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;

public interface IPLFreeFunction extends Callback {
    /**
     * Prototype of a callback that frees a block of memory. This is usually specified when using a custom memory
     * allocator with Phonon. The default behavior is to use the OS-dependent aligned version of {@code free}.
     *
     * @param memoryBlock Pointer to the block of memory.
     */
    void apply(Pointer memoryBlock);
}
