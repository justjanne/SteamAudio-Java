package de.kuschku.steamaudio.lib.context;

import org.bridj.Callback;
import org.bridj.Pointer;
import org.bridj.ann.Ptr;

@SuppressWarnings("deprecation")
public abstract class IPLAllocateFunction extends Callback<IPLAllocateFunction> {
    /**
     * Prototype of a callback that allocates memory. This is usually specified to let Phonon use a custom memory
     * allocator. The default behavior is to use the OS-dependent aligned version of {@code malloc}.
     *
     * @param  size        The number of bytes to allocate.
     * @param  alignment   The alignment (in bytes) of the start address of the allocated memory.
     *
     * @return Pointer to the allocated block of memory, or {@code NULL} if allocation failed.
     */
    public Pointer<?> apply(@Ptr long size, @Ptr long alignment) {
        return Pointer.pointerToAddress(apply$2(size, alignment));
    }

    /**
     * Prototype of a callback that allocates memory. This is usually specified to let Phonon use a custom memory
     * allocator. The default behavior is to use the OS-dependent aligned version of {@code malloc}.
     *
     * @param  size        The number of bytes to allocate.
     * @param  alignment   The alignment (in bytes) of the start address of the allocated memory.
     *
     * @return Pointer to the allocated block of memory, or {@code NULL} if allocation failed.
     */
    @Ptr
    public long apply$2(@Ptr long size, @Ptr long alignment) {
        return Pointer.getPeer(apply(size, alignment));
    }
}
