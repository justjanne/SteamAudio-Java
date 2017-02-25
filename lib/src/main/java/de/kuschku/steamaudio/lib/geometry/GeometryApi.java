package de.kuschku.steamaudio.lib.geometry;

import com.sun.jna.Library;
import com.sun.jna.Native;

@SuppressWarnings("unused")
public interface GeometryApi extends Library {
    static GeometryApi getInstance() {
        return Native.loadLibrary("steamaudio", GeometryApi.class);
    }

    /**
     * Calculates the relative direction from the listener to a sound source. The returned direction vector is expressed
     * in the listener's coordinate system.
     *
     * @param sourcePosition   World-space coordinates of the source.
     * @param listenerPosition World-space coordinates of the listener.
     * @param listenerAhead    World-space unit-length vector pointing ahead relative to the listener.
     * @param listenerUp       World-space unit-length vector pointing up relative to the listener.
     *
     * @return A unit-length vector in the listener's coordinate space, pointing from the listener to the source.
     */
    IPLVector3.ByValue iplCalculateRelativeDirection(IPLVector3.ByValue sourcePosition,
                                                     IPLVector3.ByValue listenerPosition,
                                                     IPLVector3.ByValue listenerAhead, IPLVector3.ByValue listenerUp);
}
