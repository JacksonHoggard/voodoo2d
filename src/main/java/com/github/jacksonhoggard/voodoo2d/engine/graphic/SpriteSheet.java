package com.github.jacksonhoggard.voodoo2d.engine.graphic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet {

    public Texture[] textures;

    public SpriteSheet(String filename, int size) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(filename));
        } catch(IOException e) {
            e.printStackTrace();
        }

        int rows = img.getHeight() / size;
        int cols = img.getWidth() / size;

        BufferedImage[] sprites = new BufferedImage[rows * cols];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                sprites[(i * cols) + j] = img.getSubimage(
                  j * size,
                  i * size,
                  size,
                  size
                );
            }
        }

        try {
            textures = Texture.loadTexture(sprites);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public SpriteSheet(String filename) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(filename));
        } catch(IOException e) {
            e.printStackTrace();
        }

        int rows = img.getHeight();
        int cols = img.getWidth();

        BufferedImage[] sprites = new BufferedImage[1];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                sprites[(i * cols) + j] = img.getSubimage(
                        j * img.getWidth(),
                        i * img.getHeight(),
                        img.getWidth(),
                        img.getHeight()
                );
            }
        }

        try {
            textures = Texture.loadTexture(sprites);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Texture[] getTextures() {
        return textures;
    }

}
