package de.kuschku.steamaudio.lib.scene;

import org.bridj.Callback;

public abstract class IPLFinalizeSceneProgressCallback extends Callback<IPLFinalizeSceneProgressCallback> {
    /**
     * A callback that is called to update the application on the progress of the iplFinalizeScene function. You can use
     * this to provide the user with visual feedback, like a progress bar.
     *
     * @param progress Fraction of the finalization process that has been completed, between 0.0 and 1.0.
     */
    public abstract void apply(float progress);
}
