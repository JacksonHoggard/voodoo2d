package com.github.crembluray.voodoo2d.engine;

import com.github.crembluray.voodoo2d.engine.graph.Mesh;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class GameObject {

    private final Mesh mesh;
    
    private final Vector2f position;
    
    private float scale;

    private final Vector3f rotation;

    public GameObject(Mesh mesh) {
        this.mesh = mesh;
        position = new Vector2f();
        scale = 1;
        rotation = new Vector3f();
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
}