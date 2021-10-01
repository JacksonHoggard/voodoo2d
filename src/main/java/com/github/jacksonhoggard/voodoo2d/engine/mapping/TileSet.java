package com.github.jacksonhoggard.voodoo2d.engine.mapping;

public class TileSet {
    private int firstGID, lastGID, tileWidth, tileHeight, imageWidth, imageHeight, tileAmountWidth;
    private int[][] GIDs;
    private String name, source;

    public TileSet(int firstGID, String name, int tileWidth, int tileHeight, String source, int imageWidth, int imageHeight) {
        this.firstGID = firstGID;
        this.name = name;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.source = source;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        GIDs = new int[imageWidth / tileWidth][imageHeight / tileHeight];
        int counter = 0;
        for(int y = 0; y < imageHeight / tileHeight; y++) {
            for(int x = 0; x < imageWidth / tileWidth; x++) {
                GIDs[x][y] = firstGID + counter;
                counter++;
            }
        }
        tileAmountWidth = (int)Math.floor(imageWidth / tileWidth);
        lastGID = (int)(tileAmountWidth * Math.floor(imageHeight / tileHeight));
    }

    public int getFirstGID() {
        return this.firstGID;
    }

    public void setFirstGID(int firstGID) {
        this.firstGID = firstGID;
    }

    public int getLastGID() {
        return this.lastGID;
    }

    public void setLastGID(int lastGID) {
        this.lastGID = lastGID;
    }

    public int getTileWidth() {
        return this.tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return this.tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    public int getImageWidth() {
        return this.imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return this.imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getTileAmountWidth() {
        return this.tileAmountWidth;
    }

    public void setTileAmountWidth(int tileAmountWidth) {
        this.tileAmountWidth = tileAmountWidth;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int[][] getGIDs() {
        return GIDs;
    }
}