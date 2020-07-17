package com.github.crembluray.voodoo2d.engine.animation;

import com.github.crembluray.voodoo2d.engine.Timer;
import com.github.crembluray.voodoo2d.engine.gameObject.GameObject;
import com.github.crembluray.voodoo2d.game.Game;

public class AnimationManager {

    protected static double currentTime;

    protected static double elapsedTime;

    protected static double lastTime;

    public AnimationManager() {
        elapsedTime = 0;
        currentTime = 0;
        lastTime = Timer.getTime();
    }

    public void playAnimations() {
        currentTime = Timer.getTime();
        elapsedTime += currentTime - lastTime;

        for (GameObject g : GameObject.getGameObjects()) {
            if(g.getAnimation().isPlaying()) {
                g.getAnimation().run();
            }
        }

        lastTime = currentTime;
    }

}
