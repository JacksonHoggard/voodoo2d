package com.github.jacksonhoggard.voodoo2d.game;

import com.github.jacksonhoggard.voodoo2d.engine.GameEngine;
import com.github.jacksonhoggard.voodoo2d.engine.IGameLogic;
import com.github.jacksonhoggard.voodoo2d.engine.Window;

public class Main {
 
    public static void main(String[] args) {
        try {
            IGameLogic gameLogic = new Game();
            GameEngine gameEng = new GameEngine(new Window("V2D",800,800, 2,false, false), gameLogic);
            gameEng.run();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}