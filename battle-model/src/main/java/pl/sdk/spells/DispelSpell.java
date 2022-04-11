package pl.sdk.spells;

import pl.sdk.SpellsStatistic;
import pl.sdk.creatures.Creature;

public class DispelSpell extends AbstractSpell {
    private final int duration;

    public DispelSpell(String aName, int aManaCost, int aDuration, SpellsStatistic.SpellElement aElement) {
        super(aName, aManaCost, SpellsStatistic.TargetType.ALLY, aElement);
        duration = aDuration;

    }

    @Override
    public int getSplashRange() {
        return 0;
    }

    @Override
    public void cast(Creature aCreature) {

    }


}
