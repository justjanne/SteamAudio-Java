package de.kuschku.steamaudio.lib.baking;

import de.kuschku.steamaudio.lib.SteamAudioBridge;
import org.bridj.Callback;

public abstract class IPLBakeProgressCallback extends Callback<IPLBakeProgressCallback> {
    /**
     * A callback that is called to update the application on the progress of the {@link
     * SteamAudioBridge.baking#__iplBakeReverb} or {@link SteamAudioBridge.baking#__iplBakePropagation} functions. You
     * can use this to provide visual feedback to the user, like a progress bar.
     *
     * @param progress Fraction of the baking process that has been completed, between 0.0 and 1.0.
     */
    public abstract void apply(float progress);
}
