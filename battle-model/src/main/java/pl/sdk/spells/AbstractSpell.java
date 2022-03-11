package pl.sdk.spells;

import pl.sdk.Point;
import pl.sdk.SpellsStatistic;
import pl.sdk.creatures.Creature;

public abstract class AbstractSpell {
    protected final int manaCost;
    protected final SpellsStatistic.TargetType targetType;
    protected final SpellsStatistic.SpellElement element;


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

   public  void cast(Creature aT){};


}

