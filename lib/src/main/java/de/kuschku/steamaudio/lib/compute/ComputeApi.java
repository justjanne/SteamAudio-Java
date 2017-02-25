package de.kuschku.steamaudio.lib.compute;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;
import de.kuschku.steamaudio.lib.util.IPLerror;

@SuppressWarnings("unused")
public interface ComputeApi extends Library {
    static ComputeApi getInstance() {
        return Native.loadLibrary("steamaudio", ComputeApi.class);
    }

    /**
     * Creates a Compute Device object. The same Compute Device must be used by the game engine and audio engine parts
     * of the Phonon integration. Depending on the OpenCL driver and device, this function may take some time to
     * execute, so do not call it from performance-sensitive code.
     *
     * @param deviceType      The type of device to use.
     * @param numComputeUnits Reserved for future use.
     * @param device          [out] Handle to the created Compute Device object.
     *
     * @return Status code indicating whether or not the operation succeeded.
     */
    IPLerror iplCreateComputeDevice(IPLComputeDeviceType deviceType, int numComputeUnits, PointerByReference device);

    /**
     * Destroys a Compute Device object. If any other API objects are still referencing the Compute Device object, it
     * will not be destroyed; destruction occurs when the object's reference count reaches zero.
     *
     * @param device [in, out] Address of a handle to the Compute Device object to destroy.
     */
    void iplDestroyComputeDevice(PointerByReference device);
}
