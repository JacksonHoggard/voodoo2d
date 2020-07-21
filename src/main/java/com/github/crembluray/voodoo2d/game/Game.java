package com.github.crembluray.voodoo2d.game;

import com.github.crembluray.voodoo2d.engine.*;
import com.github.crembluray.voodoo2d.engine.animation.Animation;
import com.github.crembluray.voodoo2d.engine.audio.AudioManager;
import com.github.crembluray.voodoo2d.engine.audio.AudioSource;
import com.github.crembluray.voodoo2d.engine.gameObject.GameObject;
import com.github.crembluray.voodoo2d.engine.graphic.Mesh;
import com.github.crembluray.voodoo2d.engine.mapping.MapHost;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

public class Game implements IGameLogic {

    private final Vector2f cameraInc;

    private final Camera camera;

    private final Renderer renderer;

    private MapTree mapTree;

    private GameObject[] gameObjects;

    private Player player;

    public static final float PLAYER_POS_STEP = 0.05f;

    public Game() {
        renderer = new Renderer();
        camera = new Camera();
        cameraInc = new Vector2f();
        player = new Player();
        mapTree = new MapTree();
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        player.init();
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
        cameraInc.set(0, 0);
        player.input(window, mouseInput);
        if(window.isKeyPressed(GLFW_KEY_S))
            cameraInc.y = 1;
        if(window.isKeyPressed(GLFW_KEY_A))
            cameraInc.x = 1;
        if(window.isKeyPressed(GLFW_KEY_D))
            cameraInc.x = -1;
        if(window.isKeyPressed(GLFW_KEY_W))
            cameraInc.y = -1;
    }

    @Override
    public void update(float interval, MouseInput mouseInput) {
        // Update camera position
        player.getPosition().x -= cameraInc.x * PLAYER_POS_STEP;
        player.getPosition().y -= cameraInc.y * PLAYER_POS_STEP;
    }

    @Override
    public void render(Window window) {
        renderer.render(window, camera, gameObjects);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        for (GameObject gameObject : gameObjects) {
            gameObject.getMesh().cleanUp();
        }
    }

}
