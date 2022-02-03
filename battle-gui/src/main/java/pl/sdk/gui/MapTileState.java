package pl.sdk.gui;

import javafx.scene.shape.Rectangle;

abstract class MapTileState {
    protected MapTile mapTile;

    MapTileState(MapTile aMapTile) {
        mapTile = aMapTile;
    }

    abstract void updateBackground(Rectangle aRec);

    abstract String currentState();
}
