package org.lwjglb.game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjglb.engine.GameEngine;
import org.lwjglb.engine.IGameLogic;
import org.lwjglb.engine.Window;

public class Main {

    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            boolean vSync = false;
            IGameLogic gameLogic = new DummyGame();
            Window.WindowOptions opts = new Window.WindowOptions();
            opts.cullFace = false;
            opts.showFps = true;
            opts.compatibleProfile = false;
            opts.antialiasing = true;
            logger.info("Initializing Engine");
            GameEngine gameEng = new GameEngine("GAME", vSync, opts, gameLogic);
            gameEng.start();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }
}
