package pl.sdk.gui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pl.sdk.converter.EcoBattleConverter;
import pl.sdk.creatures.AbstractEconomyFractionFactory;
import pl.sdk.creatures.EconomyCreature;
import pl.sdk.hero.EconomyEngine;
import pl.sdk.hero.Player;
import pl.sdk.spells.AbstractEconomySpellFactory;
import pl.sdk.spells.EconomySpell;
import pl.sdk.spells.SpellFactoryType;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

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

        TabPane tabPane = createTabs();
        shopsBox.getChildren().add(tabPane);
        shopsBox.setAlignment(Pos.CENTER);

        VBox stateBox = createStateBox();
        heroStateBox.getChildren().add(stateBox);

    }

    private TabPane createTabs() {
        HBox creatureShop = createCreatureShop();
        VBox spellShop = createSpellShop();

        TabPane tabPane = new TabPane();
        Tab creatureTab = new Tab();
        creatureTab.setContent(creatureShop);
        ImageView creatureTabImage = new ImageView(new Image(getClass().getResourceAsStream("/graphics.spells/giant" +
                ".png")));
        creatureTabImage.setFitHeight(50);
        creatureTabImage.setFitWidth(50);
        creatureTabImage.getStyleClass().add("tab");
        creatureTab.setGraphic(creatureTabImage);
        Tab spellTab = new Tab();
        spellTab.setContent(spellShop);
        ImageView spellTabImage = new ImageView(new Image(getClass().getResourceAsStream("/graphics.spells/spellbook" +
                ".png")));
        spellTabImage.setFitHeight(50);
        spellTabImage.setFitWidth(50);
        spellTabImage.fitHeightProperty();
        spellTab.setGraphic(spellTabImage);
        spellTab.getStyleClass().add("tab");

        tabPane.getTabs().add(creatureTab);
        tabPane.getTabs().add(spellTab);

        tabPane.getTabs().forEach(tab -> tab.setClosable(false));
        return tabPane;
    }

    private VBox createSpellShop() {
        AbstractEconomySpellFactory factory = AbstractEconomySpellFactory.getInstance(SpellFactoryType.DEFAULT);
        VBox spellShop = new VBox();

        List<EconomySpell> spellList = economyEngine.getCurrentSpellPopulation();
        spellList.forEach(s -> spellShop.getChildren().add(new SpellButton(this, factory, s.getName())));
        return spellShop;
    }

    private HBox createCreatureShop() {
        AbstractEconomyFractionFactory factory =
                AbstractEconomyFractionFactory.getInstance(economyEngine.getActivePlayer().getFraction());
        HBox creatureShop = new HBox();
        VBox creatureNotUpgrade = new VBox();
        VBox creatureUpgraded = new VBox();

        for (int i = 1; i < 8; i++) {
            creatureNotUpgrade.getChildren().add(new CreatureButton(this, factory, false, i));
            creatureUpgraded.getChildren().add(new CreatureButton(this, factory, true, i));
        }
        creatureShop.getChildren().add(creatureNotUpgrade);
        Separator separator = new Separator();
        creatureShop.getChildren().add(separator);
        creatureShop.getChildren().add(creatureUpgraded);
        return creatureShop;
    }


    private VBox createStateBox() {
        VBox stateBox = new VBox();
        VBox creatureBox = new VBox();
        VBox spellBox = new VBox();
        createCreatureStateBox(creatureBox);
        createSpellStateBox(spellBox);
        stateBox.getChildren().add(creatureBox);
        stateBox.getChildren().add(spellBox);
        return stateBox;
    }

    private void createCreatureStateBox(VBox aCreatureBox) {

        economyEngine.getActivePlayer().getCreatures().forEach(c -> {
            HBox tempHbox = new HBox();

            ImageView image =
                    new ImageView(new Image(getClass().getResourceAsStream("/graphics/creatures/" + c.getName() +
                            ".png")));
            ;
            image.setFitHeight(80);
            image.setFitWidth(80);
            tempHbox.getChildren().add(image);

            Label creatureName = new Label(c.getName());
            creatureName.getStyleClass().add("hero-state");
            tempHbox.getChildren().add(creatureName);

            Label creatureAmount = new Label(String.valueOf(c.getAmount()));
            tempHbox.getChildren().add(creatureAmount);
            creatureAmount.getStyleClass().add("hero-state");

            tempHbox.setAlignment(Pos.CENTER_LEFT);
            aCreatureBox.getChildren().add(tempHbox);
            Separator stateSeparator = new Separator();
            aCreatureBox.getChildren().add(stateSeparator);
        });
    }

    private void createSpellStateBox(VBox aSpellBox) {

        economyEngine.getActivePlayer().getSpells().forEach(c -> {
            HBox tempHbox = new HBox();

            ImageView image =
                    new ImageView(new Image(getClass().getResourceAsStream("/graphics.spells/spell"  + ".png")));
            image.setFitHeight(55);
            image.setFitWidth(55);
            tempHbox.getChildren().add(image);

            Label spellName = new Label(c.getName());
            spellName.getStyleClass().add("hero-state");
            tempHbox.getChildren().add(spellName);

            tempHbox.setAlignment(Pos.CENTER_LEFT);
            tempHbox.setPadding(new Insets(15, 15, 15, 15));
            aSpellBox.getChildren().add(tempHbox);
            Separator stateSeparator = new Separator();
            aSpellBox.getChildren().add(stateSeparator);
        });
    }

    public int getGold() {
        return economyEngine.getActivePlayer().getGold();
    }


    @Override
    public void propertyChange(PropertyChangeEvent aPropertyChangeEvent) {
        if (aPropertyChangeEvent.getPropertyName().equals(END_OF_TURN)) {
            goToBattle();
        } else {
            refreshGui();
        }
    }

    void buyCreature(EconomyCreature aCreature) {
        economyEngine.buy(aCreature);
    }

    void buySpell(EconomySpell aEconomySpell) {
        economyEngine.buySpell(aEconomySpell);
    }

    public int calculateCreatureMaxAmount(EconomyCreature aCreature) {
        return economyEngine.calculateMaxAmount(aCreature);
    }

    int calculateSpellMaxAmount(EconomySpell aSpell) {
        return economyEngine.calculateSpellMaxAmount(aSpell);
    }

    boolean hasSpell(String aName) {
        return economyEngine.hasSpell(aName);
    }
}
