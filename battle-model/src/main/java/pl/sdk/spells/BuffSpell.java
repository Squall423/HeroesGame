package pl.sdk.spells;

import pl.sdk.SpellsStatistic;
import pl.sdk.creatures.Creature;

public class BuffSpell extends AbstractSpell {

    private final int duration;


    public BuffSpell(String aName, int aManaCost, int aDuration, SpellsStatistic.SpellElement aElement) {
        super(aName, aManaCost, SpellsStatistic.TargetType.ALLY, aElement);
        duration = aDuration;

    }

    public int getDuration() {
        return duration;
    }

    @Override
    public int getSplashRange() {
        return 0;

    }

    @Override
    public void cast(Creature aCreature) {
        BuffStatistic hasteStats = new BuffStatistic(3);
        aCreature.buff(hasteStats);
    }


}
