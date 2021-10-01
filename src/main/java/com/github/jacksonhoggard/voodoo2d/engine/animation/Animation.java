package com.github.jacksonhoggard.voodoo2d.engine.animation;

import com.github.jacksonhoggard.voodoo2d.engine.Timer;
import com.github.jacksonhoggard.voodoo2d.engine.gameObject.GameObject;

public class Animation {

    private final int[] frames;

    private final int firstFrame, lastFrame;

    private int currentFrame;

    private final GameObject parent;

    private double rate;

    private boolean play;

    private double currentTime;

    private double elapsedTime;

    private double lastTime;

    public Animation(GameObject parent, int firstFrame, int lastFrame, int rate) {
        this.parent = parent;
        elapsedTime = 0;
        currentTime = 0;
        lastTime = Timer.getTime();
        this.firstFrame = firstFrame;
        this.lastFrame = lastFrame;
        frames = new int[(lastFrame - firstFrame) + 1];
        this.rate = 1.0/(double)rate;
    }

    protected void run() {
        currentTime = Timer.getTime();
        elapsedTime += currentTime - lastTime;

        if(elapsedTime >= rate) {
            elapsedTime -= rate;
            currentFrame++;
        }
        if(currentFrame >= frames.length) currentFrame = 0;
        parent.getMesh().setCurrentFrame(currentFrame + firstFrame);

        lastTime = currentTime;
    }

    public void play() {
        if(!play) {
            elapsedTime = 0;
            currentTime = 0;
            lastTime = Timer.getTime();
        }
        parent.setAnimation(this);
        play = true;
    }

    public void pause() {
        play = false;
    }

    public void stop() {
        if(play) parent.getMesh().setCurrentFrame(firstFrame);
        play = false;
        setCurrentFrame(firstFrame);
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
