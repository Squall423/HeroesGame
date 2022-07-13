package pl.sdk.gui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pl.sdk.*;
import pl.sdk.creatures.Creature;
import pl.sdk.creatures.NecropolisFactory;
import pl.sdk.spells.AbstractSpell;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import static pl.sdk.GameEngine.AFTER_ATTACK;
import static pl.sdk.GameEngine.AFTER_MOVE;

public class BattleMapController implements PropertyChangeListener {

    @FXML
    private GridPane gridMap;

    @FXML
    private Button passButton;

    @FXML
    private Button spellBookButton;

    @FXML
    private Button escapeButton;

    private final GameEngine gameEngine;

    private SpellBook spellBook;
    private String APPLICATION_STOP = "stopping the application impossible";

    public BattleMapController() {
        List<Creature> notUpgradedCreatures = new ArrayList<>();
        List<Creature> upgradedCreatures = new ArrayList<>();
        NecropolisFactory factory = new NecropolisFactory();
        for (int i = 1; i <= 7; i++) {
            notUpgradedCreatures.add(factory.create(false, i, 10));
        }
        for (int i = 1; i <= 7; i++) {
            upgradedCreatures.add(factory.create(true, i, 10));
        }

        gameEngine = new GameEngine(new Hero(notUpgradedCreatures), new Hero(upgradedCreatures));

    }

    public BattleMapController(Hero aHero1, Hero aHero2) {
        gameEngine = new GameEngine(aHero1, aHero2);
    }

    @FXML
    void initialize() {
        gameEngine.addObserver(GameEngine.CURRENT_CREATURE_CHANGED, this);
        gameEngine.addObserver(GameEngine.CREATURE_MOVED, this);
        gameEngine.addObserver(GameEngine.CREATURE_ATTACKED, this);

        passButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            gameEngine.pass();
        });

        spellBookButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            SpellChooserDialog spellChooser = new SpellChooserDialog(gameEngine.getActiveHero().getSpells(),
                    gameEngine.getActiveHero().getCurrentMana(), gameEngine.getActiveHero().getMaxMana());
            spellChooser.startDialog(this::prepareToCastSpell);
        });

        escapeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            try {
                Node source = (Node) e.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            } catch (Exception aE) {
                System.out.println(APPLICATION_STOP);
                aE.printStackTrace();
            }
        });

        refreshGui(null);
    }

    private void refreshGui(AbstractSpell aSpellToCast) {

        gridMap.getChildren().clear();
        spellBookButton.setDisable(!gameEngine.canCastSpell());
//TODO
//        if (aSpellToCast != null && aSpellToCast.getTargetType().equals(SpellsStatistic.TargetType.ALL_ALLIES)) {
//            gameEngine.castSpell(aspellToCast);
//        }
        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 15; y++) {
                MapTile rec = new MapTile();
                gridMap.add(rec, x, y);
                Creature c = gameEngine.get(x, y);
                if (c != null) {
                    boolean shouldFlip = gameEngine.isHeroTwoGotCreature(c);
                    rec.addCreature(c.getName(), c.getAmount(), shouldFlip);
                }
                if (aSpellToCast == null || aSpellToCast.getTargetType().equals(SpellsStatistic.TargetType.ALL_ALLIES)) {
                    prepareTile(x, y, rec);
                } else {
                    prepareTileWithSpells(x, y, rec, aSpellToCast);
                }
            }
        }
    }

    private void prepareTileWithSpells(int x, int y, MapTile rec, AbstractSpell aSpellToCast) {
        if (gameEngine.canCastSpell(aSpellToCast, new Point(x, y))) {
            rec.changeState(new MapTileCastSpellPossibleState());
            rec.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                gameEngine.
                        castSpell(aSpellToCast, new Point(x, y));
                refreshGui(null);
            });
        }

    }

    private void prepareTile(int x, int y, MapTile rec) {

        gameEngine.addObserver(AFTER_MOVE, rec);
        gameEngine.addObserver(AFTER_ATTACK, rec);
        Creature c = gameEngine.get(x, y);
        if (c != null) {
            if (c == gameEngine.getActiveCreature()) {
                rec.changeState(new MapTileActiveCreatureState());

            } else if (gameEngine.canAttack(x, y)) {
                final int x1 = x;
                final int y1 = y;
                rec.changeState(new MapTileAttackPossibleState());
                rec.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> gameEngine.attack(x1, y1));
            }
        } else if (gameEngine.canMove(x, y)) {
            final int x1 = x;
            final int y1 = y;
            rec.changeState(new MapTileMovePossibleState());
            rec.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> gameEngine.move(new Point(x1, y1)));
        }
    }

    void prepareToCastSpell(AbstractSpell aChosenSpell) {
        refreshGui(aChosenSpell);
    }

    @Override
    public void propertyChange(PropertyChangeEvent aPropertyChangeEvent) {
        refreshGui(null);
    }
}


