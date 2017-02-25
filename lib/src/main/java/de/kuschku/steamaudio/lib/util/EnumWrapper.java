package de.kuschku.steamaudio.lib.util;

import com.sun.jna.FromNativeContext;
import com.sun.jna.NativeMapped;

import java.util.function.Function;

public class EnumWrapper<T extends IntValuedEnum> implements NativeMapped {
    private final Function<Integer, T> converter;
    private T enumValue;
    private int intValue;

    public EnumWrapper(Function<Integer, T> converter) {
        this(0, converter);
    }

    public EnumWrapper(T value, Function<Integer, T> converter) {
        this.converter = converter;
        set(value);
    }

    public EnumWrapper(int value, Function<Integer, T> converter) {
        this.converter = converter;
        set(value);
    }

    public void set(int value) {
        enumValue = converter.apply(value);
        intValue = value;
    }

    public void set(T value) {
        enumValue = value;
        intValue = value.value();
    }

    public T getEnumValue() {
        return enumValue;
    }

    public int getIntValue() {
        return intValue;
    }

    @Override
    public Object fromNative(Object nativeValue, FromNativeContext context) {
        try {
            EnumWrapper<T> value = (EnumWrapper<T>) getClass().newInstance();
            value.set((Integer) nativeValue);
            return value;
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

    @Override
    public Object toNative() {
        return getIntValue();
    }

    @Override
    public Class<?> nativeType() {
        return Integer.class;
    }
}
