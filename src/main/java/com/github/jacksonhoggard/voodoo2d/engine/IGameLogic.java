package com.github.jacksonhoggard.voodoo2d.engine;

public interface IGameLogic {

    void init(Window window) throws Exception;
    
    void input(Window window, MouseInput mouseInput);

    void update(MouseInput mouseInput);
    
    void render(Window window);
    
    void cleanup();
}