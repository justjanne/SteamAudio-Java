package de.kuschku.steamaudio.demo;

import de.kuschku.steamaudio.lib.SteamAudio;
import de.kuschku.steamaudio.lib.util.ErrorUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String... args) throws ErrorUtil.SteamAudioException, IOException {
        logger.info("Initializing OpenGL");
        SharedLibraryLoader.load();
        SteamAudio.context.iplInitializeCrashHandler();
        org.lwjglb.game.Main.main(args);
    }
}
