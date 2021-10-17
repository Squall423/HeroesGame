package pl.sdk.gui;

import javafx.geometry.Pos;
import javafx.scene.Node;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class CreatureButton extends Button {

//    private final String creatureName;
//    private Stage dialog;


//    public CreatureButton(EcoController aEcoController, NecropolisFactory aFactory, boolean aUpgraded, int aTier) {
//        super(aFactory.create(aUpgraded, aTier, 1).getName());
//        creatureName = aFactory.create(aUpgraded, aTier, 1).getName();
//        getStyleClass().add("creatureButton");


//        addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
//            int amount = startDialogAndGetCreatureAmount();
//            if (amount != 0) {
//                aEcoController.buy(aFactory.create(aUpgraded, aTier, amount));
//            }
//            aEcoController.refreshGui();
//        });
//    }

//    private int startDialogAndGetCreatureAmount() {
//        VBox centerPane = new VBox();
//        HBox bottomPane = new HBox();
//        HBox topPane = new HBox();
//        Stage dialog = prepareWindow(centerPane, bottomPane, topPane);
//        Slider slider = createSlider();
//        prepareConfirmAndCancelButton(bottomPane, slider);
//        prepareTop(topPane, slider);
//        centerPane.getChildren().add(slider);
//
//        dialog.showAndWait();
//
//        return (int) slider.getValue();
//    }

    private void prepareTop(HBox aTopPane, Slider aSlider) {
        aTopPane.getChildren().add(new Label("Single Cost: " + "0"));
        Label slideValueLabel = new Label("0");
        aSlider.valueProperty().addListener((slider, aOld, aNew) -> slideValueLabel.setText(String.valueOf((aNew.intValue()))));
        aTopPane.getChildren().add(slideValueLabel);
        aTopPane.getChildren().add(new Label ("Purchase Cost: "));

    }

//    private Stage prepareWindow(Pane aCenter, Pane aBottom, Pane aTop) {
//        dialog = new Stage();
//        BorderPane pane = new BorderPane();
//        Scene scene = new Scene(pane, 500, 300);
//        scene.getStylesheets().add("fxml/main.css");
//        dialog.setScene(scene);
//        dialog.initOwner(this.getScene().getWindow());
//        dialog.initModality(Modality.APPLICATION_MODAL);
//        dialog.setTitle("Buying " + creatureName);
//
//        pane.setTop(aTop);
//        pane.setCenter(aCenter);
//        pane.setBottom(aBottom);
//        return dialog;
//    }

//    private void prepareConfirmAndCancelButton(HBox aBottomPane, Slider aSlider) {
//        Button okButton = new Button("OK");
//        aBottomPane.setAlignment(Pos.CENTER);
//        okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> dialog.close());
//        okButton.setPrefWidth(200);
//        Button cancelButton = new Button("CLOSE");
//        cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) ->
//        {
//            aSlider.setValue(0);
//            dialog.close();
//        });
//        cancelButton.setPrefWidth(200);
//        HBox.setHgrow(okButton, Priority.ALWAYS);
//        HBox.setHgrow(cancelButton, Priority.ALWAYS);
//        aBottomPane.getChildren().add(okButton);
//        aBottomPane.getChildren().add(cancelButton);
//    }

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
