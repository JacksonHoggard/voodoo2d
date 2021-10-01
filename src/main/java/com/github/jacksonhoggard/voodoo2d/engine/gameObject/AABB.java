package com.github.jacksonhoggard.voodoo2d.engine.gameObject;

import org.joml.Vector2f;

public class AABB {

    /**
     * The bounding box of the object is defined by two vectors.
     * @param center is a 2D coordinate marking the center of the bounding box.
     * @param distance is a pair of values representing the distance between the edges and the center of the bounding box.
     */
    private Vector2f center, distance;

    public AABB(){
        center = new Vector2f();
        distance = new Vector2f();
    }

    public AABB(final Vector2f center, final Vector2f distance){
        this.center = center;
        this.distance = distance;
    }

    /**
     * This method determines whether or not a given AABB collides with this AABB.
     * @param other This is the target of the collision check.
     * @return boolean Whether or not a collision has occurred.
     */
    public boolean intersects(final AABB other) {
        return (( Math.abs(center.x - other.center.x) < distance.x + other.distance.x) ||
                ( Math.abs(center.y - other.center.y) < distance.y + other.distance.y));
    }

    //Getter & Setter methods
    public Vector2f getCenter(){
        return center;
    }

    public void setCenter(final Vector2f center) {
        this.center = center;
    }

    public Vector2f getDistance() {
        return distance;
    }

    public void setDistance(final Vector2f distance) {
        this.distance = distance;
    }
}