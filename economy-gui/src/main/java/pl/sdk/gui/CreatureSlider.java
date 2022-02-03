package pl.sdk.gui;

import javafx.scene.control.Slider;

import javax.annotation.processing.Completion;

public class CreatureSlider {

    private final int maxValue;
    private Slider slider;

    CreatureSlider(int aMaxValue) {
        maxValue = aMaxValue;
    }

    Slider createSlider() {
        slider = new Slider();
        slider.setMin(0);
        slider.setMax(maxValue);
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

