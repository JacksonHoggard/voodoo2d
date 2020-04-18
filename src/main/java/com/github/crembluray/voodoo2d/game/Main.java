package com.github.crembluray.voodoo2d.game;

import com.github.crembluray.voodoo2d.engine.GameEngine;
import com.github.crembluray.voodoo2d.engine.IGameLogic;

public class Main {
 
    public static void main(String[] args) {
        try {
            boolean vSync = true;
            IGameLogic gameLogic = new Game();
            GameEngine gameEng = new GameEngine("V2D", vSync, gameLogic);
            gameEng.run();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }
}