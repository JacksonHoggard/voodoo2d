package com.github.jacksonhoggard.voodoo2d.engine.gui;

import com.github.jacksonhoggard.voodoo2d.engine.MouseInput;
import com.github.jacksonhoggard.voodoo2d.engine.Window;
import com.github.jacksonhoggard.voodoo2d.engine.gameObject.AABB;
import com.github.jacksonhoggard.voodoo2d.engine.gameObject.GameObject;
import com.github.jacksonhoggard.voodoo2d.engine.graphic.Mesh;
import org.joml.Vector2f;

public class Button extends GameObject {

    public enum States {
        IDLE,
        SELECTED,
        CLICKED
    }

    private final AABB aabb;

    public Button(Mesh mesh, Vector2f size) {
        super(mesh);
        aabb = new AABB(new Vector2f(this.getPosition().x, this.getPosition().y), size);
    }

    public void update(int selectedState) {
        this.getMesh().setCurrentFrame(selectedState);
    }

    public boolean isHovering(Window window) {
        Vector2f worldCoords = MouseInput.calcWorldCoords(window);
        aabb.setCenter(new Vector2f(this.getPosition().x, this.getPosition().y));
        AABB cursor = new AABB(worldCoords, new Vector2f(0, 0));

        return cursor.intersects(aabb);
    }
}
