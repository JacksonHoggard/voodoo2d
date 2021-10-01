package com.github.jacksonhoggard.voodoo2d.engine.mapping;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

// The map class contains the tilemap created from the tmx file
public class Map {

    private static Layer[] layerList;

    // Constructor builds the entire map and loads the map into memory
    public Map(ArrayList<TileSet> tileSets, String tmxTarget) {
        try {
            // Load the xml file as a document.
            File in = new File(tmxTarget);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(in);
            NodeList layers = doc.getElementsByTagName("layer");
            NamedNodeMap map = doc.getElementsByTagName("map").item(0).getAttributes();
            // Scrape information from the document and build map
            int tileWidth = Integer.parseInt(map.getNamedItem("tilewidth").getTextContent());
            int tileHeight = Integer.parseInt(map.getNamedItem("tileheight").getTextContent());
            layerList = new Layer[layers.getLength()];
            // Iterate through each layer
            for(int i = 0; i < layers.getLength(); i++) {
                Node node = layers.item(i);
                Element tiles = (Element) node;
                NamedNodeMap tmp = layers.item(i).getAttributes();
                int layerWidth = Integer.parseInt(tmp.getNamedItem("width").getTextContent());
                int layerHeight = Integer.parseInt(tmp.getNamedItem("height").getTextContent());
                int counter = 0;
                // Store layer data in a 2D matrix
                int[][] tmpMatrix = new int[layerWidth][layerHeight];
                for(int x = 0; x < layerWidth; x++) {
                    for(int y = 0; y < layerHeight; y++) {
                        NamedNodeMap tileData = tiles.getElementsByTagName("tile").item(counter).getAttributes();
                        int gid = Integer.parseInt(tileData.getNamedItem("gid").getTextContent());
                        if(gid > 0)
                            tmpMatrix[x][y] = gid;
                        counter++;
                    }
                }
                layerList[i] = new Layer(tmpMatrix, layerWidth, layerHeight, tileWidth, tileHeight, tileSets);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public Layer[] getLayers() {
        return layerList;
    }

}
