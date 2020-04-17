package com.github.crembluray.voodoo2d.engine;

public class GameEngine {

    public static final int TARGET_FPS = 75;

    public static final int TARGET_UPS = 30;

    private final Timer timer;

    private final Window window;

    private final IGameLogic gameLogic;

    public final MouseInput mouseInput;

    private String windowTitle;

    private double lastFps;

    private int fps;

    public GameEngine(String title, boolean vSync, int width, int height, IGameLogic gameLogic) {
        window = new Window(title, width, height, vSync);
        timer = new Timer();
        mouseInput = new MouseInput();
        this.gameLogic = gameLogic;
        this.windowTitle = title;
    }

    public GameEngine(String title, boolean vSync, IGameLogic gameLogic) {
        this(title, vSync, 0, 0, gameLogic);
    }

    public void run() {
        try{
            init();
            gameLoop();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cleanup();
        }
    }

    protected void init() throws Exception {
        window.init();
        timer.init();
        mouseInput.init(window);
        gameLogic.init(window);
        lastFps = timer.getTime();
        fps = 0;
    }

    protected void gameLoop() {
        float elapsedTime;
        float accumulator = 0f;
        float interval = 1f / TARGET_UPS;

        while(!window.windowShouldClose()) {
            elapsedTime = timer.getElapsedTime();
            accumulator += elapsedTime;

            input();

            while(accumulator >= interval) {
                update(interval);
                accumulator -= interval;
            }

            render();

            if(!window.isvSync()) {
                sync();
            }
        }
    }

    protected void update(float interval) {
        gameLogic.update(interval, mouseInput, window);
    }

    protected void cleanup() {
        gameLogic.cleanup();
    }

    private void sync() {
        float loopSlot = 1f / TARGET_FPS;
        double endTime = timer.getLastLoopTime() + loopSlot;
        while(timer.getTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void render() {
        if(timer.getLastLoopTime() - lastFps > 1) {
            lastFps = timer.getLastLoopTime();
            window.setTitle(windowTitle + " - " + fps + " FPS");
            fps = 0;
        }
        fps++;
        window.clear();
        gameLogic.render(window);
        window.update();
    }

    protected void input() {
        mouseInput.input(window);
        gameLogic.input(window, mouseInput);
    }

}
