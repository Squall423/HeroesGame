package pl.sdk.spells;

import pl.sdk.SpellsStatistic;
import pl.sdk.creatures.Creature;

public class BuffOrDebuffSpell extends AbstractSpell {

    private final int duration;
    private BuffOrDebuffStatistic buffStats;


    public BuffOrDebuffSpell(String aName, int aManaCost, int aDuration,
                             SpellsStatistic.TargetType aTarget, SpellsStatistic.SpellElement aElement) {
        super(aName, aManaCost, aTarget, aElement);
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
        aCreature.buff(buffStats);
    }


}
