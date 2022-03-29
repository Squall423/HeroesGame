package pl.sdk.converter.spells;

import org.junit.jupiter.api.Test;
import pl.sdk.SpellsStatistic;
import pl.sdk.converter.SpellMasteries;
import pl.sdk.spells.EconomySpell;
import pl.sdk.spells.SingleTargetDamageSpell;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DamageSpellFactoryTest {

    @Test
    void shouldConvertMagicArrowSpellsCorrectly() {
        EconomySpell toCovert = new EconomySpell(SpellsStatistic.MAGIC_ARROW);
        SpellMasteries masteries = new SpellMasteries();

        SingleTargetDamageSpell singleTargetDamageSpell =
                (SingleTargetDamageSpell) DamageSpellFactory.create(toCovert
                , 1, masteries);

        assertEquals(20, singleTargetDamageSpell.getDamage());
        assertEquals(3, singleTargetDamageSpell.getSplashRange());
        assertEquals(5, singleTargetDamageSpell.getManaCost());
        assertEquals(SpellsStatistic.TargetType.ENEMY, singleTargetDamageSpell.getTargetType());
    }

    @Test
    void shouldConvertImplosionSpellsCorrectly() {
        EconomySpell toCovert = new EconomySpell(SpellsStatistic.IMPLOSION);
        SpellMasteries masteries = new SpellMasteries();
        SingleTargetDamageSpell singleTargetDamageSpell = (SingleTargetDamageSpell) DamageSpellFactory.create(toCovert, 1, masteries);

        assertEquals(175, singleTargetDamageSpell.getDamage());
        assertEquals(0, singleTargetDamageSpell.getSplashRange());
        assertEquals(3, singleTargetDamageSpell.getManaCost());
        assertEquals(SpellsStatistic.TargetType.ENEMY, singleTargetDamageSpell.getTargetType());
    }

}