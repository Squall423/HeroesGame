package pl.sdk.spells;

import org.junit.jupiter.api.Test;
import pl.sdk.SpellBook;
import pl.sdk.SpellFactoryForTests;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpellBookTest {

    @Test
    void shouldNotAllowToCastMoreThanOneSpellInRound() {
        SpellBook spellBook = new SpellBook(10, List.of(SpellFactoryForTests.createMagicArrow()));

        assertTrue(spellBook.canCastSpell());
        spellBook.cast(SpellFactoryForTests.createMagicArrow());
        assertFalse(spellBook.canCastSpell());
    }

    @Test
    void shouldNotAllowToCastSecondSpellAfterEndOfTurn() {
        SpellBook spellBook = new SpellBook(10, List.of(SpellFactoryForTests.createMagicArrow()));

        assertTrue(spellBook.canCastSpell());

        spellBook.cast(SpellFactoryForTests.createMagicArrow());
        assertFalse(spellBook.canCastSpell());

        spellBook.endOfTurn();

        assertTrue(spellBook.canCastSpell());
    }

    @Test
    void shouldCanCastSpellIfHasEnoughMana() {
        SpellBook spellBook = new SpellBook(10, List.of(SpellFactoryForTests.createMagicArrow()));
        assertTrue(spellBook.canCastSpell(SpellFactoryForTests.createMagicArrow()));
    }

    @Test
    void shouldCannotCastSpellIfHasNotEnoughMana() {
        SpellBook spellBook = new SpellBook(2, List.of(SpellFactoryForTests.createMagicArrow()));
        assertFalse(spellBook.canCastSpell(SpellFactoryForTests.createMagicArrow()));
    }

    @Test
    void shouldSubtractManaCorrectly() {
        SpellBook spellBook = new SpellBook(10, List.of(SpellFactoryForTests.createMagicArrow()));
        spellBook.cast(SpellFactoryForTests.createMagicArrow());
        assertEquals(5, spellBook.getCurrentMana());
    }
}