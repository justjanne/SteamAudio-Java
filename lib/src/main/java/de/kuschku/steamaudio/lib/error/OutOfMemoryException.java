package de.kuschku.steamaudio.lib.error;

/**
 * An Exception representing that a native call unexpectedly returned {@link IPLerror#IPL_STATUS_OUTOFMEMORY}
 */
public class OutOfMemoryException extends SteamAudioException {
    public OutOfMemoryException() {
    }

    public OutOfMemoryException(String message) {
        super(message);
    }
}
