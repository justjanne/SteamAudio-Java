package de.kuschku.steamaudio.demo;

import de.kuschku.steamaudio.lib.util.SteamAudio;
import de.kuschku.steamaudio.lib.error.SteamAudioException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String... args) throws SteamAudioException, IOException {
        logger.info("Initializing OpenGL");
        SharedLibraryLoader.load();
        SteamAudio.context.iplInitializeCrashHandler();
        org.lwjglb.game.Main.main(args);
    }
}
