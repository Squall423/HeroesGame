package pl.sdk.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pl.sdk.creatures.Creature;
import pl.sdk.creatures.NecropolisFactory;

import java.util.ArrayList;
import java.util.List;

public class EcoController {

    @FXML
    HBox heroStateHBox;
    @FXML
    HBox shopsBox;
    @FXML
    Button readyButton;

    private final List<Creature> creatureList;
    private final List<Creature> creatureList2;
    private List<Creature> currentCreatureList;

    public EcoController() {
        creatureList = new ArrayList<>();
        creatureList2 = new ArrayList<>();
        currentCreatureList = creatureList;
    }


    @FXML
    void initialize() {
        refreshGui();
        readyButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            if (currentCreatureList == creatureList) {
                currentCreatureList = creatureList2;

            } else {
                currentCreatureList = creatureList;
            }
            refreshGui();
        });

    }

    void refreshGui() {
        shopsBox.getChildren().clear();
        heroStateHBox.getChildren().clear();

        NecropolisFactory factory = new NecropolisFactory();
        VBox creatureShop = new VBox();

        for (int i = 1; i < 8; i++) {
            creatureShop.getChildren().add(new CreatureButton(this, factory.create(false, i)));
            creatureShop.getChildren().add(new CreatureButton(this, factory.create(true, i)));
        }
        shopsBox.getChildren().add(creatureShop);

        VBox creaturesBox = new VBox();
        currentCreatureList.forEach(c -> creaturesBox.getChildren().add(new Label(c.getName())));
        heroStateHBox.getChildren().add(creaturesBox);

    }

    void buy(Creature aCreature) {
        currentCreatureList.add(aCreature);
    }
}
