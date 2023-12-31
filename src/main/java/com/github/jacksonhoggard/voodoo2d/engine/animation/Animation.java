package com.github.jacksonhoggard.voodoo2d.engine.animation;

import com.github.jacksonhoggard.voodoo2d.engine.Timer;
import com.github.jacksonhoggard.voodoo2d.engine.gameObject.GameObject;

public class Animation {

    private final int[] frames;
    private final int firstFrame;
    private int currentFrame;
    private final GameObject parent;
    private double animSpeed;
    private double elapsed;
    private boolean play;

    public Animation(GameObject parent, int firstFrame, int lastFrame, double rate) {
        this.parent = parent;
        this.firstFrame = firstFrame;
        frames = new int[(lastFrame - firstFrame) + 1];
        this.animSpeed = 1 / rate;
    }

    protected void run() {
        elapsed += Timer.getDeltaTime();
        while(elapsed >= animSpeed) {
            elapsed -= animSpeed;
            currentFrame++;
            if(elapsed < animSpeed) elapsed = 0;
        }

        if(currentFrame >= frames.length) currentFrame = 0;
        parent.getMesh().setCurrentFrame(currentFrame + firstFrame);
    }

    public void play() {
        if(!play) {
            elapsed = 0;
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

    public double getAnimSpeed() {
        return animSpeed;
    }

    public void setAnimSpeed(int rate) {
        this.animSpeed = rate;
    }

    protected GameObject getParent() {
        return parent;
    }
}
