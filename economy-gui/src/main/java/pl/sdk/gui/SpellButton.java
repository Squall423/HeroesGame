package pl.sdk.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pl.sdk.spells.AbstractEconomySpellFactory;
import pl.sdk.spells.EconomySpell;

public class SpellButton extends Button {
    public SpellButton(EcoController aEcoController, AbstractEconomySpellFactory aFactory, String aName) {
        super();
        EconomySpell spell = aFactory.create(aName);

        addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                aEcoController.buySpell(aFactory.create(aName));
                aEcoController.refreshGui();
            }
        });

        HBox topPane = new HBox();
        ImageView image =
                new ImageView(new Image(getClass().getResourceAsStream("/graphics.spells/spell"  + ".png")));
        image.setFitHeight(20);
        image.setFitWidth(20);
        topPane.getChildren().add(image);
        Label spellName = new Label(spell.getName());
        spellName.getStyleClass().add("buy-creature-text");
        topPane.getChildren().add(spellName);
        topPane.setAlignment(Pos.CENTER);

        VBox borderPane = new VBox();
        HBox statisticBox = new HBox();
        Label goldCost = new Label("Gold" + spell.getGoldCost());
        goldCost.getStyleClass().add("creature-button-statistic");
        statisticBox.getChildren().add(goldCost);
        Label manaLabel = new Label("Mana: " + spell.getManaCost());
        manaLabel.getStyleClass().add("creature-button-statistic");
        statisticBox.getChildren().add(manaLabel);
        Label levelLabel = new Label("Level: " + spell.getLevel());
        levelLabel.getStyleClass().add("creature-button-statistic");
        statisticBox.getChildren().add(levelLabel);
        Label targetLabel = new Label("Target: " + spell.getTargetType());
        targetLabel.getStyleClass().add("creature-button-statistic");
        statisticBox.getChildren().add(targetLabel);
        statisticBox.setAlignment(Pos.CENTER);
        borderPane.getChildren().add(statisticBox);

        Label descriptionLabel = new Label(spell.getDescription());
        descriptionLabel.getStyleClass().add("creature-button-statistic");
        borderPane.getChildren().add(descriptionLabel);
        borderPane.setAlignment(Pos.CENTER);
        borderPane.getStyleClass().add("border");

        VBox buttonContent = new VBox();
        buttonContent.getChildren().add(topPane);
        buttonContent.getChildren().add(borderPane);
        this.setGraphic(buttonContent);
        if (aEcoController.calculateSpellMaxAmount(spell) <= 0 || aEcoController.hasSpell(spell.getName()))
        setDisable(true);
        getStyleClass().add("creatureButton");
    }


}
