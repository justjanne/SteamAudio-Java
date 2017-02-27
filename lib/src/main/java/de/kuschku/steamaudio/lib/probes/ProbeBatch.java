package de.kuschku.steamaudio.lib.probes;

import de.kuschku.steamaudio.lib.SteamAudio;
import de.kuschku.steamaudio.lib.util.ErrorUtil;
import de.kuschku.steamaudio.lib.util.PointerHandle;

import java.nio.ByteBuffer;

public class ProbeBatch extends PointerHandle {
    public ProbeBatch(byte[] serializedData) throws ErrorUtil.SteamAudioException {
        super(SteamAudio.probes::iplLoadProbeBatch, ByteBuffer.wrap(serializedData), serializedData.length);
        setOnDelete(SteamAudio.probes::iplDestroyProbeBatch);
    }

    public ProbeBatch() throws ErrorUtil.SteamAudioException {
        super(SteamAudio.probes::iplCreateProbeBatch);
        setOnDelete(SteamAudio.probes::iplDestroyProbeBatch);
    }

    public ByteBuffer save() {
        int size = SteamAudio.probes.iplSaveProbeBatch(this, null);
        ByteBuffer buffer = ByteBuffer.allocate(size);
        if (size != SteamAudio.probes.iplSaveProbeBatch(this, buffer)) throw new UnknownError();
        return buffer;
    }

    public void addProbe(ProbeBox box, int index) {
        SteamAudio.probes.iplAddProbeToBatch(this, box, index);
    }
}
