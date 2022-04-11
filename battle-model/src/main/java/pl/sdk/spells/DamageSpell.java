package pl.sdk.spells;

import pl.sdk.SpellsStatistic;
import pl.sdk.creatures.Creature;

public class DamageSpell extends AbstractSpell {

    private final int damage;
    private final int splashRange;

    public DamageSpell(String aName, int aManaCost, SpellsStatistic.TargetType aTargetType,
                       SpellsStatistic.SpellElement aElement, int aSpellDamage, int aSplashRange) {
        super(aName, aManaCost, aTargetType, aElement);
        damage = aSpellDamage;
        splashRange = aSplashRange;

    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void cast(Creature aCreature) {
        aCreature.applyMagicDamage(damage);
    }

    @Override
    public int getSplashRange() {
        return splashRange;

    }
}
