package pl.sdk.converter.spells;

import org.junit.jupiter.api.Test;
import pl.sdk.SpellsStatistic;
import pl.sdk.spells.BuffSpell;
import pl.sdk.spells.EconomySpell;

import static org.junit.jupiter.api.Assertions.*;

class SummonSpellFactoryTest {


    @Test
    void shouldConvertSlowSpellsCorrectly() {
        EconomySpell toCovert = new EconomySpell(SpellsStatistic.SUMMON_AIR_ELEMENTAL);

        BuffSpell spell = (BuffSpell) new BuffSpellFactory().createInner(toCovert, 1);

        assertEquals(1, spell.getDuration());
        assertEquals("Air Elemental", spell.getSummoningCreatureAmount());
        assertEquals(0, spell.getSplashRange());
        assertEquals(25, spell.getManaCost());
        assertEquals(SpellsStatistic.TargetType.MAP, spell.getTargetType());
    }
}