package pl.sdk.gui;

import com.google.common.collect.Range;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pl.sdk.*;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class BattleMapController implements PropertyChangeListener {

    @FXML
    private GridPane gridMap;

    @FXML
    private Button passButton;
    private final GameEngine gameEngine;

    public BattleMapController() {
        List<Creature> notUpgradedCreatures = new ArrayList<>();

        Creature c;
        c = new Creature.Builder()
                .name("Skeleton")
                .armor(6)
                .attack(5)
                .damage(Range.closed(1, 3))
                .maxHp(6)
                .moveRange(4)
                
                .build();
        notUpgradedCreatures.add(c);
        c = new Creature.Builder()
                .name("Walking Dead")
                .armor(5)
                .attack(5)
                .damage(Range.closed(2, 3))
                .maxHp(15)
                .moveRange(3)
                .build();
        notUpgradedCreatures.add(c);
        c = new Creature.Builder()
                .name("Wight")
                .armor(7)
                .attack(7)
                .damage(Range.closed(3, 5))
                .maxHp(18)
                .moveRange(5)
                .build();
        notUpgradedCreatures.add(c);
        c = new BlockCounterAttackCreature.Builder()
                .name("Vampire")
                .armor(7)
                .attack(10)
                .damage(Range.closed(3, 5))
                .maxHp(18)
                .moveRange(5)
                .build();
        notUpgradedCreatures.add(c);
        c = new ShootingCreature.Builder()
                .name("Lich")
                .armor(10)
                .attack(13)
                .damage(Range.closed(5, 8))
                .maxHp(30)
                .moveRange(6)
                .build();
        notUpgradedCreatures.add(c);
        c = new Creature.Builder()
                .name("Black Knight")
                .armor(16)
                .attack(16)
                .damage(Range.closed(15, 30))
                .maxHp(120)
                .moveRange(7)
                .build();
        notUpgradedCreatures.add(c);
        c = new Creature.Builder()
                .name("Bone Dragon")
                .armor(16)
                .attack(16)
                .damage(Range.closed(25, 50))
                .maxHp(150)
                .moveRange(9)
                .build();
        notUpgradedCreatures.add(c);

        List<Creature> upgradedCreatures = new ArrayList<>();

        c = new Creature.Builder()
                .name("Skeleton Warrior")
                .armor(6)
                .attack(6)
                .damage(Range.closed(1, 3))
                .maxHp(6)
                .moveRange(5)
                .build();
        upgradedCreatures.add(c);
        c = new Creature.Builder()
                .name("Zombie")
                .armor(5)
                .attack(5)
                .damage(Range.closed(2, 3))
                .maxHp(20)
                .moveRange(4)
                .build();
        upgradedCreatures.add(c);
        c = new Creature.Builder()
                .name("Wraith")
                .armor(5)
                .attack(5)
                .damage(Range.closed(2, 3))
                .maxHp(20)
                .moveRange(4)
                .build();
        upgradedCreatures.add(c);
        c = new Creature.Builder()
                .name("Vampire Lord")
                .armor(10)
                .attack(10)
                .damage(Range.closed(5, 8))
                .maxHp(40)
                .moveRange(9)
                .build();
        upgradedCreatures.add(c);
        c = new ShootingCreature.Builder()
                .name("Power Lich")
                .armor(10)
                .attack(13)
                .damage(Range.closed(11, 15))
                .maxHp(40)
                .moveRange(7)
                .build();
        upgradedCreatures.add(c);
        c = new Creature.Builder()
                .name("Dread Knight")
                .armor(18)
                .attack(18)
                .damage(Range.closed(15,30))
                .maxHp(120)
                .moveRange(9)
                .build();
        upgradedCreatures.add(c);
        c = new Creature.Builder()
                .name("Ghost Dragon")
                .armor(17)
                .attack(19)
                .damage(Range.closed(25,50))
                .maxHp(200)
                .moveRange(14)
                .build();
        upgradedCreatures.add(c);


        gameEngine = new GameEngine(notUpgradedCreatures, upgradedCreatures);

    }

    @FXML
    void initialize() {
        gameEngine.addObserver(GameEngine.CURRENT_CREATURE_CHANGED, this);
        gameEngine.addObserver(GameEngine.CREATURE_MOVED, this);
        gameEngine.addObserver(GameEngine.CREATURE_ATTACKED, this);

        passButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            gameEngine.pass();
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
                    rec.addCreature(c.getName(), c.currentHealth());

                    if (c == gameEngine.getActiveCreature()) {
                        rec.setBackground(Color.GREEN);
                    } else if (gameEngine.canAttack(x, y)) {
                        final int x1 = x;
                        final int y1 = y;
                        rec.setBackground(Color.RED);
                        rec.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> gameEngine.attack(x1, y1));
                    }
                } else if (gameEngine.canMove(x, y)) {
                    final int x1 = x;
                    final int y1 = y;
                    rec.setBackground(Color.GREY);
                    rec.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> gameEngine.move(new Point(x1, y1)));
                }
            }

        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent aPropertyChangeEvent) {
        refreshGui();
    }
}


