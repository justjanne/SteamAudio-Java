package de.kuschku.steamaudio.lib.scene;

import org.bridj.Callback;

public abstract class IPLLoadSceneProgressCallback extends Callback<IPLLoadSceneProgressCallback> {
    public abstract void apply(float progress);
}
