package pl.sdk.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

class BuyCreatureDialog {


    private final CreatureSlider creatureSlider;
    private final String creatureName;
    private int goldCost;
    private int maxValue;
    private int population;
    private Stage dialog;


    public BuyCreatureDialog(String aCreatureName, int aMaxValue, int aGoldCost, int aPopulation) {
        creatureName = aCreatureName;
        creatureSlider = new CreatureSlider(aMaxValue);
        goldCost = aGoldCost;
        maxValue = aMaxValue;
        population = aPopulation;
    }

    void startDialog() {
        HBox centerPane = new HBox();
        HBox bottomPane = new HBox();
        VBox topPane = new VBox();
        Stage dialog = prepareWindow(centerPane, bottomPane, topPane);
        Slider slider = creatureSlider.createSlider();

        prepareConfirmAndCancelButton(bottomPane, slider);
        prepareTop(topPane);
        prepareCenter(centerPane, slider);

        dialog.showAndWait();

    }

    private void prepareCenter(HBox aCenterPane, Slider aSlider) {
        VBox centerPane = new VBox();
        Slider slider = aSlider;

        VBox leftPane = new VBox();

        leftPane.getChildren().add(new Label("Cost Per Troop"));
        leftPane.getChildren().add(getSeparator());

        ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/graphics.icons/money-bag.png")));
        image.setFitHeight(50);
        image.setFitWidth(50);
        leftPane.getChildren().add(image);
        leftPane.getChildren().add(getSeparator());

        Label goldCostLabel = new Label(String.valueOf(goldCost));
        leftPane.getChildren().add(goldCostLabel);
        leftPane.getStyleClass().add("troop-box");
        leftPane.setAlignment(Pos.CENTER);

        HBox statePane = new HBox();
        VBox availablePane = new VBox();
        VBox populationPane = new VBox();
        VBox recruitPane = new VBox();

        Label populationText = new Label("Population");
        populationPane.getChildren().add(populationText);
        populationPane.getChildren().add(getSeparator());
        Label populationValueLabel = new Label(String.valueOf(population));
        populationPane.getChildren().add(populationValueLabel);
        populationPane.setAlignment(Pos.CENTER);
        statePane.getChildren().add(populationPane);

        Label availableText = new Label("Available");
        availablePane.getChildren().add(availableText);
        availablePane.getChildren().add(getSeparator());
        Label maxValueLabel = new Label(String.valueOf(maxValue));
        availablePane.getChildren().add(maxValueLabel);
        availablePane.setAlignment(Pos.CENTER);
        statePane.getChildren().add(availablePane);

        Label recruitTextLabel = new Label("Recruit");
        recruitPane.getChildren().add(recruitTextLabel);
        recruitPane.getChildren().add(getSeparator());
        Label recruitValue = new Label(String.valueOf(0));
        recruitPane.getChildren().add(recruitValue);
        recruitPane.setAlignment(Pos.CENTER);
        statePane.getChildren().add(recruitPane);

        statePane.getStyleClass().add("border");
        centerPane.getChildren().add(statePane);

        VBox sliderPane = new VBox();
        sliderPane.getChildren().add(slider);

        Button maxButton = new Button("MAX");
        maxButton.setPrefWidth(150);
        maxButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> aSlider.setValue(maxValue));
        sliderPane.getChildren().add(maxButton);
        sliderPane.setAlignment(Pos.CENTER);

        centerPane.getChildren().add(sliderPane);

        VBox rightPane = new VBox();
        Label totalCostTest = new Label("Total Cost");
        rightPane.getChildren().add(totalCostTest);
        rightPane.getChildren().add(getSeparator());

        ImageView image2 = new ImageView(new Image(getClass().getResourceAsStream("/graphics.icons/money-bag.png")));
        image2.setFitHeight(50);
        image2.setFitWidth(50);
        rightPane.getChildren().add(image2);
        rightPane.getChildren().add(getSeparator());

        Label totalCost = new Label(String.valueOf(0));
        rightPane.getChildren().add(totalCost);
        rightPane.setAlignment(Pos.CENTER);
        rightPane.getStyleClass().add("troop-box");

        slider.valueProperty()
                .addListener(new ChangeListener<Number>() {
                                 @Override
                                 public void changed(ObservableValue<? extends Number> aObservableValue,
                                                     Number oldValue, Number newValue) {
                                     totalCost.setText(String.valueOf(newValue.intValue() * goldCost));
                                     recruitValue.setText(String.valueOf(newValue.intValue()));
                                 }
                             }
                );

        aCenterPane.getChildren().add(leftPane);
        aCenterPane.getChildren().add(centerPane);
        aCenterPane.getChildren().add(rightPane);
        HBox.setMargin(rightPane, new Insets(0, 0, 100, 40));
        HBox.setMargin(leftPane, new Insets(0, 40, 100, 0));
        aCenterPane.setAlignment(Pos.CENTER);
    }

    private Separator getSeparator() {
        return new Separator();
    }

    int getCreatureAmount() {
        return (int) creatureSlider.getCreatureAmount();
    }

    private void prepareTop(VBox aTopPane) {
        Label recruitLabel = new Label("Recruit " + creatureName);
        recruitLabel.getStyleClass().add("buy-creature-text");
        aTopPane.getChildren().add(recruitLabel);

        ImageView image =
                new ImageView(new Image(getClass().getResourceAsStream("/graphics/creatures/" + creatureName + ".png")));
        image.setFitHeight(200);
        image.setFitWidth(200);
        aTopPane.getChildren().add(image);
        aTopPane.setAlignment(Pos.CENTER);
    }

    private Stage prepareWindow(Pane aCenter, Pane aBottom, Pane aTop) {
        dialog = new Stage();
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane, 1000, 700);
        scene.getStylesheets().add("fxml/main.css");
        dialog.setScene(scene);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Recruit " + creatureName);

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

        Button maxButton = new Button("MAX");
        maxButton.setPrefWidth(200);
        maxButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> aSlider.setValue(maxValue));

        Button cancelButton = new Button("CLOSE");
        cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            aSlider.setValue(0);
            dialog.close();
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
