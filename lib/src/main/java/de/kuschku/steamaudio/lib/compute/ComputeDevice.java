package de.kuschku.steamaudio.lib.compute;

import de.kuschku.steamaudio.lib.SteamAudio;
import de.kuschku.steamaudio.lib.util.ErrorUtil;
import de.kuschku.steamaudio.lib.util.PointerHandle;

public class ComputeDevice extends PointerHandle {
    public ComputeDevice(IPLComputeDeviceType deviceType, int numComputeUnits) throws ErrorUtil.SteamAudioException {
        super(SteamAudio.compute::iplCreateComputeDevice, deviceType, numComputeUnits);
        setOnDelete(SteamAudio.compute::iplDestroyComputeDevice);
    }
}
