package pl.sdk.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pl.sdk.Creature;
import pl.sdk.GameEngine;


import java.util.ArrayList;
import java.util.List;

public class BattleMapController {

    @FXML
    private GridPane gridMap;

    @FXML
    private Button passButton;
    private final GameEngine gameEngine;

    public BattleMapController() {
        List<Creature> creatures1 = new ArrayList<>();
        creatures1.add(new Creature());
        creatures1.add(new Creature());
        creatures1.add(new Creature());
        List<Creature> creatures2 = new ArrayList<>();
        creatures2.add(new Creature());
        creatures2.add(new Creature());
        creatures2.add(new Creature());
        gameEngine = new GameEngine(creatures1, creatures2);

    }

    @FXML
    void initialize() {
        passButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            gameEngine.pass();
            refreshGui();
        });

        refreshGui();
    }

    private void refreshGui() {
        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 15; y++) {
                MapTile rec = new MapTile();
                gridMap.add(rec, x, y);

                Creature c = gameEngine.get(x, y);
                if (c != null) {
                    rec.addCreature(c.getName());

                    if (c == gameEngine.getActiveCreature()) {
                        rec.setBackground(Color.GREEN);
                    }
                }
            }

        }
    }

}


