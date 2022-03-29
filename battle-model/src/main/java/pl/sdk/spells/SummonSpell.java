package pl.sdk.spells;

import pl.sdk.SpellsStatistic;

public class SummonSpell extends AbstractSpell {
    private final int duration;

    public SummonSpell(int aManaCost, int aDuration, SpellsStatistic.SpellElement aElement) {
        super(aManaCost, SpellsStatistic.TargetType.MAP, aElement);
        duration = aDuration;

    }

    @Override
    public int getSplashRange() {
        return 0;
    }

    public String getSummoningCreatureAmount() {
        return "Air Elemental";

    }

    public int getDuration() {
        return 0;

    }
}
