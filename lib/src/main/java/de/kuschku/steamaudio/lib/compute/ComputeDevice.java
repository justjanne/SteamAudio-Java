package de.kuschku.steamaudio.lib.compute;

import de.kuschku.steamaudio.lib.util.SteamAudio;
import de.kuschku.steamaudio.lib.util.PointerHandle;
import de.kuschku.steamaudio.lib.error.SteamAudioException;

public class ComputeDevice extends PointerHandle {
    /**
     * Creates a Compute Device object. The same Compute Device must be used by the game engine and audio engine parts
     * of the Phonon integration. Depending on the OpenCL driver and device, this function may take some time to
     * execute, so do not call it from performance-sensitive code.
     *
     * @param deviceType      The type of device to use.
     * @param numComputeUnits Reserved for future use.
     *
     * @throws SteamAudioException Describes what kind of error happened in native code.
     */
    public ComputeDevice(IPLComputeDeviceType deviceType, int numComputeUnits) throws SteamAudioException {
        super(SteamAudio.compute::iplCreateComputeDevice, deviceType, numComputeUnits);
        setOnDelete(SteamAudio.compute::iplDestroyComputeDevice);
    }
}
