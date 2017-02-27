package de.kuschku.steamaudio.lib.baking;

import org.bridj.Callback;

public abstract class IPLBakeProgressCallback extends Callback<IPLBakeProgressCallback> {
    public abstract void apply(float progress);
}
