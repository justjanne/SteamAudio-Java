package de.kuschku.steamaudio.lib.probes;

import de.kuschku.steamaudio.lib.SteamAudio;
import de.kuschku.steamaudio.lib.util.ErrorUtil;
import de.kuschku.steamaudio.lib.util.PointerHandle;

public class ProbeManager extends PointerHandle {
    /**
     * Creates a Probe Manager object. A Probe Manager object manages a set of Probe Batch objects are runtime. It is
     * typically exported from the game engine to the audio engine via an Environment object. Probe Batch objects can be
     * dynamically added to or removed from a Probe Manager object.
     */
    public ProbeManager() throws ErrorUtil.SteamAudioException {
        super(SteamAudio.probes::iplCreateProbeManager);
        setOnDelete(SteamAudio.probes::iplDestroyProbeManager);
    }

    /**
     * Adds a Probe Batch to a Probe Manager object. Once this function returns, probes in the Probe Batch will be used
     * to calculate sound propagation effects.
     *
     * @param probeBatch Handle to the Probe Batch object to add.
     */
    public void addProbeBatch(ProbeBatch probeBatch) {
        SteamAudio.probes.iplAddProbeBatch(this, probeBatch);
    }

    /**
     * Removes a Probe Batch from a Probe Manager object. Once this function returns, probes in the Probe Batch will no
     * longer be used to calculate sound propagation effects.
     *
     * @param probeBatch Handle to the Probe Batch object to remove.
     */
    public void removeProbeBatch(ProbeBatch probeBatch) {
        SteamAudio.probes.iplRemoveProbeBatch(this, probeBatch);
    }
}
