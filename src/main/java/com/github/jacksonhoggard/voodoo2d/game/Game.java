package com.github.jacksonhoggard.voodoo2d.game;

import com.github.jacksonhoggard.voodoo2d.engine.*;
import com.github.jacksonhoggard.voodoo2d.engine.gameObject.GameObject;

public class Game implements IGameLogic {


    private final Camera camera;

    private final Renderer renderer;

    private final MapTree mapTree;

    private GameObject[] gameObjects;

    private final Player player;

    private final GUI gui;


    public Game() {
        renderer = new Renderer();
        camera = new Camera();
        player = new Player();
        mapTree = new MapTree();
        gui = new GUI();
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        player.init();
        gui.init();
        mapTree.init();
        gameObjects = new GameObject[] {
                mapTree.getMapBack(),
                mapTree.getMapFront(),
                player,
                mapTree.getMapTop()
        };
    }

    @Override
    public void input(Window window, MouseInput mouseInput) {
        player.input(window);
    }

    @Override
    public void update(float interval, MouseInput mouseInput) {
        // Update camera position
        player.update();
    }

    @Override
    public void render(Window window) {
        renderer.render(window, camera, gameObjects, gui.getGameObjects());
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        for (GameObject gameObject : gameObjects) {
            gameObject.getMesh().cleanUp();
        }
    }

}
