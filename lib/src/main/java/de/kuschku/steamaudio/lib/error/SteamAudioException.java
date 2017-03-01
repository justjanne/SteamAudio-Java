package de.kuschku.steamaudio.lib.error;

/**
 * An Exception representing that a native call unexpectedly returned an {@link IPLerror}
 */
public abstract class SteamAudioException extends Exception {
    public SteamAudioException() {
        super();
    }

    public SteamAudioException(String message) {
        super(message);
    }
}
