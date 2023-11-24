package com.github.jacksonhoggard.voodoo2d.engine;

public class Timer {

    private static double lastLoopTime;
    private static double deltaTime;

    public static double getTime() {
        return System.nanoTime() / 1000000000D; // Time in seconds
    }

    public static void calculateDeltaTime() {
        double time = getTime();
        deltaTime = (time - lastLoopTime);
        lastLoopTime = time;
    }

    public static float getDeltaTime() {
        return (float)deltaTime;
    }
}