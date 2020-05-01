package com.github.crembluray.voodoo2d.engine.animation;

import com.github.crembluray.voodoo2d.engine.gameObject.GameObject;

public class Animation {

    private final int[] frames;

    private final int firstFrame, lastFrame;

    private int currentFrame;

    private final GameObject parent;

    private int rate;

    private int timesRun;

    private boolean play;

    public Animation(GameObject parent, int firstFrame, int lastFrame, int rate) {
        this.parent = parent;
        this.firstFrame = firstFrame;
        this.lastFrame = lastFrame;
        frames = new int[(lastFrame - firstFrame) + 1];
        this.rate = rate;
        timesRun = 0;
        AnimationManager.addAnimation(this);
    }

    protected void run() {
        currentFrame++;
        if(currentFrame == frames.length) {
            currentFrame = 0;
        }
        parent.getMesh().setCurrentFrame(currentFrame + firstFrame);
        timesRun++;
    }

    public void play() {
        if(!play) AnimationManager.elapsedTime = 0;
        play = true;
    }

    public void pause() {
        play = false;
    }

    public void stop() {
        play = false;
        parent.getMesh().setCurrentFrame(firstFrame);
        timesRun = 0;
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    protected int getTimesRun() {
        return timesRun;
    }

    protected void reset() {
        timesRun = 0;
    }

    protected GameObject getParent() {
        return parent;
    }
}
