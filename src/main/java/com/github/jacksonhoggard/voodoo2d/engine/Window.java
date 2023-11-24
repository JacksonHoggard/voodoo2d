package com.github.jacksonhoggard.voodoo2d.engine;

import static org.lwjgl.glfw.GLFW.*;

import com.github.jacksonhoggard.voodoo2d.engine.log.Log;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.APIUtil;

import java.util.Map;

import static org.lwjgl.glfw.GLFWErrorCallback.getDescription;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private long windowHandle;
    private boolean antiAliasing;
    private boolean vSync;
    private int width;
    private int height;
    private final String title;
    private boolean resized;
    private final WINDOW_MODE windowMode;
    public enum WINDOW_MODE {
        WINDOWED,
        BORDERLESS,
        MAXIMIZED
    }


    /**
     * Creates window with specified size and flags.
     *
     * @param title Title of game window
     * @param width Width of screen
     * @param height Height of screen
     * @param windowMode Windowed 0 | Fullscreen windowed 1 | Fullscreen borderless 2
     * @param vSync Enable VSync
     * @param antiAliasing Enable Anti-Aliasing
     */
	public Window(String title, int width, int height, int windowMode, boolean vSync, boolean antiAliasing) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.vSync = vSync;
        this.antiAliasing = antiAliasing;
        this.resized = false;

        if (windowMode == 2) {
            this.windowMode = WINDOW_MODE.BORDERLESS;
        } else if (windowMode == 1) {
            this.windowMode = WINDOW_MODE.MAXIMIZED;
        } else {
            this.windowMode = WINDOW_MODE.WINDOWED;
        }
    }

    /** Initializes screen for game. */
    public void init() {
        // Set up an error callback
        glfwSetErrorCallback((error, description) -> {
            Map<Integer, String> ERROR_CODES = APIUtil.apiClassTokens((field, value) -> 65536 < value && value < 131072, null, org.lwjgl.glfw.GLFW.class);

            String msg = getDescription(description);
            StackTraceElement[] stack = Thread.currentThread().getStackTrace();

            StringBuilder stackTrace = new StringBuilder();
            for(int i = 4; i < stack.length; ++i) {
                stackTrace.append("\t\t");
                stackTrace.append(stack[i].toString()).append('\n');
            }

            Log.engine().error("[LWJGL] " + ERROR_CODES.get(error) + "error\n\tDescription : " + msg + "\n\tStacktrace  :\n" + stackTrace);
        });

        // Initialize GLFW
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);

        // Ensure window is at least 150x150 (smallest comfortably draggable size)
        height = Math.max(height, 150);
        width = Math.max(width, 150);

        // Get primary monitor to calculate screen position
        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        int scrW = vidMode.width();
        int scrH = vidMode.height();

        // TODO FIX STRETCHING IN BORDERLESS MODE
        // Create window
        if (windowMode == WINDOW_MODE.BORDERLESS) { // Create borderless fullscreen window
            windowHandle = glfwCreateWindow(width, height, title, glfwGetPrimaryMonitor(), NULL);
            glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);
            glfwMaximizeWindow(windowHandle);
        } else if (windowMode == WINDOW_MODE.MAXIMIZED) { // Create fullscreen window
            windowHandle = glfwCreateWindow(scrW/2, scrH/2, title, NULL, NULL);
            glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);
            glfwMaximizeWindow(windowHandle);
        } else { // Create window with specified size
            // Center the window on monitor
            windowHandle = glfwCreateWindow(width, height, title, NULL, NULL);
            glfwSetWindowPos(windowHandle,(scrW - width) / 2,(scrH - height) / 2);
        }

        // Updates game when window is resized
        glfwSetFramebufferSizeCallback(windowHandle, (window, width, height) -> {
            this.width = width;
            this.height = height;
            this.setResized(true);
        });

        // Set up a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(windowHandle, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });

        // Make the OpenGL context current
        glfwMakeContextCurrent(windowHandle);

        // Enable VSync and AntiAliasing if true
        if(isvSync())
            glfwSwapInterval(1);
        if (hasAntiAliasing())
            glfwWindowHint(GLFW_SAMPLES, 4);

        glfwShowWindow(windowHandle);

        GL.createCapabilities();

        // Support for transparencies
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        setClearColor(0.0f,0.0f,0.0f,1.0f);
    }


    // ************************************************************************************************************* //

    // TODO FINISH DOCUMENTATION
    /**
     * Sets clear color of screen when refreshing every frame (default black).
     *
     * @param r Red amount 0-1
     * @param g Green amount 0-1
     * @param b Blue amount 0-1
     * @param alpha Alpha 0-1
     */
    public void setClearColor(float r, float g, float b, float alpha) {
        glClearColor(r, g, b, alpha);
    }

    public void setResized(boolean resized) {
        this.resized = resized;
    }

    public boolean isvSync() {
        return vSync;
    }

    public void setVSync(boolean vSync) {
        this.vSync = vSync;
    }

    public boolean isResized() {
        return resized;
    }

    public boolean windowShouldClose() {
        return glfwWindowShouldClose(windowHandle);
    }

    public void update() {
        glfwSwapBuffers(windowHandle);
        glfwPollEvents();
    }

    public boolean isKeyPressed(int keyCode) {
        return glfwGetKey(windowHandle, keyCode) == GLFW_PRESS;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        glfwSetWindowTitle(windowHandle, title);
    }

    public long getWindowHandle() {
        return windowHandle;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public boolean hasAntiAliasing() {
		return antiAliasing;
	}

	public void setAntiAliasing(boolean antiAliasing) {
		this.antiAliasing = antiAliasing;
	}
}