package com.github.jacksonhoggard.voodoo2d.game;

import com.github.jacksonhoggard.voodoo2d.engine.GameEngine;
import com.github.jacksonhoggard.voodoo2d.engine.IGameLogic;
import com.github.jacksonhoggard.voodoo2d.engine.Window;

public class Main {
 
    public static void main(String[] args) {
        try {
            IGameLogic gameLogic = new Game();
            Window window = new Window("V2D",800,800,0,false,true);
            GameEngine gameEng = new GameEngine(window, gameLogic);
            gameEng.run();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}