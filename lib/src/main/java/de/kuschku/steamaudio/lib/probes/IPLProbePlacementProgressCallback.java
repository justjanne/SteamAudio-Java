package de.kuschku.steamaudio.lib.probes;

import org.bridj.Callback;

public abstract class IPLProbePlacementProgressCallback extends Callback<IPLProbePlacementProgressCallback> {
    public abstract void apply(float progress);
}
