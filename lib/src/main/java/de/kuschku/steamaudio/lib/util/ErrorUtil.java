package de.kuschku.steamaudio.lib.util;

import de.kuschku.steamaudio.lib.error.*;

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

}
