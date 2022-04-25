package pl.sdk.converter.spells;

import org.junit.jupiter.api.Test;
import pl.sdk.SpellsStatistic;
import pl.sdk.converter.SpellMasteries;
import pl.sdk.spells.BuffOrDebuffSpell;
import pl.sdk.spells.EconomySpell;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuffOrDebuffSpellFactoryTest {

    @Test
    void shouldConvertHasteSpellsCorrectly() {
        EconomySpell toCovert = new EconomySpell(SpellsStatistic.HASTE);
        SpellMasteries masteries = new SpellMasteries();

        BuffOrDebuffSpell spell = (BuffOrDebuffSpell) new BuffOrDebuffSpellFactory().createInner(toCovert.getName(),
                toCovert, 1, masteries);

        assertEquals(1, spell.getDuration());
        assertEquals(0, spell.getSplashRange());
        assertEquals(6, spell.getManaCost());
        assertEquals(SpellsStatistic.TargetType.ALLY, spell.getTargetType());
    }

    @Test
    void shouldConvertSlowSpellsCorrectly() {
        EconomySpell toCovert = new EconomySpell(SpellsStatistic.SLOW);
        SpellMasteries masteries = new SpellMasteries();
        BuffOrDebuffSpell spell = (BuffOrDebuffSpell) new BuffOrDebuffSpellFactory().createInner(toCovert.getName(),
                toCovert, 1, masteries);

        assertEquals(1, spell.getDuration());
        assertEquals(0, spell.getSplashRange());
        assertEquals(6, spell.getManaCost());
        assertEquals(SpellsStatistic.TargetType.ENEMY, spell.getTargetType());
    }
}