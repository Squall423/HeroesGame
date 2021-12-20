package pl.sdk.gui;

import javafx.scene.control.Slider;

public class CreatureSlider {

    private final int creatureGold;
    private final int heroGold;
    private Slider slider;

    CreatureSlider(int aCreatureGold, int aHeroGold) {
        creatureGold = aCreatureGold;
        heroGold = aHeroGold;
    }

    Slider createSlider() {
        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(heroGold / creatureGold);
        slider.setValue(0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(10);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(10);
        return slider;
    }

    public int getCreatureAmount() {
        return (int) slider.getValue();
    }
}

