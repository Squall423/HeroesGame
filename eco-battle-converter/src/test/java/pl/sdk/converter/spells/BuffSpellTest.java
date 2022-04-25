package pl.sdk.converter.spells;

import org.junit.jupiter.api.Test;
import pl.sdk.Fraction;
import pl.sdk.SpellsStatistic;
import pl.sdk.converter.SpellMasteries;
import pl.sdk.creatures.AbstractFractionFactory;
import pl.sdk.creatures.Creature;
import pl.sdk.spells.AbstractSpell;
import pl.sdk.spells.EconomySpell;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuffSpellTest {

    @Test
    void shouldIncreaseMoveRange() {
        AbstractSpell haste = SpellFactory.create("Haste", new EconomySpell(SpellsStatistic.HASTE), 1,
                new SpellMasteries());
        Creature creature = AbstractFractionFactory.getInstance(Fraction.TEST_FRACTION)
                .create(true, 7, 5);

        assertEquals(14, creature.getMoveRange());
        haste.cast(creature);
        assertEquals(17, creature.getMoveRange());
    }
}