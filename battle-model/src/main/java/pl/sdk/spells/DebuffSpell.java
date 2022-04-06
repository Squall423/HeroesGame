package pl.sdk.spells;

import pl.sdk.SpellsStatistic;

public class DebuffSpell extends AbstractSpell{
    private final int duration;


    public DebuffSpell(String aName,int aManaCost, int aDuration, SpellsStatistic.SpellElement aElement) {
        super( aName,aManaCost, SpellsStatistic.TargetType.ENEMY, aElement);
        duration = aDuration;

    }

    int getDuration() {
        return duration;
    }

    @Override
    public int getSplashRange() {
        return 0;

    }
}
