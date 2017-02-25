package de.kuschku.steamaudio.lib.context;

import com.ochafik.lang.jnaerator.runtime.NativeSize;
import com.sun.jna.Callback;
import com.sun.jna.Pointer;

public interface IPLAllocateFunction extends Callback {
    /**
     * Prototype of a callback that allocates memory. This is usually specified to let Phonon use a custom memory
     * allocator. The default behavior is to use the OS-dependent aligned version of <code>malloc</code>.
     *
     * @param size      The number of bytes to allocate.
     * @param alignment The alignment (in bytes) of the start address of the allocated memory.
     *
     * @return Pointer to the allocated block of memory, or {@code null} if allocation failed.
     */
    Pointer apply(NativeSize size, NativeSize alignment);
}
