package pl.sdk.gui;

import javafx.scene.shape.Rectangle;

abstract class MapTileState {
    protected MapTile mapTile;


    abstract void updateBackground(Rectangle aRec);

    abstract String currentState();
}
