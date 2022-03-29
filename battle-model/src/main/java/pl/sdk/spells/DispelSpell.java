package pl.sdk.spells;

import pl.sdk.SpellsStatistic;

public class DispelSpell extends AbstractSpell{
    private final int duration;

    public DispelSpell(int aManaCost, int aDuration, SpellsStatistic.SpellElement aElement) {
        super(aManaCost,SpellsStatistic.TargetType.ALLY,aElement);
        duration = aDuration;

    }

    @Override
    public int getSplashRange() {
        return 0;
    }


}
