package com.github.crembluray.voodoo2d.game;

import com.github.crembluray.voodoo2d.engine.gameObject.GameObject;
import com.github.crembluray.voodoo2d.engine.mapping.MapHost;

public class MapTree {

    private MapHost map;
    private GameObject mapBack;
    private GameObject mapFront;
    private GameObject mapTop;

    public MapTree() {
        map = new MapHost("src\\main\\resources\\maps\\example.tmx");
    }

    public void init() {
        map.init();
        map.setScale(3.0f);
        mapBack = map.getMap().getLayers()[0].asGameObject();
        mapFront = map.getMap().getLayers()[1].asGameObject();
        mapTop = map.getMap().getLayers()[2].asGameObject();
    }


    public GameObject getMapBack() {
        return mapBack;
    }

    public GameObject getMapFront() {
        return mapFront;
    }

    public GameObject getMapTop() {
        return mapTop;
    }
}
