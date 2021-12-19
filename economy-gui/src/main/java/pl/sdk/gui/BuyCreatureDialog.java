package pl.sdk.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

class BuyCreatureDialog {

    private CreatureButton creatureButton;
    private Stage dialog;
    private Slider slider;

    BuyCreatureDialog(CreatureButton aCreatureButton) {
        creatureButton = aCreatureButton;
    }

    void startDialog() {
        VBox centerPane = new VBox();
        HBox bottomPane = new HBox();
        HBox topPane = new HBox();
        Stage dialog = prepareWindow(centerPane, bottomPane, topPane);
        slider = createSlider();
        prepareConfirmAndCancelButton(bottomPane, slider);
        prepareTop(topPane, slider);
        centerPane.getChildren().add(slider);

        dialog.showAndWait();

    }

    int getCreatureAmount() {
        return (int) slider.getValue();
    }

    private void prepareTop(HBox aTopPane, Slider aSlider) {
        aTopPane.getChildren().add(new Label("Single Cost: " + "0"));
        Label slideValueLabel = new Label("0");
        aSlider.valueProperty().addListener((slider, aOld, aNew) -> slideValueLabel.setText(String.valueOf((aNew.intValue()))));
        aTopPane.getChildren().add(slideValueLabel);
        aTopPane.getChildren().add(new Label("Purchase Cost: "));

    }

    private Stage prepareWindow(Pane aCenter, Pane aBottom, Pane aTop) {
        dialog = new Stage();
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane, 500, 300);
        scene.getStylesheets().add("fxml/main.css");
        dialog.setScene(scene);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Buying " + creatureButton.getCreatureName());

        pane.setTop(aTop);
        pane.setCenter(aCenter);
        pane.setBottom(aBottom);
        return dialog;
    }

    private void prepareConfirmAndCancelButton(HBox aBottomPane, Slider aSlider) {
        Button okButton = new Button("OK");
        aBottomPane.setAlignment(Pos.CENTER);
        okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> dialog.close());
        okButton.setPrefWidth(200);
        Button cancelButton = new Button("CLOSE");
        cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) ->
        {
            aSlider.setValue(0);
            dialog.close();
        });
        cancelButton.setPrefWidth(200);
        HBox.setHgrow(okButton, Priority.ALWAYS);
        HBox.setHgrow(cancelButton, Priority.ALWAYS);
        aBottomPane.getChildren().add(okButton);
        aBottomPane.getChildren().add(cancelButton);
    }

    private Slider createSlider() {
        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(10);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(10);
        return slider;
    }

}
