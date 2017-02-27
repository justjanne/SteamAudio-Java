package de.kuschku.steamaudio.lib.scene;

import org.bridj.Callback;

public abstract class IPLFinalizeSceneProgressCallback extends Callback<IPLFinalizeSceneProgressCallback> {
    public abstract void apply(float progress);
}
