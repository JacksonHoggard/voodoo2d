package com.github.crembluray.voodoo2d.game;

import com.github.crembluray.voodoo2d.engine.*;
import com.github.crembluray.voodoo2d.engine.graphic.Mesh;
import com.github.crembluray.voodoo2d.engine.graphic.SpriteSheet;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

public class Game implements IGameLogic {

    private final Vector2f cameraInc;

    private final Camera camera;

    private final Renderer renderer;

    private GameObject[] gameObjects;

    private Mesh mesh;

    private int spriteStep;

    public static final float CAMERA_POS_STEP = 0.05f;

    public Game() {
        renderer = new Renderer();
        camera = new Camera();
        cameraInc = new Vector2f();
        spriteStep = 0;
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        mesh = Mesh.loadMesh("textures/img.png", 64);
        GameObject gameObject = new GameObject(mesh);
        gameObject.setPosition(0, 0);
        gameObjects = new GameObject[] {gameObject};
    }

    @Override
    public void input(Window window, MouseInput mouseInput) {
        cameraInc.set(0, 0);
        if (window.isKeyPressed(GLFW_KEY_W)) {
            cameraInc.y = 1;
        } else if (window.isKeyPressed(GLFW_KEY_S)) {
            cameraInc.y = -1;
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            cameraInc.x = -1;
        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            cameraInc.x = 1;
        }
        if(window.isKeyPressed(GLFW_KEY_SPACE)) {
            if(mesh.getSpriteSheet().getTextures().length - 1 == spriteStep) {
                spriteStep = 0;
            } else {
                spriteStep++;
            }
            mesh.setCurrentSprite(spriteStep);
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
