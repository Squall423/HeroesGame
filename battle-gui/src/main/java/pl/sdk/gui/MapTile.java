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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static pl.sdk.GameEngine.AFTER_ATTACK;
import static pl.sdk.GameEngine.AFTER_MOVE;


class MapTile extends StackPane implements PropertyChangeListener {



    private final Rectangle rec;
    private MapTileState state;

    public MapTile() {
        rec = new Rectangle(40, 40);
        rec.setStroke(Color.BLACK);
        state = new MapTileDefaultState();
        handleState();
        getChildren().add(rec);
    }

     void changeState(MapTileState aState) {
        state = aState;
        handleState();
    }

    String getState() {
        return state.currentState();
    }

    void handleState() {
        state.updateBackground(rec);
    }

    void addCreature(String aName, int aAmount, boolean aShouldFlip) {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/graphics/creatures/" + aName +
                ".png")));
        image.setFitHeight(40);
        image.setFitWidth(40);
        if (aShouldFlip) {
            image.setScaleX(-1);
        }
        vbox.getChildren().add(image);
        Text text = new Text(String.valueOf(aAmount));
        text.setFont(new Font(10.0));
        vbox.getChildren().add(text);
        getChildren().add(vbox);

    }


    @Override
    public void propertyChange(PropertyChangeEvent aPropertyChangeEvent) {
        if (aPropertyChangeEvent.getPropertyName().equals(AFTER_MOVE)) {
            if (getState().equals("Move Possible")) {
                afterMove();
            }
        } else if (aPropertyChangeEvent.getPropertyName().equals(AFTER_ATTACK)) {
            if (getState().equals("Attack Possible")) {
                afterAttack();
            }
        }
    }

    private void afterAttack() {
        changeState(new MapTileAfterAttackState());

    }

    private void afterMove() {
        changeState(new MapTileAfterMoveState());
    }
}
