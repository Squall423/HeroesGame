package pl.sdk.gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MapTileActiveCreatureState extends MapTileState {
    private static final String STATE_NAME = "Active Creature";

    MapTileActiveCreatureState(MapTile aMapTile) {
        super(aMapTile);
    }

    @Override
    void updateBackground(Rectangle aRec) {
        aRec.setFill(Color.GREEN);
    }

    @Override
    String currentState() {
        return STATE_NAME;
    }
}
