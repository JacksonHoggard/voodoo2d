package com.github.crembluray.voodoo2d.engine.animation;

import com.github.crembluray.voodoo2d.engine.gameObject.GameObject;

public class AnimationManager {

    public void playAnimations() {
        for (GameObject g : GameObject.getGameObjects()) {
            if (g.getAnimation().isPlaying()) {
                g.getAnimation().run();
            }
        }
    }

}
