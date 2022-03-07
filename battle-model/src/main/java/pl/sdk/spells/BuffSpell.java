package pl.sdk.spells;

import pl.sdk.SpellsStatistic;

public class BuffSpell extends AbstractSpell {

    private final int duration;

    public BuffSpell(int aManaCost, int aDuration, SpellsStatistic.SpellElement aElement) {
        super(aManaCost, SpellsStatistic.TargetType.ALLY, aElement);
        duration = aDuration;

    }

    public int getDuration() {
        return duration;
    }

    @Override
    public int getSplashRange() {
        return 0;

    }
}
