package de.kuschku.steamaudio.lib.context;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import org.bridj.ann.Ptr;

import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * The Context object. Any of the data members may be {@code NULL}, in which case Phonon will use a built-in default
 * behavior.
 */
@Library("steamaudio")
public class IPLContext extends StructObject {
    static {
        BridJ.register();
    }

    public IPLContext() {
        super();
    }

    public IPLContext(Pointer<? extends StructObject> pointer) {
        super(pointer);
    }

    /**
     * @return Callback for logging messages.
     */
    @Field(0)
    public Pointer<IPLLogFunction> logCallback() {
        return this.io.getPointerField(this, 0);
    }

    /**
     * @param logCallback Callback for logging messages.
     */
    @Field(0)
    public IPLContext logCallback(Pointer<IPLLogFunction> logCallback) {
        this.io.setPointerField(this, 0, logCallback);
        return this;
    }

    /**
     * @param logCallback Callback for logging messages.
     */
    public IPLContext logCallback(Consumer<String> logCallback) {
        logCallback(Pointer.getPointer(new IPLLogFunction() {
            @Override
            public void apply(Pointer<Byte> message) {
                logCallback.accept(message.getCString());
            }
        }));
        return this;
    }

    /**
     * @return Callback for allocating memory.
     */
    @Field(1)
    public Pointer<IPLAllocateFunction> allocateCallback() {
        return this.io.getPointerField(this, 1);
    }

    /**
     * @param allocateCallback Callback for allocating memory.
     */
    @Field(1)
    public IPLContext allocateCallback(Pointer<IPLAllocateFunction> allocateCallback) {
        this.io.setPointerField(this, 1, allocateCallback);
        return this;
    }

    /**
     * @param allocateCallback Callback for allocating memory.
     */
    public IPLContext allocateCallback(BiFunction<Long, Long, Long> allocateCallback) {
        allocateCallback(Pointer.getPointer(new IPLAllocateFunction() {
            public long apply$2(@Ptr long size, @Ptr long alignment) {
                return allocateCallback.apply(size, alignment);
            }
        }));
        return this;
    }

    /**
     * @return Callback for freeing memory.
     */
    @Field(2)
    public Pointer<IPLFreeFunction> freeCallback() {
        return this.io.getPointerField(this, 2);
    }

    /**
     * @param freeCallback Callback for freeing memory.
     */
    @Field(2)
    public IPLContext freeCallback(Pointer<IPLFreeFunction> freeCallback) {
        this.io.setPointerField(this, 2, freeCallback);
        return this;
    }

    /**
     * @param freeCallback Callback for freeing memory.
     */
    public IPLContext freeCallback(Consumer<Long> freeCallback) {
        freeCallback(Pointer.getPointer(new IPLFreeFunction() {
            @Override
            public void apply(long memoryBlock) {
                freeCallback.accept(memoryBlock);
            }
        }));
        return this;
    }
}
