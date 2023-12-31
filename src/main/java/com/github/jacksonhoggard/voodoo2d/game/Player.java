package com.github.jacksonhoggard.voodoo2d.game;

import com.github.jacksonhoggard.voodoo2d.engine.Timer;
import com.github.jacksonhoggard.voodoo2d.engine.Window;
import com.github.jacksonhoggard.voodoo2d.engine.animation.Animation;
import com.github.jacksonhoggard.voodoo2d.engine.gameObject.GameObject;
import com.github.jacksonhoggard.voodoo2d.engine.graphic.Mesh;
import com.github.jacksonhoggard.voodoo2d.engine.log.Log;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

public class Player extends GameObject {

    private Animation[] animations;
    private Vector2f deltaPosition;
    private Vector2f lastPosition;
    private Vector2f playerPos;
    private final float playerSpeed = 0.75F;

    public Player() {
        super();
        animations = new Animation[0];
        deltaPosition = new Vector2f(0,0);
        lastPosition = new Vector2f(0, 0);
        setScale(0.2f);
    }

    public void init() {
        this.setMesh(Mesh.loadMesh("textures/player.png", 64));
        Animation runDown = new Animation(this, 0, 3, 6);
        Animation runLeft = new Animation(this, 4, 7, 6);
        Animation runRight = new Animation(this, 8, 11, 6);
        Animation runUp = new Animation(this, 12, 15, 6);
        animations = new Animation[]{runDown, runLeft, runRight, runUp};
        playerPos = new Vector2f(0,0);
    }

    public void input(Window window) {
        deltaPosition.set(0,0);

        if(window.isKeyPressed(GLFW_KEY_S)) {
            deltaPosition.y = -1F;
            animations[0].play();
        } else animations[0].stop();

        if(window.isKeyPressed(GLFW_KEY_A)) {
            deltaPosition.x = -1F;
            animations[1].play();
        } else animations[1].stop();

        if(window.isKeyPressed(GLFW_KEY_D)) {
            deltaPosition.x = 1F;
            animations[2].play();
        } else animations[2].stop();

        if(window.isKeyPressed(GLFW_KEY_W)) {
            deltaPosition.y = 1F;
            animations[3].play();
        } else animations[3].stop();
    }

    public void update() {
        playerPos.y += ((deltaPosition.y * playerSpeed) * Timer.getDeltaTime());
        playerPos.x += ((deltaPosition.x * playerSpeed) * Timer.getDeltaTime());
        this.setPosition(playerPos.x, playerPos.y);
        if(!lastPosition.equals(playerPos) && deltaPosition.x == 0 && deltaPosition.y == 0) {
            Log.game().debug("Player pos: (x:" + playerPos.x + ", y: " + playerPos.y + ")");
            lastPosition.set(playerPos);
        }
    }
}
