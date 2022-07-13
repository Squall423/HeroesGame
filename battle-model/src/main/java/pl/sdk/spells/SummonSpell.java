package pl.sdk.spells;

import pl.sdk.SpellsStatistic;
import pl.sdk.creatures.Creature;

public class SummonSpell extends AbstractSpell {
    private final int duration;

    public SummonSpell(String aName, int aManaCost, int aDuration, SpellsStatistic.SpellElement aElement) {
        super(aName,aManaCost, SpellsStatistic.TargetType.MAP, aElement);
        duration = aDuration;

    }

    @Override
    public int getSplashRange() {
        return 0;
    }

    @Override
    public void cast(Creature aCreature) {

    }

    public String getSummoningCreatureAmount() {
        return "Air Elemental";

    }

    public int getDuration() {
        return 0;

    }
}
