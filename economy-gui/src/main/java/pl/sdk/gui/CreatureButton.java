package pl.sdk.gui;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import pl.sdk.creatures.Creature;


public class CreatureButton extends Button {


    public CreatureButton(EcoController aEcoController, Creature aCreature) {
        super(aCreature.getName());

        addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            aEcoController.buy(aCreature);
        });
    }
}
