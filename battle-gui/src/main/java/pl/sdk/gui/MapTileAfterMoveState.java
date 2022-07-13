package pl.sdk.gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



public class MapTileAfterMoveState extends MapTileState {
    private static final String AFTER_MOVE = "AFTER_MOVE";


    @Override
    void updateBackground(Rectangle aRec) {
        aRec.setFill(Color.LIGHTGRAY);
    }

    @Override
    String currentState() {
        return AFTER_MOVE;
    }
}
