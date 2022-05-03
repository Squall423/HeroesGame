package pl.sdk.converter.spells;

import org.junit.jupiter.api.Test;
import pl.sdk.*;
import pl.sdk.converter.SpellMasteries;
import pl.sdk.creatures.AbstractFractionFactory;
import pl.sdk.creatures.Creature;
import pl.sdk.spells.AbstractSpell;
import pl.sdk.spells.EconomySpell;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuffOrDebuffSpellTest {

    @Test
    void shouldIncreaseMoveRange() {
        Creature c1 = AbstractFractionFactory.getInstance(Fraction.TEST_FRACTION)
                .create(true, 7, 5);
        Creature c2 = AbstractFractionFactory.getInstance(Fraction.TEST_FRACTION)
                .create(true, 7, 5);
        GameEngine engine = new GameEngine(new Hero(List.of(c1)), new Hero(List.of(c2)));
        AbstractSpell haste = SpellFactory.create(new EconomySpell(SpellsStatistic.HASTE), 1,
                new SpellMasteries());

        assertEquals(14, c1.getMoveRange());
        engine.castSpell(haste, new Point(0, 1));
        assertEquals(17, c1.getMoveRange());

    }

    @Test
    void shouldEndBuffAfterTwoTurns() {
        Creature c1 = AbstractFractionFactory.getInstance(Fraction.TEST_FRACTION)
                .create(true, 7, 5);
        Creature c2 = AbstractFractionFactory.getInstance(Fraction.TEST_FRACTION)
                .create(true, 7, 5);
        GameEngine engine = new GameEngine(new Hero(List.of(c1)), new Hero(List.of(c2)));
        AbstractSpell spell = SpellFactory.create(new EconomySpell(SpellsStatistic.HASTE), 1,
                new SpellMasteries());

        assertEquals(14, c1.getMoveRange());
        engine.castSpell(spell, new Point(0, 1));
        assertEquals(17, c1.getMoveRange());

        engine.pass();
        engine.pass();
        engine.pass();
        engine.pass();

        assertEquals(14, c1.getMoveRange());
    }

    @Test
    void shouldDecreaseMoveRange() {
        Creature c1 = AbstractFractionFactory.getInstance(Fraction.TEST_FRACTION)
                .create(true, 7, 5);
        Creature c2 = AbstractFractionFactory.getInstance(Fraction.TEST_FRACTION)
                .create(true, 7, 5);
        GameEngine engine = new GameEngine(new Hero(List.of(c1)), new Hero(List.of(c2)));
        AbstractSpell slow = SpellFactory.create(new EconomySpell(SpellsStatistic.SLOW), 1,
                new SpellMasteries());

        assertEquals(14, c1.getMoveRange());
        engine.castSpell(slow, new Point(0, 1));
        assertEquals(11, c1.getMoveRange());
    }

}