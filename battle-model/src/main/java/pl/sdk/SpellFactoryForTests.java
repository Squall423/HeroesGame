package pl.sdk;

import pl.sdk.spells.AbstractSpell;
import pl.sdk.spells.DamageSpell;

public class SpellFactoryForTests {
    public static AbstractSpell createMagicArrow() {
        return new DamageSpell(SpellsStatistic.MAGIC_ARROW.getName(), 5, SpellsStatistic.TargetType.ENEMY,
                SpellsStatistic.SpellElement.AIR
                , 5, 5);
    }

    public static AbstractSpell createMagicArrowWithSplash(int aSplash) {
        return new DamageSpell(SpellsStatistic.MAGIC_ARROW.getName(), 5, SpellsStatistic.TargetType.ENEMY,
                SpellsStatistic.SpellElement.AIR, 5, aSplash);
    }

    public static AbstractSpell createMagicArrowWithSplashAndTargetType(int aSplash,
                                                                        SpellsStatistic.TargetType aTargetType) {
        return new DamageSpell(SpellsStatistic.MAGIC_ARROW.getName(), 5, aTargetType, SpellsStatistic.SpellElement.AIR
                , 5, aSplash);
    }
}
