package com.github.jacksonhoggard.voodoo2d.game;

import com.github.jacksonhoggard.voodoo2d.engine.Window;
import com.github.jacksonhoggard.voodoo2d.engine.animation.Animation;
import com.github.jacksonhoggard.voodoo2d.engine.gameObject.GameObject;
import com.github.jacksonhoggard.voodoo2d.engine.graphic.Mesh;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;

public class Player extends GameObject {

    private Animation[] animations;
    private Vector2f deltaPosition;

    public static final float PLAYER_POS_STEP = 0.05f;

    public Player() {
        super();
        animations = new Animation[0];
        deltaPosition = new Vector2f(0,0);
        setScale(0.2f);
    }

    public void init() {
        this.setMesh(Mesh.loadMesh("textures/player.png", 64));
        Animation runDown = new Animation(this, 0, 3, 6);
        Animation runLeft = new Animation(this, 4, 7, 6);
        Animation runRight = new Animation(this, 8, 11, 6);
        Animation runUp = new Animation(this, 12, 15, 6);
        animations = new Animation[]{runDown, runLeft, runRight, runUp};
    }

    public void input(Window window) {
        deltaPosition.set(0,0);
        if(window.isKeyPressed(GLFW_KEY_S))
        {
            deltaPosition.y = 1;
            animations[0].play();
        }
        else
            animations[0].stop();
        if(window.isKeyPressed(GLFW_KEY_A))
        {
            deltaPosition.x = 1;
            animations[1].play();
        }
        else
            animations[1].stop();
        if(window.isKeyPressed(GLFW_KEY_D))
        {
            deltaPosition.x = -1;
            animations[2].play();
        }
        else
            animations[2].stop();
        if(window.isKeyPressed(GLFW_KEY_W))
        {
            deltaPosition.y = -1;
            animations[3].play();
        }
        else
            animations[3].stop();
    }

    public void update()
    {
        getPosition().x -= deltaPosition.x * PLAYER_POS_STEP;
        getPosition().y -= deltaPosition.y * PLAYER_POS_STEP;
    }
}
