package pl.sdk.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.sdk.creatures.EconomyCreature;


public class StatisticCreatureDialog {


    private CreatureButton creatureButton;
    private Stage dialog;
    private EconomyCreature economyCreature;

    public StatisticCreatureDialog(CreatureButton aCreatureButton, EconomyCreature aEconomyCreature) {
        creatureButton = aCreatureButton;
        economyCreature = aEconomyCreature;
    }

    void startDialog() {
        HBox topPane = new HBox();
        VBox leftPane = new VBox();
        VBox rightPane = new VBox();
        HBox bottomPane = new HBox();
        Stage dialog = prepareWindow(topPane, rightPane, leftPane, bottomPane);
        prepareTop(topPane);
        prepareLeft(leftPane);
        prepareRight(rightPane);
        prepareBottom(bottomPane);
        dialog.showAndWait();
    }

    private void prepareTop(HBox aTopPane) {
        Label creatureName = new Label(economyCreature.getName());
        creatureName.getStyleClass().add("desc-text");
        aTopPane.getChildren().add(creatureName);
        aTopPane.setAlignment(Pos.CENTER);

    }

    private void prepareBottom(HBox aBottom) {
        Label description = new Label(economyCreature.getDescription());
        description.getStyleClass().add("desc-text");
        aBottom.getChildren().add(description);

    }

    private void prepareLeft(VBox aLeftPane) {
        ImageView image =
                new ImageView(new Image(getClass().getResourceAsStream("/graphics/creatures/" + economyCreature.getName() + ".png")));
        image.setFitHeight(100);
        image.setFitWidth(100);
        aLeftPane.getChildren().add(image);
    }

    private void prepareRight(VBox aRightPane) {
        aRightPane.getChildren().add(new Label("Attack: " + economyCreature.getAttack()));
        aRightPane.getChildren().add(new Label("Armor: " + economyCreature.getArmor()));
        aRightPane.getChildren().add(new Label("Damage: " + economyCreature.getDamage()));
        aRightPane.getChildren().add(new Label("Health: " + economyCreature.getMaxHp()));
        aRightPane.getChildren().add(new Label("Speed: " + economyCreature.getMoveRange()));
        aRightPane.setPadding(new Insets(0, 88, 30, 0));
    }

    private Stage prepareWindow(Pane aTop, Pane aRight, Pane aLeft, Pane aBottom) {
        dialog = new Stage();

        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane, 500, 300);
        scene.getStylesheets().add("fxml/main.css");

        dialog.setScene(scene);
        dialog.initOwner(creatureButton.getScene().getWindow());
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Statistic " + economyCreature.getName());

        pane.setTop(aTop);
        pane.setRight(aRight);
        pane.setLeft(aLeft);
        pane.setBottom(aBottom);
        return dialog;
    }
}
