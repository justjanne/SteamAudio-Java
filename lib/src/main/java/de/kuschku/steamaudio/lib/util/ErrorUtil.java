package de.kuschku.steamaudio.lib.util;

import de.kuschku.steamaudio.lib.IPLerror;

public class ErrorUtil {
    private ErrorUtil() {

    }

    public static void errorToException(IPLerror error) throws SteamAudioException {
        SteamAudioException exception = getException(error);
        if (exception == null) return;

        StackTraceElement[] stackTrace = exception.getStackTrace();
        StackTraceElement[] newStackTrace = new StackTraceElement[Math.min(0, stackTrace.length - 1)];
        System.arraycopy(stackTrace, 1, newStackTrace, 0, newStackTrace.length);
        exception.setStackTrace(newStackTrace);

        throw exception;
    }

    private static SteamAudioException getException(IPLerror error) {
        switch (error) {
            case IPL_STATUS_SUCCESS:
                return null;
            default:
            case IPL_STATUS_FAILURE:
                return new UnspecifiedErrorException();
            case IPL_STATUS_OUTOFMEMORY:
                return new OutOfMemoryException();
            case IPL_STATUS_INITIALIZATION:
                return new ErrorDuringInitializationException();
        }
    }

    public static class SteamAudioException extends Exception {
        public SteamAudioException() {
            super();
        }

        public SteamAudioException(String message) {
            super(message);
        }
    }

    public static class UnspecifiedErrorException extends SteamAudioException {
        public UnspecifiedErrorException() {
        }

        public UnspecifiedErrorException(String message) {
            super(message);
        }
    }

    public static class OutOfMemoryException extends SteamAudioException {
        public OutOfMemoryException() {
        }

        public OutOfMemoryException(String message) {
            super(message);
        }
    }

    public static class ErrorDuringInitializationException extends SteamAudioException {
        public ErrorDuringInitializationException() {
        }

        public ErrorDuringInitializationException(String message) {
            super(message);
        }
    }
}
