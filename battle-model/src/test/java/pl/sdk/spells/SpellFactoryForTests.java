package pl.sdk.spells;

import pl.sdk.SpellsStatistic;

public class SpellFactoryForTests {
    public static AbstractSpell createMagicArrow() {
        return new DamageSpell(5, SpellsStatistic.TargetType.ENEMY, SpellsStatistic.SpellElement.AIR, 5, 5);
    }
    public static AbstractSpell createMagicArrowWithSplash(int aSplash) {
        return new DamageSpell(5, SpellsStatistic.TargetType.ENEMY, SpellsStatistic.SpellElement.AIR, aSplash, 5);
    }

}
