package pl.sdk.converter.spells;

import org.junit.jupiter.api.Test;
import pl.sdk.SpellsStatistic;
import pl.sdk.converter.SpellMasteries;
import pl.sdk.spells.BuffSpell;
import pl.sdk.spells.EconomySpell;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuffSpellFactoryTest {

    @Test
    void shouldConvertHasteSpellsCorrectly() {
        EconomySpell toCovert = new EconomySpell(SpellsStatistic.HASTE);
        SpellMasteries masteries = new SpellMasteries();

        BuffSpell spell = (BuffSpell) new BuffSpellFactory().createInner(toCovert, 1, masteries);

        assertEquals(1, spell.getDuration());
        assertEquals(0, spell.getSplashRange());
        assertEquals(6, spell.getManaCost());
        assertEquals(SpellsStatistic.TargetType.ALLY, spell.getTargetType());
    }
}