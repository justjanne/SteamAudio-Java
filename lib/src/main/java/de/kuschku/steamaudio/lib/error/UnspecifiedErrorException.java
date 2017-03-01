package de.kuschku.steamaudio.lib.error;

/**
 * An Exception representing that a native call unexpectedly returned {@link IPLerror#IPL_STATUS_FAILURE}
 */
public class UnspecifiedErrorException extends SteamAudioException {
    public UnspecifiedErrorException() {
    }

    public UnspecifiedErrorException(String message) {
        super(message);
    }
}
