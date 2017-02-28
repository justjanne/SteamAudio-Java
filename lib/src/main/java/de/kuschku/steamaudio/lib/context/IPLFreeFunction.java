package de.kuschku.steamaudio.lib.context;

import org.bridj.Callback;
import org.bridj.Pointer;
import org.bridj.ann.Ptr;

public abstract class IPLFreeFunction extends Callback<IPLFreeFunction> {
    /**
     * Prototype of a callback that frees a block of memory. This is usually specified when using a custom memory
     * allocator with Phonon. The default behavior is to use the OS-dependent aligned version of {@code free}.
     *
     * @param memoryBlock Pointer to the block of memory.
     */
    public void apply(Pointer<?> memoryBlock) {
        apply(Pointer.getPeer(memoryBlock));
    }

    /**
     * Prototype of a callback that frees a block of memory. This is usually specified when using a custom memory
     * allocator with Phonon. The default behavior is to use the OS-dependent aligned version of {@code free}.
     *
     * @param memoryBlock Pointer to the block of memory.
     */
    @SuppressWarnings("deprecation")
    public void apply(@Ptr long memoryBlock) {
        apply(Pointer.pointerToAddress(memoryBlock));
    }
}
