package pl.sdk.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pl.sdk.converter.EcoBattleConverter;
import pl.sdk.creatures.EconomyCreature;
import pl.sdk.creatures.EconomyNecropolisFactory;
import pl.sdk.hero.EconomyEngine;
import pl.sdk.hero.EconomyHero;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EcoController implements PropertyChangeListener {

    @FXML
    HBox heroStateHBox;
    @FXML
    HBox shopsBox;
    @FXML
    Button readyButton;
    @FXML
    Separator separator;
    @FXML
    Label playerLabel;
    @FXML
    Label currentGoldLabel;
    @FXML
    Label roundNumberLabel;


    private final EconomyEngine economyEngine;
    private String activeHeroName;

    public EcoController(EconomyHero aHero1, EconomyHero aHero2) {
        economyEngine = new EconomyEngine(aHero1, aHero2);
    }


    @FXML
    void initialize() {
        refreshGui();
        economyEngine.addObserver(EconomyEngine.ACTIVE_HERO_CHANGED, this);
        economyEngine.addObserver(EconomyEngine.HERO_BOUGHT_CREATURE, this);
        economyEngine.addObserver(EconomyEngine.NEXT_ROUND, this);
        readyButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            if (economyEngine.getRoundNumber() < 4) {
                economyEngine.pass();

            } else {
                goToBattle();
            }
        });
    }
//
    private void goToBattle() {
        EcoBattleConverter.startBattle(economyEngine.getPlayer1(),economyEngine.getPlayer2());

    }

    void refreshGui() {
        playerLabel.setText(String.valueOf(economyEngine.getActiveHero()));
        currentGoldLabel.setText(String.valueOf(economyEngine.getActiveHero().getGold()));
        roundNumberLabel.setText(String.valueOf(economyEngine.getRoundNumber()));
        separator = new Separator();
        shopsBox.getChildren().add(separator);
        shopsBox.getChildren().clear();
        heroStateHBox.getChildren().clear();



        EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        VBox creatureShop = new VBox();


        for (int i = 1; i < 8; i++) {
            creatureShop.getChildren().add(new CreatureButton(this, factory, false, i));
            creatureShop.getChildren().add(new CreatureButton(this, factory, true, i));
        }
        shopsBox.getChildren().add(creatureShop);

        VBox creaturesBox = new VBox();

        economyEngine.getActiveHero().getCreatures().forEach(c -> {
            HBox tempHBox = new HBox();
            tempHBox.getChildren().add(new Label(String.valueOf(c.getAmount())));
            tempHBox.getChildren().add(new Label(c.getName()));
            creaturesBox.getChildren().add(tempHBox);
        });
        heroStateHBox.getChildren().add(creaturesBox);


    }

    void buy(EconomyCreature aCreature) {
        economyEngine.buy(aCreature);
    }

    @Override
    public void propertyChange(PropertyChangeEvent aPropertyChangeEvent) {
        refreshGui();

    }
}
