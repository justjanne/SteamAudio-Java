package de.kuschku.steamaudio.lib.probes;

import de.kuschku.steamaudio.lib.SteamAudio;
import de.kuschku.steamaudio.lib.SteamAudioBridge;
import de.kuschku.steamaudio.lib.geometry.IPLBox;
import de.kuschku.steamaudio.lib.geometry.IPLSphere;
import de.kuschku.steamaudio.lib.scene.Scene;
import de.kuschku.steamaudio.lib.util.ErrorUtil;
import de.kuschku.steamaudio.lib.util.PointerHandle;
import org.bridj.Pointer;

import java.nio.ByteBuffer;

public class ProbeBox extends PointerHandle {
    public ProbeBox(byte[] serializedData) throws ErrorUtil.SteamAudioException {
        super(SteamAudio.probes::iplLoadProbeBox, ByteBuffer.wrap(serializedData), serializedData.length);
        setOnDelete(SteamAudio.probes::iplDestroyProbeBox);
    }

    public ProbeBox(Scene scene, IPLBox box, IPLProbePlacementParams placementParams,
                    IPLProbePlacementProgressCallback progressCallback) throws ErrorUtil.SteamAudioException {
        super(SteamAudioBridge.probes::__iplCreateProbeBox, scene, box, placementParams, progressCallback);
        setOnDelete(SteamAudio.probes::iplDestroyProbeBox);
    }

    public ByteBuffer save() {
        int size = SteamAudio.probes.iplSaveProbeBox(this, null);
        ByteBuffer buffer = ByteBuffer.allocate(size);
        if (size != SteamAudio.probes.iplSaveProbeBox(this, buffer)) throw new UnknownError();
        return buffer;
    }

    public Pointer<IPLSphere> getProbeSpheres() {
        int size = SteamAudio.probes.iplGetProbeSpheres(this, null);
        Pointer<IPLSphere> pointer = Pointer.allocateArray(IPLSphere.class, size);
        if (size != SteamAudio.probes.iplGetProbeSpheres(this, pointer)) throw new UnknownError();
        return pointer;
    }
}
