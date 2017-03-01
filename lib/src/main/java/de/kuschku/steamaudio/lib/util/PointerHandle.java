package de.kuschku.steamaudio.lib.util;

import de.kuschku.steamaudio.lib.error.IPLerror;
import de.kuschku.steamaudio.lib.error.SteamAudioException;
import org.bridj.Pointer;

import java.io.Closeable;
import java.io.IOException;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public abstract class PointerHandle implements Closeable {
    private final Pointer<Pointer<?>> reference = Pointer.allocatePointer();
    private Consumer<Pointer<Pointer<?>>> onDelete;

    protected PointerHandle(Consumers._0 fun) {
        fun.apply(reference);
    }

    protected <A> PointerHandle(Consumers._1<A> fun, A a) {
        fun.apply(a, reference);
    }

    protected <A, B> PointerHandle(Consumers._2<A, B> fun, A a, B b) {
        fun.apply(a, b, reference);
    }

    protected <A, B, C> PointerHandle(Consumers._3<A, B, C> fun, A a, B b, C c) {
        fun.apply(a, b, c, reference);
    }

    protected <A, B, C, D> PointerHandle(Consumers._4<A, B, C, D> fun, A a, B b, C c, D d) {
        fun.apply(a, b, c, d, reference);
    }

    protected <A, B, C, D, E> PointerHandle(Consumers._5<A, B, C, D, E> fun, A a, B b, C c, D d, E e) {
        fun.apply(a, b, c, d, e, reference);
    }

    protected <A, B, C, D, E, F> PointerHandle(Consumers._6<A, B, C, D, E, F> fun, A a, B b, C c, D d, E e, F f) {
        fun.apply(a, b, c, d, e, f, reference);
    }

    protected <A, B, C, D, E, F, G> PointerHandle(Consumers._7<A, B, C, D, E, F, G> fun, A a, B b, C c, D d, E e, F f,
                                                  G g) {
        fun.apply(a, b, c, d, e, f, g, reference);
    }

    protected <A, B, C, D, E, F, G, H> PointerHandle(Consumers._8<A, B, C, D, E, F, G, H> fun, A a, B b, C c, D d, E e,
                                                     F f, G g, H h) {
        fun.apply(a, b, c, d, e, f, g, h, reference);
    }

    protected <A, B, C, D, E, F, G, H, I> PointerHandle(Consumers._9<A, B, C, D, E, F, G, H, I> fun, A a, B b, C c, D d,
                                                        E e, F f, G g, H h, I i) {
        fun.apply(a, b, c, d, e, f, g, h, i, reference);
    }

    protected PointerHandle(Methods._0 fun) throws SteamAudioException {
        ErrorUtil.errorToException(fun.apply(reference));
    }

    protected <A> PointerHandle(Methods._1<A> fun, A a) throws SteamAudioException {
        ErrorUtil.errorToException(fun.apply(a, reference));
    }

    protected <A, B> PointerHandle(Methods._2<A, B> fun, A a, B b) throws SteamAudioException {
        ErrorUtil.errorToException(fun.apply(a, b, reference));
    }

    protected <A, B, C> PointerHandle(Methods._3<A, B, C> fun, A a, B b, C c) throws SteamAudioException {
        ErrorUtil.errorToException(fun.apply(a, b, c, reference));
    }

    protected <A, B, C, D> PointerHandle(Methods._4<A, B, C, D> fun, A a, B b, C c, D d)
            throws SteamAudioException {
        ErrorUtil.errorToException(fun.apply(a, b, c, d, reference));
    }

    protected <A, B, C, D, E> PointerHandle(Methods._5<A, B, C, D, E> fun, A a, B b, C c, D d, E e)
            throws SteamAudioException {
        ErrorUtil.errorToException(fun.apply(a, b, c, d, e, reference));
    }

    protected <A, B, C, D, E, F> PointerHandle(Methods._6<A, B, C, D, E, F> fun, A a, B b, C c, D d, E e, F f)
            throws SteamAudioException {
        ErrorUtil.errorToException(fun.apply(a, b, c, d, e, f, reference));
    }

    protected <A, B, C, D, E, F, G> PointerHandle(Methods._7<A, B, C, D, E, F, G> fun, A a, B b, C c, D d, E e, F f,
                                                  G g) throws SteamAudioException {
        ErrorUtil.errorToException(fun.apply(a, b, c, d, e, f, g, reference));
    }

    protected <A, B, C, D, E, F, G, H> PointerHandle(Methods._8<A, B, C, D, E, F, G, H> fun, A a, B b, C c, D d, E e,
                                                     F f, G g, H h) throws SteamAudioException {
        ErrorUtil.errorToException(fun.apply(a, b, c, d, e, f, g, h, reference));
    }

    protected <A, B, C, D, E, F, G, H, I> PointerHandle(Methods._9<A, B, C, D, E, F, G, H, I> fun, A a, B b, C c, D d,
                                                        E e, F f, G g, H h, I i) throws SteamAudioException {
        ErrorUtil.errorToException(fun.apply(a, b, c, d, e, f, g, h, i, reference));
    }

    public static Pointer<?> reference(PointerHandle handle) {
        return handle == null ? Pointer.NULL : handle.reference();
    }

    public Pointer<?> reference() {
        return reference.get();
    }

    protected void setOnDelete(Consumer<Pointer<Pointer<?>>> onDelete) {
        this.onDelete = onDelete;
    }

    @Override
    public void close() throws IOException {
        if (onDelete != null) onDelete.accept(reference);
        reference.release();
    }

    public static class Methods {
        @FunctionalInterface
        public interface _0 {
            IPLerror apply(Pointer<Pointer<?>> reference);
        }

        @FunctionalInterface
        public interface _1<A> {
            IPLerror apply(A a, Pointer<Pointer<?>> reference);
        }

        @FunctionalInterface
        public interface _2<A, B> {
            IPLerror apply(A a, B b, Pointer<Pointer<?>> reference);
        }

        @FunctionalInterface
        public interface _3<A, B, C> {
            IPLerror apply(A a, B b, C d, Pointer<Pointer<?>> reference);
        }

        @FunctionalInterface
        public interface _4<A, B, C, D> {
            IPLerror apply(A a, B b, C c, D d, Pointer<Pointer<?>> reference);
        }

        @FunctionalInterface
        public interface _5<A, B, C, D, E> {
            IPLerror apply(A a, B b, C c, D d, E e, Pointer<Pointer<?>> reference);
        }

        @FunctionalInterface
        public interface _6<A, B, C, D, E, F> {
            IPLerror apply(A a, B b, C c, D d, E e, F f, Pointer<Pointer<?>> reference);
        }

        @FunctionalInterface
        public interface _7<A, B, C, D, E, F, G> {
            IPLerror apply(A a, B b, C c, D d, E e, F f, G g, Pointer<Pointer<?>> reference);
        }

        @FunctionalInterface
        public interface _8<A, B, C, D, E, F, G, H> {
            IPLerror apply(A a, B b, C c, D d, E e, F f, G g, H h, Pointer<Pointer<?>> reference);
        }

        @FunctionalInterface
        public interface _9<A, B, C, D, E, F, G, H, I> {
            IPLerror apply(A a, B b, C c, D d, E e, F f, G g, H h, I i, Pointer<Pointer<?>> reference);
        }
    }

    public static class Consumers {
        @FunctionalInterface
        public interface _0 {
            void apply(Pointer<Pointer<?>> reference);
        }

        @FunctionalInterface
        public interface _1<A> {
            void apply(A a, Pointer<Pointer<?>> reference);
        }

        @FunctionalInterface
        public interface _2<A, B> {
            void apply(A a, B b, Pointer<Pointer<?>> reference);
        }

        @FunctionalInterface
        public interface _3<A, B, C> {
            void apply(A a, B b, C d, Pointer<Pointer<?>> reference);
        }

        @FunctionalInterface
        public interface _4<A, B, C, D> {
            void apply(A a, B b, C c, D d, Pointer<Pointer<?>> reference);
        }

        @FunctionalInterface
        public interface _5<A, B, C, D, E> {
            void apply(A a, B b, C c, D d, E e, Pointer<Pointer<?>> reference);
        }

        @FunctionalInterface
        public interface _6<A, B, C, D, E, F> {
            void apply(A a, B b, C c, D d, E e, F f, Pointer<Pointer<?>> reference);
        }

        @FunctionalInterface
        public interface _7<A, B, C, D, E, F, G> {
            void apply(A a, B b, C c, D d, E e, F f, G g, Pointer<Pointer<?>> reference);
        }

        @FunctionalInterface
        public interface _8<A, B, C, D, E, F, G, H> {
            void apply(A a, B b, C c, D d, E e, F f, G g, H h, Pointer<Pointer<?>> reference);
        }

        @FunctionalInterface
        public interface _9<A, B, C, D, E, F, G, H, I> {
            void apply(A a, B b, C c, D d, E e, F f, G g, H h, I i, Pointer<Pointer<?>> reference);
        }
    }
}