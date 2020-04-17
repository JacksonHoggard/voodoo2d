package com.github.crembluray.voodoo2d.game;

import com.github.crembluray.voodoo2d.engine.GameEngine;
import com.github.crembluray.voodoo2d.engine.IGameLogic;

public class Main {

    public static void main(String[] args) {
        try {
            boolean vSync = true;
            IGameLogic gameLogic = new Game();
            GameEngine gameEngine = new GameEngine("V2D", vSync, gameLogic);
            gameEngine.run();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

}
