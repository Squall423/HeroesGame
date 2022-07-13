package pl.sdk.spells;

import pl.sdk.SpellsStatistic;
import pl.sdk.creatures.Creature;

import java.net.ProtocolFamily;

public abstract class AbstractSpell {
    protected final SpellsStatistic.TargetType targetType;
    protected final SpellsStatistic.SpellElement element;
    protected final int manaCost;
    protected final String name;


    AbstractSpell(String aName, int aManaCost, SpellsStatistic.TargetType aTargetType,
                  SpellsStatistic.SpellElement aElement) {
        name = aName;
        manaCost = aManaCost;
        targetType = aTargetType;
        element = aElement;
    }

    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }

    public SpellsStatistic.TargetType getTargetType() {
        return targetType;
    }

    public abstract int getSplashRange();

    public abstract void cast(Creature aCreature);

    public SpellsStatistic.SpellElement getElement() {
        return element;
    }
}

