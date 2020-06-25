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
        if(elapsedTime >= 0.01d) {
            elapsedTime = 0;
            for(Animation a : animations) a.reset();
        }
        for(int i = 0; i < animations.size(); i++) {
            if(animations.get(i).isPlaying())
                if(elapsedTime > (0.01d / animations.get(i).getRate()) * animations.get(i).getTimesRun()) {
                        for(int j = 0; j < animations.size(); j++)
                            if(i != j) {
                                if(animations.get(i).getParent() == animations.get(j).getParent())
                                    animations.get(j).stop();
                            }
                animations.get(i).run();
            }
        }
        if(elapsedTime >= 0.01d) {
            elapsedTime = 0;
            for(Animation a : animations) a.reset();
        }
    }

}
