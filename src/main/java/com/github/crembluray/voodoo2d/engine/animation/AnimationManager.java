package com.github.crembluray.voodoo2d.engine.animation;

import java.util.ArrayList;

public class AnimationManager {

    private static ArrayList<Animation> animations;

    protected static double elapsedTime;

    public AnimationManager() {
        animations = new ArrayList<Animation>();
        elapsedTime = 0;
    }

    public static void addAnimation(Animation animation) {
        animations.add(animation);
    }

    public void playAnimations(double lastTime) {
        lastTime /= 1e7;
        elapsedTime += lastTime;
        if(elapsedTime >= 1.0d) {
            elapsedTime = 0;
            for(Animation a : animations) a.reset();
        }
        for(Animation a : animations) {
            if(elapsedTime > (1.0d / a.getRate()) * a.getTimesRun()) {
                if(a.isPlaying())
                    a.run();
            }
        }
        if(elapsedTime >= 1.0d) {
            elapsedTime = 0;
            for(Animation a : animations) a.reset();
        }
    }

}
