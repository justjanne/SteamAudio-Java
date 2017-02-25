package de.kuschku.steamaudio.lib.util;

import com.sun.jna.Structure;

public interface SmartStructure<T extends SmartStructure<T>> {
    Structure[] toArray(int size);

    Structure[] toArray(Structure[] array);

    default T[] toSmartArray(int size) {
        return (T[]) toArray(size);
    }

    default T[] toSmartArray(Structure[] array) {
        return (T[]) toArray(array);
    }
}
