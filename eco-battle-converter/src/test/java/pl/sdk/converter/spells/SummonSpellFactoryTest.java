package pl.sdk.converter.spells;

import org.junit.jupiter.api.Test;
import pl.sdk.SpellsStatistic;
import pl.sdk.converter.SpellMasteries;
import pl.sdk.spells.EconomySpell;
import pl.sdk.spells.SummonSpell;

import static org.junit.jupiter.api.Assertions.*;

class SummonSpellFactoryTest {

    @Test
    void shouldConvertSlowSpellsCorrectly() {
        EconomySpell toCovert = new EconomySpell(SpellsStatistic.SUMMON_AIR_ELEMENTAL);
        SpellMasteries masteries = new SpellMasteries();
        SummonSpell spell = (SummonSpell) new SummonSpellFactory().createInner( toCovert, 1,
                masteries);

        assertEquals(0, spell.getDuration());
        assertEquals("Air Elemental", spell.getSummoningCreatureAmount());
        assertEquals(0, spell.getSplashRange());
        assertEquals(25, spell.getManaCost());
        assertEquals(SpellsStatistic.TargetType.MAP, spell.getTargetType());
    }

}