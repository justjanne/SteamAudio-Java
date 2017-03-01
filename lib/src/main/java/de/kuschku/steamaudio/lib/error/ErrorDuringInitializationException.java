package de.kuschku.steamaudio.lib.error;

/**
 * An Exception representing that a native call unexpectedly returned {@link IPLerror#IPL_STATUS_INITIALIZATION}
 */
public class ErrorDuringInitializationException extends SteamAudioException {
    public ErrorDuringInitializationException() {
    }

    public ErrorDuringInitializationException(String message) {
        super(message);
    }
}
