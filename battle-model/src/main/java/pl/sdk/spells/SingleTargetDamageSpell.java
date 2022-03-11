package pl.sdk.spells;

import pl.sdk.SpellsStatistic;
import pl.sdk.creatures.Creature;

public class SingleTargetDamageSpell extends AbstractSpell {


    public SingleTargetDamageSpell(int aManaCost, SpellsStatistic.TargetType aTargetType,
                                   SpellsStatistic.SpellElement aElement,int aSpellDamage, int aSplashRange ) {
        super(aManaCost,aTargetType, aElement);
    }



    public int getDamage() {
        return 175;
    }

    public int getSplashRange() {
        return 0;
    }


}
