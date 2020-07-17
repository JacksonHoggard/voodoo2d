package com.github.crembluray.voodoo2d.engine.animation;

import com.github.crembluray.voodoo2d.engine.gameObject.GameObject;

import static com.github.crembluray.voodoo2d.engine.animation.AnimationManager.elapsedTime;

public class Animation {

    private final int[] frames;

    private final int firstFrame, lastFrame;

    private int currentFrame;

    private final GameObject parent;

    private double rate;

    private boolean play;

    public Animation(GameObject parent, int firstFrame, int lastFrame, int rate) {
        this.parent = parent;
        this.firstFrame = firstFrame;
        this.lastFrame = lastFrame;
        frames = new int[(lastFrame - firstFrame) + 1];
        this.rate = 1.0/(double)rate;
    }

    protected void run() {
        if(elapsedTime >= rate) {
            elapsedTime -= rate;
            currentFrame++;
        }
        if(currentFrame >= frames.length) currentFrame = 0;
        parent.getMesh().setCurrentFrame(currentFrame + firstFrame);
    }

    public void play() {
        if(!play) elapsedTime = 0;
        play = true;
    }

    public void pause() {
        play = false;
    }

    public void stop() {
        play = false;
        parent.getMesh().setCurrentFrame(firstFrame);
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public boolean isPlaying() {
        return play;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    protected GameObject getParent() {
        return parent;
    }
}
