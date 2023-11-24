package com.github.jacksonhoggard.voodoo2d.engine.log;

public class Log {
    private static Logger ENGINE_LOGGER;
    private static Logger GAME_LOGGER;
    public static void init() {
        ENGINE_LOGGER = new Logger("Engine", Logger.TRACE);
        GAME_LOGGER = new Logger("Game", Logger.TRACE);
    }

    public static Logger engine() {
        return ENGINE_LOGGER;
    }

    public static Logger game() {
        return GAME_LOGGER;
    }
}
