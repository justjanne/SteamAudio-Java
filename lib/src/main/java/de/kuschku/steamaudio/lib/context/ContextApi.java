package de.kuschku.steamaudio.lib.context;

import com.sun.jna.Library;
import com.sun.jna.Native;

@SuppressWarnings("unused")
public interface ContextApi extends Library {
    static ContextApi getInstance() {
        return Native.loadLibrary("steamaudio", ContextApi.class);
    }

    void iplInitializeCrashHandler();

    void iplTerminateCrashHandler();
}
