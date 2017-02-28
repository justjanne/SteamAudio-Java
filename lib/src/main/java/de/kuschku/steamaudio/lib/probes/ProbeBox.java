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
    /**
     * Deserializes a Probe Box object from a byte array. This is typically called by the game engine's editor when
     * loading a Probe Box object from disk.
     *
     * @param data Byte array containing the serialized representation of the Probe Box object. Must not be {@code
     *             NULL}.
     *
     * @throws ErrorUtil.SteamAudioException Describes what kind of error happened in native code.
     */
    public ProbeBox(ByteBuffer data) throws ErrorUtil.SteamAudioException {
        super(SteamAudio.probes::iplLoadProbeBox, data, data.capacity());
        setOnDelete(SteamAudio.probes::iplDestroyProbeBox);
    }

    /**
     * Generates probes within a box. This function should typically be called from the game engine's editor, in
     * response to the user indicating that they want to generate probes in the scene.
     *
     * @param scene            Handle to the Scene object.
     * @param box              Bounding box within which to place probes.
     * @param placementParams  Parameters specifying how probes should be generated.
     * @param progressCallback Pointer to a function that reports the percentage of this function's work that has been
     *                         completed. May be {@code NULL}.
     *
     * @throws ErrorUtil.SteamAudioException Describes what kind of error happened in native code.
     */
    public ProbeBox(Scene scene, IPLBox box, IPLProbePlacementParams placementParams,
                    IPLProbePlacementProgressCallback progressCallback) throws ErrorUtil.SteamAudioException {
        super(SteamAudioBridge.probes::__iplCreateProbeBox, scene, box, placementParams, progressCallback);
        setOnDelete(SteamAudio.probes::iplDestroyProbeBox);
    }

    /**
     * Serializes a Probe Box object to a byte array. This is typically called by the game engine's editor in order to
     * save the Probe Box object's data to disk.
     *
     * @return Byte array into which the Probe Box object will be serialized.
     */
    public ByteBuffer save() {
        int size = SteamAudio.probes.iplSaveProbeBox(this, null);
        ByteBuffer buffer = ByteBuffer.allocate(size);
        if (size != SteamAudio.probes.iplSaveProbeBox(this, buffer)) throw new UnknownError();
        return buffer;
    }

    /**
     * Retrieves spheres describing the positions and influence radii of all probes in the Probe Box object. This
     * function should typically be called from the game engine's editor, and the retrieved spheres should be used for
     * visualization.
     *
     * @return Pointer to an array into which information about the probe spheres is returned.
     */
    public Pointer<IPLSphere> getProbeSpheres() {
        int size = SteamAudio.probes.iplGetProbeSpheres(this, null);
        Pointer<IPLSphere> pointer = Pointer.allocateArray(IPLSphere.class, size);
        if (size != SteamAudio.probes.iplGetProbeSpheres(this, pointer)) throw new UnknownError();
        return pointer;
    }
}
