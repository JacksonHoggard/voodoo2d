<p align="center">
    <img width="100" height="100" src="https://raw.githubusercontent.com/CremBluRay/CremBluRay.github.io/master/assets/images/demo/voodoo2d/voodoo2d.png">
</p>

# Voodoo 2D
A lightweight engine to easily build complex cross-platform 2D games in Java.
<p align="center">
    <img width="800" height="600" src="https://raw.githubusercontent.com/CremBluRay/CremBluRay.github.io/master/assets/images/demo/voodoo2d/canvas.gif">
</p>

Code:
```java
Mesh mesh = Mesh.loadMesh("textures/player.png", 64); // Create new mesh with size of 64x64 pixels
GameObject gameObject = new GameObject(mesh); // Makes a new gameObject from mesh
Animation run = new Animation(gameObject, 0, 3, 6); // Creates new animation with frames 0 - 3 at 6 fps
run.play(); // Play the animation!
```

##### INPUT
```java
if(window.isKeyPressed(GLFW_KEY_D)) { // Checks if "D" key is down
    run.play(); // Plays our animation!
```

##### COLLISION
```java
aabb = new AABB(); // Make a new collision box
aabb.setMin(0.0f, 0.0f); // Set the bottom left point of the boundary
aabb.setMax(1.0f, 0.175f); // Set the top right point of the boundary
```

### TROUBLESHOOTING
**A game object is not drawing to the screen!**
* Make sure you placed the game object in the gameObjects list that will be passed into the render method of the renderer.
* Check to see if the object has been instantiated properly.

**Animations are not diplaying properly**
* Ensure that you have given the proper range of sprites to be displayed from the spritesheet (i.e. 0 - 3).
* Remember that, when playing an animation, all other animations attached to that game object will be stopped.

**Something else isn't working properly**
* Open up an issue! Someone will get to it as soon as possible.

### Current Version
* 1.0-SNAPSHOT (Development Version)

### Areas for improvements / involvement
* A tilemap system
* Anti aliasing
* Audio
* GUI
* Particle System
* Scenes
* Lighting options