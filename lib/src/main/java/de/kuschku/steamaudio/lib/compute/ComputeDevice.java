package de.kuschku.steamaudio.lib.compute;

import de.kuschku.steamaudio.lib.SteamAudio;
import de.kuschku.steamaudio.lib.util.ErrorUtil;
import de.kuschku.steamaudio.lib.util.PointerHandle;

public class ComputeDevice extends PointerHandle {
    /**
     * Creates a Compute Device object. The same Compute Device must be used by the game engine and audio engine parts
     * of the Phonon integration. Depending on the OpenCL driver and device, this function may take some time to
     * execute, so do not call it from performance-sensitive code.
     *
     * @param deviceType      The type of device to use.
     * @param numComputeUnits Reserved for future use.
     *
     * @throws ErrorUtil.SteamAudioException Describes what kind of error happened in native code.
     */
    public ComputeDevice(IPLComputeDeviceType deviceType, int numComputeUnits) throws ErrorUtil.SteamAudioException {
        super(SteamAudio.compute::iplCreateComputeDevice, deviceType, numComputeUnits);
        setOnDelete(SteamAudio.compute::iplDestroyComputeDevice);
    }
}
