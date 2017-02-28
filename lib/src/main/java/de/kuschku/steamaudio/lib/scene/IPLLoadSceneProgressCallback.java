package de.kuschku.steamaudio.lib.scene;

import org.bridj.Callback;

public abstract class IPLLoadSceneProgressCallback extends Callback<IPLLoadSceneProgressCallback> {
    /**
     * A callback that is called to update the application on the progress of the iplLoadScene function. You can use
     * this to provide the user with visual feedback, like a progress bar.
     *
     * @param progress Fraction of the loading process that has been completed, between 0.0 and 1.0.
     */
    public abstract void apply(float progress);
}
