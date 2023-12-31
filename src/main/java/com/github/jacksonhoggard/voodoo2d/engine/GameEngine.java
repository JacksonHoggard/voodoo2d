package com.github.jacksonhoggard.voodoo2d.engine;

import com.github.jacksonhoggard.voodoo2d.engine.animation.AnimationManager;
import com.github.jacksonhoggard.voodoo2d.engine.log.Log;

public class GameEngine implements Runnable {

    private final Window window;
    private final MouseInput mouseInput;
    private final AnimationManager animationManager;
    private final IGameLogic gameLogic;
    private double fpsTimer;
    private int fps;

    /**
     * Creates game using window.
     *
     * @param window Window object
     * @param gameLogic Game logic object
     */
    public GameEngine(Window window, IGameLogic gameLogic) {
        this.window = window;
        this.gameLogic = gameLogic;

        Log.init();
        mouseInput = new MouseInput();
        animationManager = new AnimationManager();
    }

    @Override
    public void run() {
        try {
            init();
            gameLoop();
        } catch (Exception exception) {
            Log.engine().error(exception.getMessage());
        } finally {
            cleanup();
        }
    }

    protected void init() throws Exception {
        Log.engine().info("Starting");
        window.init();
        mouseInput.init(window);
        gameLogic.init(window);
        fps = 0;
    }

    protected void gameLoop() {
        double time = 0;
        while (!window.windowShouldClose()) {
            input();
            update();
            // Fixed update gets called 60 times a second
            time += Timer.getDeltaTime();
            if (time > 0.016) {
                fixedUpdate();
                time = 0;
            }
            render();
        }
    }

    protected void cleanup() {
        Log.engine().info("Cleaning up");
        gameLogic.cleanup();
        Log.engine().cleanup();
        Log.game().cleanup();
    }

    protected void input() {
        mouseInput.input(window);
        gameLogic.input(window, mouseInput);
    }

    protected void update() {
        Timer.calculateDeltaTime();
        gameLogic.update(mouseInput);
    }

    protected void fixedUpdate() {
        animationManager.playAnimations();
        gameLogic.fixedUpdate();
    }

    protected void render() {
        fpsTimer += Timer.getDeltaTime();
        if(fpsTimer > 1) {
            fpsTimer = 0;
            window.setTitle(window.getTitle() + " - " + fps + " FPS");
            fps = 0;
        }
        fps++;

        gameLogic.render(window);
        window.update();
    }
}
