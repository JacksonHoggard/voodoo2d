package com.github.crembluray.voodoo2d.engine.gameObject;

import org.joml.Vector2f;

public class AABB {

    private final Vector2f min, max, position;

    public AABB() {
        min = new Vector2f();
        max = new Vector2f();
        position = new Vector2f();
    }

    public boolean collides(AABB other) {
        if(max.x + position.x < other.min.x + other.position.x || min.x + position.x > other.getMax().x + other.position.x)
            return false;
        if(max.y + position.y < other.min.y + other.position.y || min.y + position.y > other.getMax().y + other.position.y)
            return false;
        return true;
    }

    public Vector2f getMin() {
        return min;
    }

    public Vector2f getMax() {
        return max;
    }

    public void setMin(float x, float y) {
        min.x = x;
        min.y = y;
    }

    public void setMax(float x, float y) {
        max.x = x;
        max.y = y;
    }

    public float getHeight() {
        return max.y;
    }

    public float getWidth() {
        return max.x;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
    }
}
