package de.kuschku.steamaudio.lib.probes;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import de.kuschku.steamaudio.lib.geometry.IPLBox;
import de.kuschku.steamaudio.lib.geometry.IPLSphere;
import de.kuschku.steamaudio.lib.util.IPLerror;

import java.nio.ByteBuffer;

@SuppressWarnings("unused")
public interface ProbesApi extends Library {
    static ProbesApi getInstance() {
        return Native.loadLibrary("steamaudio", ProbesApi.class);
    }

    /**
     * Generates probes within a box. This function should typically be called from the game engine's editor, in
     * response to the user indicating that they want to generate probes in the scene.
     *
     * @param scene            Handle to the Scene object.
     * @param box              Bounding box within which to place probes.
     * @param placementParams  Parameters specifying how probes should be generated.
     * @param progressCallback Pointer to a function that reports the percentage of this function's work that has been
     *                         completed. May be {@code null}.
     * @param probeBox         [out] Handle to the created Probe Box object.
     *
     * @return Status code indicating whether or not the operation succeeded.
     */
    IPLerror iplCreateProbeBox(Pointer scene, IPLBox.ByValue box, IPLProbePlacementParams.ByValue placementParams,
                               IPLProbePlacementProgressCallback progressCallback, PointerByReference probeBox);

    /**
     * Destroys a Probe Box object.
     *
     * @param probeBox [in, out] Address of a handle to the Probe Box object to destroy.
     */
    void iplDestroyProbeBox(PointerByReference probeBox);

    /**
     * Retrieves spheres describing the positions and influence radii of all probes in the Probe Box object. This
     * function should typically be called from the game engine's editor, and the retrieved spheres should be used for
     * visualization.
     *
     * @param probeBox     Handle to a Probe Box object.
     * @param probeSpheres [out] Array into which information about the probe spheres is returned. It is the the
     *                     caller's responsibility to manage memory for this array. The array must be large enough to
     *                     hold all the spheres in the Probe Box object. May be {@code null}, in which case no spheres
     *                     are returned; this is useful when finding out the number of probes in the Probe Box object.
     *
     * @return The number of probes in the Probe Box object.
     */
    int iplGetProbeSpheres(Pointer probeBox, IPLSphere probeSpheres);

    /**
     * Serializes a Probe Box object to a byte array. This is typically called by the game engine's editor in order to
     * save the Probe Box object's data to disk.
     *
     * @param probeBox Handle to a Probe Box object.
     * @param data     [out] Byte array into which the Probe Box object will be serialized. It is the caller's
     *                 responsibility to manage memory for this array. The array must be large enough to hold all the
     *                 data in the Probe Box object. May be {@code null}, in which case no data is returned; this is
     *                 useful when finding out the size of the data stored in the Probe Box object.
     *
     * @return Size (in bytes) of the serialized data.
     */
    int iplSaveProbeBox(Pointer probeBox, ByteBuffer data);

    /**
     * Deserializes a Probe Box object from a byte array. This is typically called by the game engine's editor when
     * loading a Probe Box object from disk.
     *
     * @param data     Byte array containing the serialized representation of the Probe Box object. Must not be {@code
     *                 null}.
     * @param size     Size (in bytes) of the serialized data.
     * @param probeBox [out] Handle to the created Probe Box object.
     *
     * @return Status code indicating whether or not the operation succeeded.
     */
    IPLerror iplLoadProbeBox(ByteBuffer data, int size, PointerByReference probeBox);

    /**
     * Creates a Probe Batch object. A Probe Batch object represents a set of probes that are loaded and unloaded from
     * memory as a unit when the game is played. A Probe Batch may contain probes from multiple Probe Boxes; multiple
     * Probe Batches may contain probes from the same Probe Box. At run-time, Phonon does not use Probe Boxes, it only
     * needs Probe Batches. The typical workflow is as follows:
     * <p>
     * 1. Using the editor, the designer creates Probe Boxes to sample the scene. 2. Using the editor, the designer
     * specifies Probe Batches, and decides which probes are part of each Probe Batch. 3. The editor saves the Probe
     * Batches along with the rest of the scene data for use at run-time. 4. At run-time, Phonon uses the Probe Batches
     * to retrieve baked data.
     *
     * @param probeBatch [out] Handle to the created Probe Batch object.
     *
     * @return Status code indicating whether or not the operation succeeded.
     */
    IPLerror iplCreateProbeBatch(PointerByReference probeBatch);

