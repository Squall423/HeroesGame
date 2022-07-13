package pl.sdk.spells;

import pl.sdk.SpellsStatistic;

public class SingleTargetDamageSpell extends DamageSpell {

    private final int damage;
    private final int splashRange;

    public SingleTargetDamageSpell(String aName, int aManaCost, SpellsStatistic.TargetType aTargetType,
                                   SpellsStatistic.SpellElement aElement, int aSpellDamage, int aSplashRange) {
        super(aName, aManaCost, aTargetType, aElement, aSpellDamage, aSplashRange);
        damage = aSpellDamage;
        splashRange = aSplashRange;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getSplashRange() {
        return splashRange;
    }


}
