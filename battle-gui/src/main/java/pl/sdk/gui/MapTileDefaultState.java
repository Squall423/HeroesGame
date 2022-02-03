package pl.sdk.gui;


import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.Color.WHITE;

public class MapTileDefaultState extends MapTileState {
    private static final String STATE_NAME = "Default";

    public MapTileDefaultState(MapTile aMapTile) {
        super(aMapTile);

    }

    @Override
    void updateBackground(Rectangle aRec) {
        aRec.setFill(WHITE);
    }


    @Override
    String currentState() {
        return STATE_NAME;
    }
}