    /**
     * Destroys a Probe Batch object.
     *
     * @param probeBatch [in, out] Address of a handle to the Probe Batch object to destroy.
     */
    void iplDestroyProbeBatch(PointerByReference probeBatch);

    /**
     * Adds a specific probe from a Probe Box to a Probe Batch. Once all probes in a Probe Box have been assigned to
     * their respective Probe Batches, you can destroy the Probe Box object; the baked data for the probes will be
     * retained by the Probe Batch.
     *
     * @param probeBatch Handle to a Probe Batch object into which the probe should be added.
     * @param probeBox   Handle to a Probe Box object from which the probe should be added.
     * @param probeIndex Index of the probe to add. The index is defined relative to the array of probes returned by
     *                   {@link ProbesApi#iplGetProbeSpheres}.
     */
    void iplAddProbeToBatch(Pointer probeBatch, Pointer probeBox, int probeIndex);

    /**
     * Finalizes the set of probes that comprise a Probe Batch. Calling this function builds internal data structures
     * that are used to rapidly determine which probes influence any given point in 3D space. You may not call {@link
     * ProbesApi#iplAddProbeToBatch} after calling this function. You must call this function before calling {@link
     * ProbesApi#iplAddProbeBatch} to add this Probe Batch object to a Probe Manager object.
     *
     * @param probeBatch Handle to a ProbeBatch object.
     */
    void iplFinalizeProbeBatch(Pointer probeBatch);

    /**
     * Serializes a Probe Batch object to a byte array. This is typically called by the game engine's editor in order to
     * save the Probe Batch object's data to disk.
     *
     * @param probeBatch Handle to a Probe Batch object.
     * @param data       [out] Byte array into which the Probe Batch object will be serialized. It is the caller's
     *                   responsibility to manage memory for this array. The array must be large enough to hold all the
     *                   data in the Probe Batch object. May be {@code null}, in which case no data is returned; this
     *                   is
     *                   useful when finding out the size of the data stored in the Probe Batch object.
     *
     * @return Size (in bytes) of the serialized data.
     */
    int iplSaveProbeBatch(Pointer probeBatch, ByteBuffer data);

    /**
     * Deserializes a Probe Batch object from a byte array. This is typically called by the game engine's editor when
     * loading a Probe Batch object from disk. Calling this function implicitly calls {@link
     * ProbesApi#iplFinalizeProbeBatch}, so you do not need to call it explicitly.
     *
     * @param data       Byte array containing the serialized representation of the Probe Batch object. Must not be
     *                   {@code null}.
     * @param size       Size (in bytes) of the serialized data.
     * @param probeBatch [out] Handle to the created Probe Batch object.
     *
     * @return Status code indicating whether or not the operation succeeded.
     */
    IPLerror iplLoadProbeBatch(ByteBuffer data, int size, PointerByReference probeBatch);

    /**
     * Creates a Probe Manager object. A Probe Manager object manages a set of Probe Batch objects are runtime. It is
     * typically exported from the game engine to the audio engine via an Environment object. Probe Batch objects can be
     * dynamically added to or removed from a Probe Manager object.
     *
     * @param probeManager [out] Handle to the created Probe Manager object.
     *
     * @return Status code indicating whether or not the operation succeeded.
     */
    IPLerror iplCreateProbeManager(PointerByReference probeManager);

    /**
     * Destroys a Probe Manager object.
     *
     * @param probeManager [in, out] Address of a handle to the Probe Manager object to destroy.
     */
    void iplDestroyProbeManager(PointerByReference probeManager);

    /**
     * Adds a Probe Batch to a Probe Manager object. Once this function returns, probes in the Probe Batch will be used
     * to calculate sound propagation effects.
     *
     * @param probeManager Handle to a Probe Manager object.
     * @param probeBatch   Handle to the Probe Batch object to add.
     */
    void iplAddProbeBatch(Pointer probeManager, Pointer probeBatch);

    /**
     * Removes a Probe Batch from a Probe Manager object. Once this function returns, probes in the Probe Batch will no
     * longer be used to calculate sound propagation effects.
     *
     * @param probeManager Handle to a Probe Manager object.
     * @param probeBatch   Handle to the Probe Batch object to remove.
     */
    void iplRemoveProbeBatch(Pointer probeManager, Pointer probeBatch);
}
