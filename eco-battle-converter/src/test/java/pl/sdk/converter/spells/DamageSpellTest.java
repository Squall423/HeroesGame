package pl.sdk.converter.spells;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdk.Fraction;
import pl.sdk.Hero;
import pl.sdk.SpellsStatistic;
import pl.sdk.converter.SpellMasteries;
import pl.sdk.creatures.AbstractFractionFactory;
import pl.sdk.creatures.Creature;
import pl.sdk.creatures.EconomyTestFractionFactory;
import pl.sdk.spells.DamageSpell;
import pl.sdk.spells.EconomySpell;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DamageSpellTest {
    private EconomyTestFractionFactory cretureFactory;
    private Creature creatureForTesting;

    @BeforeEach
    void init() {
        cretureFactory = new EconomyTestFractionFactory();
        creatureForTesting = prepareCreatureWith1KHP();

    }

    @Test
    void spellShouldDeal10Damage() {
        EconomySpell toCovert = new EconomySpell(SpellsStatistic.MAGIC_ARROW);
        DamageSpell spell = (DamageSpell) new DamageSpellFactory()
                .createInner("Magic Arrow", toCovert, 1, new SpellMasteries());

        spell.cast(creatureForTesting);

        assertEquals(5, creatureForTesting.getAmount());
        assertEquals(180, creatureForTesting.getCurrentHp());
    }

    // -----------------------------------------------------------------
    private Creature prepareCreatureWith1KHP() {
        return AbstractFractionFactory.getInstance(Fraction.TEST_FRACTION).create(true, 7, 5);
    }
}