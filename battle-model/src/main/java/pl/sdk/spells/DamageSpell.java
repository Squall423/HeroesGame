package pl.sdk.spells;

import pl.sdk.SpellsStatistic;
import pl.sdk.creatures.Creature;

public class DamageSpell extends AbstractSpell {

    private final int damage;
    private final int splashRange;

    public DamageSpell(int aManaCost, SpellsStatistic.TargetType aTargetType,
                       SpellsStatistic.SpellElement aElement, int aSplashRange, int aSpellDamage) {
        super(aManaCost, aTargetType, aElement);
        splashRange = aSplashRange;
        damage = aSpellDamage;

    }
    @Override
    public int getSplashRange() {
        return 0;

    }



    public int getDamage() {
        return damage;
    }
}
