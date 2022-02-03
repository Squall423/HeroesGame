package pl.sdk.gui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pl.sdk.converter.EcoBattleConverter;
import pl.sdk.creatures.EconomyCreature;
import pl.sdk.creatures.EconomyNecropolisFactory;

import pl.sdk.hero.EconomyEngine;

import pl.sdk.hero.Player;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static pl.sdk.hero.EconomyEngine.END_OF_TURN;

public class EcoController implements PropertyChangeListener {

    @FXML
    HBox heroStateBox;
    @FXML
    HBox shopsBox;
    @FXML
    Button readyButton;
    @FXML
    Label playerLabel;
    @FXML
    Label currentGoldLabel;
    @FXML
    Label roundNumberLabel;
    @FXML
    ImageView playerIcon;
    @FXML
    ImageView goldIcon;
    @FXML
    ImageView roundIcon;


    private final EconomyEngine economyEngine;
    private String activeHeroName;

    public EcoController(Player aHero1, Player aHero2) {
        economyEngine = new EconomyEngine(aHero1, aHero2);
    }

    @FXML
    void initialize() {
        refreshGui();
        economyEngine.addObserver(EconomyEngine.ACTIVE_PLAYER_CHANGED, this);
        economyEngine.addObserver(EconomyEngine.PLAYER_BOUGHT_CREATURE, this);
        economyEngine.addObserver(EconomyEngine.NEXT_ROUND, this);
        economyEngine.addObserver(END_OF_TURN, this);

        readyButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> economyEngine.pass());
    }

    private void goToBattle() {
        EcoBattleConverter.startBattle(economyEngine.getPlayer1(), economyEngine.getPlayer2());

    }

    void refreshGui() {
        playerLabel.setText(economyEngine.heroToString());
        currentGoldLabel.setText(String.valueOf(getGold()));
        roundNumberLabel.setText(String.valueOf(economyEngine.getRoundNumber()));

        shopsBox.getChildren().clear();
        heroStateBox.getChildren().clear();

        EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        HBox creatureShop = new HBox();
        VBox creatureShopNotUpgraded = new VBox();
        VBox creatureShopUpgraded = new VBox();

        for (int i = 1; i < 8; i++) {
            creatureShopNotUpgraded.getChildren().add(new CreatureButton(this, factory, false, i));
            creatureShopUpgraded.getChildren().add(new CreatureButton(this, factory, true, i));
        }

        creatureShop.getChildren().add(creatureShopNotUpgraded);
        Separator separator = new Separator();
        creatureShop.getChildren().add(separator);
        creatureShop.getChildren().add(creatureShopUpgraded);
        shopsBox.getChildren().add(creatureShop);
        shopsBox.setAlignment(Pos.CENTER);

        VBox creaturesBox = new VBox();

        economyEngine.getActivePlayer().getCreatures().forEach(c -> {

            HBox tempHbox = new HBox();

            ImageView image =
                    new ImageView(new Image(getClass().getResourceAsStream("/graphics/creatures/" + c.getName() + ".png")));
            image.setFitHeight(100);
            image.setFitWidth(100);
            tempHbox.getChildren().add(image);

            Label creatureName = new Label(c.getName());
            creatureName.getStyleClass().add("hero-state");
            tempHbox.getChildren().add(creatureName);

            Label creatureAmount = new Label(String.valueOf(c.getAmount()));
            tempHbox.getChildren().add(creatureAmount);
            creatureAmount.getStyleClass().add("hero-state");

            tempHbox.setAlignment(Pos.CENTER_LEFT);
            creaturesBox.getChildren().add(tempHbox);
            Separator stateSeparator = new Separator();
            creaturesBox.getChildren().add(stateSeparator);

        });
        heroStateBox.getChildren().add(creaturesBox);


    }

    public int getGold() {
        return economyEngine.getActivePlayer().getGold();
    }

    void buy(EconomyCreature aCreature) {
        economyEngine.buy(aCreature);
    }

    public int calculateMaxAmount(EconomyCreature aCreature) {
        return economyEngine.calculateMaxAmount( aCreature);
    }

    @Override
    public void propertyChange(PropertyChangeEvent aPropertyChangeEvent) {
        if (aPropertyChangeEvent.getPropertyName().equals(END_OF_TURN)) {
            goToBattle();
        } else {
            refreshGui();
        }
    }
}
