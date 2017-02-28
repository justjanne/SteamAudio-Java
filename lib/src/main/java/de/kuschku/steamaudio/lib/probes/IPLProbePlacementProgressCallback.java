package de.kuschku.steamaudio.lib.probes;

import org.bridj.Callback;

public abstract class IPLProbePlacementProgressCallback extends Callback<IPLProbePlacementProgressCallback> {
    /**
     * A callback that is called to update the application on the progress of the {@link ProbeBox#ProbeBox} function.
     * You can use this to provide visual feedback to the user, like a progress bar.
     *
     * @param progress Fraction of the probe generation process that has been completed, between 0.0 and 1.0.
     */
    public abstract void apply(float progress);
}
