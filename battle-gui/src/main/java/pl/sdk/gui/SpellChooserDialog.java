package pl.sdk.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.sdk.spells.AbstractSpell;

import java.util.List;
import java.util.function.Consumer;

public class SpellChooserDialog {
    private final List<AbstractSpell> spells;
    private final int mana;
    private Stage dialog;
    private ToggleGroup spellChooser;

    SpellChooserDialog(List<AbstractSpell> aSpells, int aMana) {
        spells = aSpells;
        mana = aMana;

    }

    void startDialog(Consumer<AbstractSpell> aControllerFunction) {
        HBox centerPane = new HBox();
        HBox bottomPane = new HBox();
        VBox topPane = new VBox();
        prepareWindow(centerPane, bottomPane, topPane);

        prepareTop(topPane);
        prepareCenter(centerPane);
        prepareConfirmAndCancelButton(bottomPane, aControllerFunction);
        dialog.showAndWait();
    }

    private void prepareCenter(HBox aCenterPane) {
        spellChooser = new ToggleGroup();
        spells.forEach(s -> {
            RadioButton radio = new RadioButton(s.getName());
            radio.setToggleGroup(spellChooser);
            radio.setUserData(s);
            aCenterPane.getChildren().add(radio);
        });
    }

    private void prepareTop(VBox aTopPane) {
        aTopPane.getChildren().add(new Label("Mana: " + mana));
    }

    private void prepareWindow(Pane aCenter, Pane aBottom, Pane aTop) {
        dialog = new Stage();
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane, 900, 600);
        scene.getStylesheets().add("fxml/main.css");
        dialog.setScene(scene);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Choose spell to cast ");

        pane.setTop(aTop);
        pane.setCenter(aCenter);
        pane.setBottom(aBottom);

    }

    private void prepareConfirmAndCancelButton(HBox aBottomPane, Consumer<AbstractSpell> aPrepareControllerFunction) {
        Button okButton = new Button("OK");
        aBottomPane.setAlignment(Pos.CENTER);
        okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                    dialog.close();
                    AbstractSpell chosenSpell = (AbstractSpell) spellChooser.getSelectedToggle().getUserData();
                    aPrepareControllerFunction.accept(chosenSpell);
                });

        okButton.setPrefWidth(200);
        Button cancelButton = new Button("CLOSE");
        cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> { dialog.close();
        });
        cancelButton.setPrefWidth(200);
        HBox.setHgrow(okButton, Priority.ALWAYS);
        HBox.setHgrow(cancelButton, Priority.ALWAYS);
        aBottomPane.getChildren().add(okButton);
        aBottomPane.getChildren().add(cancelButton);
        HBox.setMargin(okButton, new Insets(20, 10, 150, 0));
        HBox.setMargin(cancelButton, new Insets(20, 0, 150, 10));
    }
}
