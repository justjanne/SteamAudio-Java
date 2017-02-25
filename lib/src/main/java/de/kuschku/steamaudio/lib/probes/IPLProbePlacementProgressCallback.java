package de.kuschku.steamaudio.lib.probes;

import com.sun.jna.Callback;

public interface IPLProbePlacementProgressCallback extends Callback {
    /**
     * A callback that is called to update the application on the progress of the {@link ProbesApi#iplCreateProbeBox}
     * function. You can use this to provide visual feedback to the user, like a progress bar.
     *
     * @param progress Fraction of the probe generation process that has been completed, between 0.0 and 1.0.
     */
    void apply(float progress);
}
