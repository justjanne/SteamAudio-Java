package de.kuschku.steamaudio.lib.baking;

import com.sun.jna.Callback;

public interface IPLBakeProgressCallback extends Callback {
    /**
     * A callback that is called to update the application on the progress of the {@link BakingApi#iplBakeReverb} or
     * {@link BakingApi#iplBakePropagation} functions. You can use this to provide visual feedback to the user, like a
     * progress bar.
     *
     * @param progress Fraction of the baking process that has been completed, between 0.0 and 1.0.
     */
    void apply(float progress);
}
