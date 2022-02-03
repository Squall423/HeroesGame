package pl.sdk.gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



public class MapTileAfterMoveState extends MapTileState {
    private static final String AFTER_MOVE = "AFTER_MOVE";
    MapTileAfterMoveState(MapTile aMapTile) {
        super(aMapTile);
    }

    @Override
    void updateBackground(Rectangle aRec) {
        aRec.setFill(Color.LIGHTGRAY);
    }

    @Override
    String currentState() {
        return AFTER_MOVE;
    }
}
