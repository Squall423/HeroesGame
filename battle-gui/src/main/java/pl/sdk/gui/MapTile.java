package pl.sdk.gui;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

class MapTile extends StackPane {

    private final Rectangle rec;

    public MapTile() {
        rec = new Rectangle(40, 40, Color.WHITE);
        rec.setStroke(Color.BLACK);
        getChildren().add(rec);
    }

    void addCreature(String aName, String currentHp) {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/graphics/creatures/" + aName +
                ".png")));
        image.setFitHeight(40);
        image.setFitWidth(40);
        vbox.getChildren().add(image);
        Text text = new Text(currentHp);
        text.setFont(new Font(10.0));
        vbox.getChildren().add(text);
        getChildren().add(vbox);

    }

    void setBackground(Color aColor) {
        rec.setFill(aColor);
    }
}
