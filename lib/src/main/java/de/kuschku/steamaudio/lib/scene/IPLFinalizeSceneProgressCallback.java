package de.kuschku.steamaudio.lib.scene;

import com.sun.jna.Callback;

public interface IPLFinalizeSceneProgressCallback extends Callback {
    /**
     * A callback that is called to update the application on the progress of the {@link SceneApi#iplFinalizeScene}
     * function. You can use this to provide the user with visual feedback, like a progress bar.
     *
     * @param progress Fraction of the finalization process that has been completed, between 0.0 and 1.0.
     */
    void apply(float progress);
}
