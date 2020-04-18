package com.github.crembluray.voodoo2d.engine;

public interface IGameLogic {

    void init(Window window) throws Exception;
    
    void input(Window window, MouseInput mouseInput);

    void update(float interval, MouseInput mouseInput);
    
    void render(Window window);
    
    void cleanup();
}