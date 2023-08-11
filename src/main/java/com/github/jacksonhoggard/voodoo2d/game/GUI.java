package com.github.jacksonhoggard.voodoo2d.game;

import com.github.jacksonhoggard.voodoo2d.engine.MouseInput;
import com.github.jacksonhoggard.voodoo2d.engine.Window;
import com.github.jacksonhoggard.voodoo2d.engine.gameObject.GameObject;
import com.github.jacksonhoggard.voodoo2d.engine.gui.Button;
import com.github.jacksonhoggard.voodoo2d.engine.gui.IGui;

public class GUI implements IGui {

    private GameObject[] elements;

    @Override
    public GameObject[] getGameObjects() {
        return elements;
    }

    @Override
    public void init() {
        elements = new GameObject[]{
//                new Button();
        };
    }

    @Override
    public void input(Window window, MouseInput mouseInput) {

    }
}
