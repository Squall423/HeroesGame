package pl.sdk.gui;


import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class MapTile extends StackPane {

    private final Rectangle rec;

    public MapTile() {
        rec = new Rectangle(40, 40, Color.WHITE);
        rec.setStroke(Color.BLACK);
        getChildren().add(rec);
    }

    void addCreature(String aName) {
        getChildren().add(new Label(aName));
    }

    void setBackground(Color aColor) {
        rec.setFill(aColor);
    }
}