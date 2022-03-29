package pl.sdk.spells;

import pl.sdk.SpellsStatistic;
import pl.sdk.creatures.Creature;

public abstract class AbstractSpell {
    protected final SpellsStatistic.TargetType targetType;
    protected final SpellsStatistic.SpellElement element;
    protected final int manaCost;



    AbstractSpell(int aManaCost, SpellsStatistic.TargetType aTargetType, SpellsStatistic.SpellElement aElement) {
        manaCost = aManaCost;
        targetType = aTargetType;
        element = aElement;

    }

    public int getManaCost() {
        return manaCost;
    }

    public SpellsStatistic.TargetType getTargetType() {
        return targetType;
    }

    public abstract int getSplashRange();

    public void cast(Creature aT) {};


}

