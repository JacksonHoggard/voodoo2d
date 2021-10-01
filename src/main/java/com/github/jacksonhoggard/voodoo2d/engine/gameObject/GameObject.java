package com.github.jacksonhoggard.voodoo2d.engine.gameObject;

import com.github.jacksonhoggard.voodoo2d.engine.animation.Animation;
import com.github.jacksonhoggard.voodoo2d.engine.graphic.Mesh;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;

public class GameObject {

    private static final ArrayList<GameObject> gameObjects = new ArrayList<>();

    private Mesh mesh;
    
    private final Vector2f position;
    
    private float scale;

    private final Vector3f rotation;

    private Animation animation;

    public GameObject() {
        position = new Vector2f();
        scale = 1;
        rotation = new Vector3f();
        animation = new Animation(this, 0, 0, 0);
        gameObjects.add(this);
    }

    public GameObject(Mesh mesh) {
        this.mesh = mesh;
        animation = new Animation(this, 0, 0, 0);
        position = new Vector2f();
        scale = 1;
        rotation = new Vector3f();
        gameObjects.add(this);
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        this.position.x = x;
        this.position.y = y;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(float x, float y, float z) {
        this.rotation.x = x;
        this.rotation.y = y;
        this.rotation.z = z;
    }

    public Mesh getMesh() {
        return mesh;
    }

    protected void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public static ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }
}