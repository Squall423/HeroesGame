package pl.sdk.spells;

import org.junit.jupiter.api.Test;
import pl.sdk.SpellBook;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpellBookTest {

    @Test
    void shouldNotAllowToCastMoreThanOneSpellInRound() {
        SpellBook spellBook = new SpellBook(10);

        assertTrue(spellBook.canCastSpell());
        spellBook.cast(SpellFactoryForTests.createMagicArrow());
        assertFalse(spellBook.canCastSpell());
    }

    @Test
    void shouldNotAllowToCastSecondSpellAfterEndOfTurn() {
        SpellBook spellBook = new SpellBook(10);

        assertTrue(spellBook.canCastSpell());

        spellBook.cast(SpellFactoryForTests.createMagicArrow());
        assertFalse(spellBook.canCastSpell());

        spellBook.endOfTurn();

        assertTrue(spellBook.canCastSpell());
    }

    @Test
    void shouldCanCastSpellIfHasEnoughMana() {
        SpellBook spellBook = new SpellBook(10);
        assertTrue(spellBook.canCastSpell(SpellFactoryForTests.createMagicArrow()));
    }

    @Test
    void shouldCannotCastSpellIfHasNotEnoughMana() {
        SpellBook spellBook = new SpellBook(2);
        assertFalse(spellBook.canCastSpell(SpellFactoryForTests.createMagicArrow()));
    }
    }