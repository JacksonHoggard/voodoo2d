package com.github.jacksonhoggard.voodoo2d.engine;

import com.github.jacksonhoggard.voodoo2d.engine.animation.AnimationManager;

public class GameEngine implements Runnable {

    public static final int TARGET_FPS = 75;

    public static final int TARGET_UPS = 30;

    private final Window window;

    private final Timer timer;

    private final MouseInput mouseInput;

    private final AnimationManager animationManager;

    private final IGameLogic gameLogic;

    private final String windowTitle;

    private double lastFps;

    private int fps;
    
    public GameEngine(String windowTitle, int width, int height, boolean vSync, boolean antiAliasing, IGameLogic gameLogic) throws Exception {
        window = new Window(windowTitle, width, height, vSync, antiAliasing);
        this.gameLogic = gameLogic;
        this.windowTitle = windowTitle;
        timer = new Timer();
        mouseInput = new MouseInput();
        animationManager = new AnimationManager();
    }

    public GameEngine(String windowTitle, boolean vSync, boolean antiAliasing, IGameLogic gameLogic) throws Exception {
        this(windowTitle, 0, 0, vSync, antiAliasing, gameLogic);
    }

    @Override
    public void run() {
        try {
            init();
            gameLoop();
        } catch (Exception excp) {
            excp.printStackTrace();
        } finally {
            cleanup();
        }
    }

    protected void init() throws Exception {
        window.init();
        timer.init();
        mouseInput.init(window);
        gameLogic.init(window);
        lastFps = Timer.getTime();
        fps = 0;
    }

    protected void gameLoop() {
        float elapsedTime;
        float accumulator = 0f;
        float interval = 1f / TARGET_UPS;

        boolean running = true;
        while (running && !window.windowShouldClose()) {
            elapsedTime = timer.getElapsedTime();
            accumulator += elapsedTime;

            input();

            while (accumulator >= interval) {
                update(interval);
                accumulator -= interval;
            }

            render();

            if (!window.isvSync()) {
                sync();
            }
        }
    }

    protected void cleanup() {
        gameLogic.cleanup();
    }

    private void sync() {
        float loopSlot = 1f / TARGET_FPS;
        double endTime = timer.getLastLoopTime() + loopSlot;
        while (Timer.getTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ie) {
            }
        }
    }

    protected void input() {
        mouseInput.input(window);
        gameLogic.input(window, mouseInput);
    }

    protected void update(float interval) {
        animationManager.playAnimations();
        gameLogic.update(interval, mouseInput);
    }

    protected void render() {
        if(timer.getLastLoopTime() - lastFps > 1) {
            lastFps = timer.getLastLoopTime();
            window.setTitle(windowTitle + " - " + fps + " FPS");
            fps = 0;
        }
        fps++;
        gameLogic.render(window);
        window.update();
    }
}
