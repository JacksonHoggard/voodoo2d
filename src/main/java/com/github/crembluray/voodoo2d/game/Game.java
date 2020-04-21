package com.github.crembluray.voodoo2d.game;

import com.github.crembluray.voodoo2d.engine.*;
import com.github.crembluray.voodoo2d.engine.animation.Animation;
import com.github.crembluray.voodoo2d.engine.animation.AnimationManager;
import com.github.crembluray.voodoo2d.engine.graphic.Mesh;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

public class Game implements IGameLogic {

    private final Vector2f cameraInc;

    private final Camera camera;

    private final Renderer renderer;

    private GameObject[] gameObjects;

    private Mesh mesh;

    private Animation[] animations;

    private int lastKey;

    public static final float CAMERA_POS_STEP = 0.05f;

    public Game() {
        renderer = new Renderer();
        camera = new Camera();
        cameraInc = new Vector2f();
        animations = new Animation[0];
        lastKey = GLFW_KEY_S;
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        mesh = Mesh.loadMesh("textures/player.png", 64);
        GameObject gameObject = new GameObject(mesh);
        gameObject.setScale(0.2f);
        Animation runDown = new Animation(gameObject, 0, 3, 6);
        Animation runLeft = new Animation(gameObject, 4, 7, 6);
        Animation runRight = new Animation(gameObject, 8, 11, 6);
        Animation runUp = new Animation(gameObject, 12, 15, 6);
        animations = new Animation[]{runDown, runLeft, runRight, runUp};
        gameObjects = new GameObject[] {gameObject};
    }

    @Override
    public void input(Window window, MouseInput mouseInput) {
        cameraInc.set(0, 0);
        if(window.isKeyPressed(GLFW_KEY_S)) {
            animations[0].play();
            cameraInc.y = 1;
            lastKey = GLFW_KEY_S;
        } else if(lastKey == GLFW_KEY_S)
            animations[0].stop();
        if(window.isKeyPressed(GLFW_KEY_A)) {
            animations[1].play();
            cameraInc.x = 1;
            lastKey = GLFW_KEY_A;
        } else if(lastKey == GLFW_KEY_A)
            animations[1].stop();
        if(window.isKeyPressed(GLFW_KEY_D)) {
            animations[2].play();
            cameraInc.x = -1;
            lastKey = GLFW_KEY_D;
        } else if(lastKey == GLFW_KEY_D)
            animations[2].stop();
        if(window.isKeyPressed(GLFW_KEY_W)) {
            animations[3].play();
            cameraInc.y = -1;
            lastKey = GLFW_KEY_W;
        } else if(lastKey == GLFW_KEY_W)
            animations[3].stop();
        if(!window.isKeyPressed(GLFW_KEY_W) &&
           !window.isKeyPressed(GLFW_KEY_A) &&
           !window.isKeyPressed(GLFW_KEY_S) &&
           !window.isKeyPressed(GLFW_KEY_D)) {
            for(Animation a : animations) a.pause();
        }
    }

    @Override
    public void update(float interval, MouseInput mouseInput) {
        // Update camera position
        camera.movePosition(cameraInc.x * CAMERA_POS_STEP, cameraInc.y * CAMERA_POS_STEP);
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
