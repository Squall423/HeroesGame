package pl.sdk.gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MapTileAttackPossibleState extends MapTileState {
    private static final String STATE_NAME = "Attack Possible";



    @Override
    void updateBackground(Rectangle aRec) {
        aRec.setFill(Color.RED);
    }

    @Override
    String currentState() {
        return STATE_NAME;
    }
}
