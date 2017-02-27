package de.kuschku.steamaudio.lib.probes;

import de.kuschku.steamaudio.lib.SteamAudio;
import de.kuschku.steamaudio.lib.util.ErrorUtil;
import de.kuschku.steamaudio.lib.util.PointerHandle;

public class ProbeManager extends PointerHandle {
    public ProbeManager() throws ErrorUtil.SteamAudioException {
        super(SteamAudio.probes::iplCreateProbeManager);
        setOnDelete(SteamAudio.probes::iplDestroyProbeManager);
    }

    public void addProbeBatch(ProbeBatch probeBatch) {
        SteamAudio.probes.iplAddProbeBatch(this, probeBatch);
    }
}
