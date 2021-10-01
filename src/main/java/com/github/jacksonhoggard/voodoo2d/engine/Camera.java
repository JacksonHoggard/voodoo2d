package com.github.jacksonhoggard.voodoo2d.engine;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera {

    private final Vector2f position;

    private final Vector3f rotation;

    public Camera() {
        position = new Vector2f();
        rotation = new Vector3f();
    }

    public Camera(Vector2f position) {
        this.position = position;
        rotation = new Vector3f();
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
    }

    public void setPosition(Vector2f position) {
        this.position.x = position.x;
        this.position.y = position.y;
    }

    public void movePosition(float offsetX, float offsetY) {
//        if ( offsetZ != 0 ) {
//            position.x += (float)Math.sin(Math.toRadians(rotation.y)) * -1.0f * offsetZ;
//        }
//        if ( offsetX != 0) {
//            position.x += (float)Math.sin(Math.toRadians(rotation.y - 90)) * -1.0f * offsetX;
//        }
        position.x += offsetX;
        position.y += offsetY;
    }

    public Vector3f getRotation() {
        return rotation;
    }
}