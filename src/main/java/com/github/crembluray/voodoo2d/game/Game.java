package com.github.crembluray.voodoo2d.game;

import com.github.crembluray.voodoo2d.engine.*;
import com.github.crembluray.voodoo2d.engine.graph.Mesh;
import com.github.crembluray.voodoo2d.engine.graph.Texture;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

public class Game implements IGameLogic {

    private static final float MOUSE_SENSITIVITY = 0.2f;

    private final Vector2f cameraInc;

    private final Camera camera;

    private final Renderer renderer;

    private GameObject[] gameObjects;

    public static final float CAMERA_POS_STEP = 0.05f;

    public Game() {
        renderer = new Renderer();
        camera = new Camera();
        cameraInc = new Vector2f();
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        // Create the Mesh
        float[] positions = new float[]{
                // V0
                -0.5f, 0.5f, 0.5f,
                // V1
                -0.5f, -0.5f, 0.5f,
                // V2
                0.5f, -0.5f, 0.5f,
                // V3
                0.5f, 0.5f, 0.5f,
                // V4
                -0.5f, 0.5f, -0.5f,
                // V5
                0.5f, 0.5f, -0.5f,
                // V6
                -0.5f, -0.5f, -0.5f,
                // V7
                0.5f, -0.5f, -0.5f,
        };
        float[] textCoords = new float[]{
                0.0f, 0.0f,
                0.0f, 0.5f,
                0.5f, 0.5f,
                0.5f, 0.0f,

                0.0f, 0.0f,
                0.5f, 0.0f,
                0.0f, 0.5f,
                0.5f, 0.5f,
        };
        int[] indices = new int[]{
            0, 1, 3, 3, 1, 2,
        };
        Texture texture = new Texture("textures/img.png");
        Mesh mesh = new Mesh(positions, textCoords, indices, texture);
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
