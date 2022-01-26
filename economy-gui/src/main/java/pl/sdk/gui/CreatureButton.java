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
import pl.sdk.creatures.EconomyCreature;
import pl.sdk.creatures.EconomyNecropolisFactory;


public class CreatureButton extends Button {


    public CreatureButton(EcoController aEcoController, EconomyNecropolisFactory aFactory, boolean aUpgraded,
                          int aTier) {
        super();

        EconomyCreature creature = (aFactory.create(aUpgraded, aTier, 1));

        addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                BuyCreatureDialog buyCreatureDialog = new BuyCreatureDialog(creature.getName(),
                        aEcoController.calculateMaxAmount(creature), creature.getGoldCost(), creature.getGrowth());
                buyCreatureDialog.startDialog();
                int amount = buyCreatureDialog.getCreatureAmount();
                if (amount != 0) {
                    aEcoController.buy(aFactory.create(aUpgraded, aTier, amount));
                }
                aEcoController.refreshGui();

            } else if (e.getButton() == MouseButton.SECONDARY) {
                StatisticCreatureDialog statisticCreatureDialog = new StatisticCreatureDialog(this,
                        aFactory.create(aUpgraded, aTier, 1));
                statisticCreatureDialog.startDialog();
            }
        });

        HBox topPane = new HBox();
        ImageView image =
                new ImageView(new Image(getClass().getResourceAsStream("/graphics/creatures/" + creature.getName() +
                        ".png")));
        image.setFitHeight(50);
        image.setFitWidth(55);
        topPane.getChildren().add(image);

        Label creatureName = new Label(creature.getName());
        creatureName.getStyleClass().add("buy-creature-text");
        topPane.getChildren().add(creatureName);
        topPane.setAlignment(Pos.CENTER);

        HBox statisticsBox = new HBox();

        Label attackLabel = new Label("Attack: " + creature.getAttack());
        attackLabel.getStyleClass().add("creature-button-statistic");
        statisticsBox.getChildren().add(attackLabel);

        Label armorLabel = new Label("Armor: " + creature.getArmor());
        armorLabel.getStyleClass().add("creature-button-statistic");
        statisticsBox.getChildren().add(armorLabel);

        Label healthLabel = new Label("Health: " + creature.getMaxHp());
        healthLabel.getStyleClass().add("creature-button-statistic");
        statisticsBox.getChildren().add(healthLabel);

        Label speedLabel = new Label("Speed: " + creature.getMoveRange());
        speedLabel.getStyleClass().add("creature-button-statistic");
        statisticsBox.getChildren().add(speedLabel);

        Label damageLabel = new Label("Damage: " + creature.getDamage());
        damageLabel.getStyleClass().add("border");
        statisticsBox.getChildren().add(damageLabel);
        statisticsBox.setAlignment(Pos.CENTER);


        VBox buttonContent = new VBox();
        buttonContent.getChildren().add(topPane);
        buttonContent.getChildren().add(statisticsBox);
        this.setGraphic(buttonContent);
        getStyleClass().add("creatureButton");


    }


}
