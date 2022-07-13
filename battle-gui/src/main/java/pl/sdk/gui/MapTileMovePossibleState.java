package pl.sdk.gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MapTileMovePossibleState extends MapTileState {

    private static final String AFTER_MOVE = "Move Possible";



    @Override
    void updateBackground(Rectangle aRec) {
        aRec.setFill(Color.GRAY);
    }

    @Override
    String currentState() {
        return AFTER_MOVE;
    }
}
