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

    private MapHost map = new MapHost("src\\main\\resources\\maps\\example.tmx");

    private GameObject[] gameObjects;

    private Animation[] animations;

    private AudioSource audioSource;

    public static final float PLAYER_POS_STEP = 0.05f;

    public Game() {
        renderer = new Renderer();
        camera = new Camera();
        cameraInc = new Vector2f();
        animations = new Animation[0];
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        map.init();
        AudioManager.init();
        audioSource = new AudioSource("audio/bounce.ogg");
        audioSource.setLooping(true);
        Mesh mesh = Mesh.loadMesh("textures/player.png", 64);
        Mesh mesh1 = Mesh.loadMesh("textures/sheet1.png", 32);
        GameObject gameObject1 = new GameObject(mesh1);
        GameObject gameObject = new GameObject(mesh);
        GameObject mapBack = map.getMap().getLayers()[0].asGameObject();
        GameObject mapFront = map.getMap().getLayers()[1].asGameObject();
        GameObject mapTop = map.getMap().getLayers()[2].asGameObject();
        map.setScale(3.0f);
        gameObject.setScale(0.2f);
        gameObject1.getMesh().setCurrentFrame(1);
        gameObject1.setPosition(0, -1.5f);
        Animation runDown = new Animation(gameObject, 0, 3, 6);
        Animation runLeft = new Animation(gameObject, 4, 7, 6);
        Animation runRight = new Animation(gameObject, 8, 11, 6);
        Animation runUp = new Animation(gameObject, 12, 15, 6);
        animations = new Animation[]{runDown, runLeft, runRight, runUp};
        gameObjects = new GameObject[] {
                mapBack,
                mapFront,
                gameObject,
                mapTop
        };
    }

    @Override
    public void input(Window window, MouseInput mouseInput) {
        cameraInc.set(0, 0);
        if(window.isKeyPressed(GLFW_KEY_S)) {
            animations[0].play();
            cameraInc.y = 1;
        }
        if(window.isKeyPressed(GLFW_KEY_A)) {
            animations[1].play();
            cameraInc.x = 1;
        }
        if(window.isKeyPressed(GLFW_KEY_D)) {
            animations[2].play();
            cameraInc.x = -1;
        }
        if(window.isKeyPressed(GLFW_KEY_W)) {
            animations[3].play();
            cameraInc.y = -1;
        }
        if(window.isKeyPressed(GLFW_KEY_SPACE)) {
            if(audioSource.isPlaying()) {
                audioSource.stop();
            } else {
                audioSource.play();
            }
        }
    }

    @Override
    public void update(float interval, MouseInput mouseInput) {
        // Update camera position
        gameObjects[2].getPosition().x -= cameraInc.x * PLAYER_POS_STEP;
        gameObjects[2].getPosition().y -= cameraInc.y * PLAYER_POS_STEP;
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
        AudioManager.cleanup();
    }

}
