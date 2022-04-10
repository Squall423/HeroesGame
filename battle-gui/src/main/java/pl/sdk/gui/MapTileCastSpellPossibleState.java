package pl.sdk.gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MapTileCastSpellPossibleState extends MapTileState {
    private static final String STATE_NAME = "Can cast spell";



    @Override
    void updateBackground(Rectangle aRec) {
        aRec.setFill(Color.BLUE);
    }

    @Override
    String currentState() {
        return STATE_NAME;
    }
}
