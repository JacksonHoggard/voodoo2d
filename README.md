<p align="center">
    <img width="150" height="150" src="https://raw.githubusercontent.com/CremBluRay/CremBluRay.github.io/master/assets/images/demo/voodoo2d/voodoo2d.png">
</p>

# Voodoo 2D
A lightweight engine to easily build complex cross-platform 2D games in Java.

[![Build Status](https://img.shields.io/travis/crembluray/voodoo2d/master?style=flat-square)](https://travis-ci.com/crembluray/voodoo2d)
[![MIT License](https://img.shields.io/github/license/crembluray/voodoo2d?style=flat-square)](https://github.com/crembluray/voodoo2d/blob/master/LICENSE)

<p align="center">
    <img width="800" height="600" src="https://raw.githubusercontent.com/CremBluRay/CremBluRay.github.io/master/assets/images/demo/voodoo2d/canvas.gif">
</p>

## Main Features

* Basic Game Infrastructure (GameLoop, Configuration, ...)
* 2D Render Engine (GUI Components, Spritesheet Animations, Ambient Lighting, Particle System, ...)
* 2D Sound Engine (support for .ogg)
* 2D Physics Engine
* Support for Tile Maps in .tmx format (e.g. made with [Tiled Editor](http://www.mapeditor.org/))
* Player input via Keyboard/Mouse

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
aabb.setCenter(0.5f, 0.0875f); // Set the center point of the boundary
aabb.setDistance(0.5f, 0.0875f); // Set the distance from the center to the edges of the boundary
```

### Maven Dependency
```xml
<dependency>
  <groupId>com.github.crembluray</groupId>
  <artifactId>voodoo2d</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

### CONTRIBUTING
If you'd like to make a contribution, please refer to [CONTRIBUTING.md](https://github.com/CremBluRay/voodoo2d/blob/master/CONTRIBUTING.md) or read the [wiki page](https://github.com/CremBluRay/voodoo2d/wiki/Cloning-Voodoo2D) on how to set up the project

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
* GUI
* Particle System
* Scenes
* Lighting options