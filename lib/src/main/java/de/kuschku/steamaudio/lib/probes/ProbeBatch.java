package de.kuschku.steamaudio.lib.probes;

import de.kuschku.steamaudio.lib.SteamAudio;
import de.kuschku.steamaudio.lib.util.ErrorUtil;
import de.kuschku.steamaudio.lib.util.PointerHandle;

import java.nio.ByteBuffer;

public class ProbeBatch extends PointerHandle {
    /**
     * Deserializes a Probe Batch object from a byte array. This is typically called by the game engine's editor when
     * loading a Probe Batch object from disk. Calling this function implicitly calls {@link #finalize()}, so you do not
     * need to call it explicitly.
     *
     * @param data Byte array containing the serialized representation of the Probe Batch object. Must not be {@code
     *             NULL}.
     *
     * @throws ErrorUtil.SteamAudioException Describes what kind of error happened in native code.
     */
    public ProbeBatch(ByteBuffer data) throws ErrorUtil.SteamAudioException {
        super(SteamAudio.probes::iplLoadProbeBatch, data, data.capacity());
        setOnDelete(SteamAudio.probes::iplDestroyProbeBatch);
    }

    /**
     * Creates a Probe Batch object. A Probe Batch object represents a set of probes that are loaded and unloaded from
     * memory as a unit when the game is played. A Probe Batch may contain probes from multiple Probe Boxes; multiple
     * Probe Batches may contain probes from the same Probe Box. At run-time, Phonon does not use Probe Boxes, it only
     * needs Probe Batches. The typical workflow is as follows: <ol><li>Using the editor, the designer creates Probe
     * Boxes to sample the scene.</li><li>Using the editor, the designer specifies Probe Batches, and decides which
     * probes are part of each Probe Batch.</li><li>The editor saves the Probe Batches along with the rest of the scene
     * data for use at run-time.</li><li>At run-time, Phonon uses the Probe Batches to retrieve baked data.</li></ol>
     *
     * @throws ErrorUtil.SteamAudioException Describes what kind of error happened in native code.
     */
    public ProbeBatch() throws ErrorUtil.SteamAudioException {
        super(SteamAudio.probes::iplCreateProbeBatch);
        setOnDelete(SteamAudio.probes::iplDestroyProbeBatch);
    }

    /**
     * Serializes a Probe Batch object to a byte array. This is typically called by the game engine's editor in order
     * to save the Probe Batch object's data to disk.
     *
     * @return Byte array into which the Probe Batch object will be serialized.
     */
    public ByteBuffer save() {
        int size = SteamAudio.probes.iplSaveProbeBatch(this, null);
        ByteBuffer buffer = ByteBuffer.allocate(size);
        if (size != SteamAudio.probes.iplSaveProbeBatch(this, buffer)) throw new UnknownError();
        return buffer;
    }

    /**
     * Adds a specific probe from a Probe Box to a Probe Batch. Once all probes in a Probe Box have been assigned to
     * their respective Probe Batches, you can destroy the Probe Box object; the baked data for the probes will be
     * retained by the Probe Batch.
     *
     * @param probeBox   Handle to a Probe Box object from which the probe should be added.
     * @param probeIndex Index of the probe to add. The index is defined relative to the array of probes returned by
     *                   {@link ProbeBox#getProbeSpheres}.
     */
    public void addProbe(ProbeBox probeBox, int probeIndex) {
        SteamAudio.probes.iplAddProbeToBatch(this, probeBox, probeIndex);
    }

    /**
     * Finalizes the set of probes that comprise a Probe Batch. Calling this function builds internal data
     * structures that are used to rapidly determine which probes influence any given point in 3D space. You may
     * not call {@link #addProbe} after calling this function. You must call this function before calling
     * {@link ProbeManager#addProbeBatch} to add this Probe Batch object to a Probe Manager object.
     */
    public void finalize() {
        SteamAudio.probes.iplFinalizeProbeBatch(this);
    }
}
