package pl.sdk.converter;

import org.junit.jupiter.api.Test;
import pl.sdk.SpellsStatistic;
import pl.sdk.converter.spells.DamageSpellFactory;
import pl.sdk.spells.EconomySpell;
import pl.sdk.spells.SingleTargetDamageSpell;

import static org.junit.jupiter.api.Assertions.*;

class DamageSpellFactoryTest {

    @Test
    void shouldConvertMagicArrowSpellsCorrectly() {
        EconomySpell toCovert = new EconomySpell(SpellsStatistic.MAGIC_ARROW);

        SingleTargetDamageSpell singleTargetDamageSpell = DamageSpellFactory.create(toCovert, 1);

        assertEquals(20, singleTargetDamageSpell.getDamage());
        assertEquals(0, singleTargetDamageSpell.getSplashRange());
        assertEquals(5, singleTargetDamageSpell.getManaCost());
        assertEquals(SpellsStatistic.TargetType.ENEMY, singleTargetDamageSpell.getTargetType());
    }
    @Test
    void shouldConvertImplosionSpellsCorrectly() {
        EconomySpell toCovert = new EconomySpell(SpellsStatistic.IMPLOSION);

        SingleTargetDamageSpell singleTargetDamageSpell = DamageSpellFactory.create(toCovert, 1);

        assertEquals(175, singleTargetDamageSpell.getDamage());
        assertEquals(0, singleTargetDamageSpell.getSplashRange());
        assertEquals(30, singleTargetDamageSpell.getManaCost());
        assertEquals(SpellsStatistic.TargetType.ENEMY, singleTargetDamageSpell.getTargetType());
    }

}