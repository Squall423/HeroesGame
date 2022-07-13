package pl.sdk.converter.spells;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdk.*;
import pl.sdk.converter.SpellMasteries;
import pl.sdk.creatures.AbstractFractionFactory;
import pl.sdk.creatures.Creature;
import pl.sdk.creatures.EconomyTestFractionFactory;
import pl.sdk.spells.DamageSpell;
import pl.sdk.spells.EconomySpell;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static pl.sdk.creatures.TestingFactory.FOR_DAMAGE_MAGIC_SPELL;
import static pl.sdk.creatures.TestingFactory.FOR_MAGIC_RESISTANCE;


class DamageSpellTest {
    private EconomyTestFractionFactory cretureFactory;
    private Creature creatureForTesting;

    @BeforeEach
    void init() {
        cretureFactory = new EconomyTestFractionFactory();
    }

    @Test
    void spellShouldDeal20Damage() {
        EconomySpell toCovert = new EconomySpell(SpellsStatistic.MAGIC_ARROW);
        DamageSpell spell = (DamageSpell) new DamageSpellFactory()
                .createInner( toCovert, 1, new SpellMasteries());

        creatureForTesting = AbstractFractionFactory.getInstance(Fraction.TEST_FRACTION)
                .create(true, FOR_DAMAGE_MAGIC_SPELL, 5);

        spell.cast(creatureForTesting);

        assertEquals(5, creatureForTesting.getAmount());
        assertEquals(180, creatureForTesting.getCurrentHp());
    }

    @Test
    void spellShouldDeal10DamageBecauseHas50PercentResistance() {
        EconomySpell toCovert = new EconomySpell(SpellsStatistic.MAGIC_ARROW);
        DamageSpell spell = (DamageSpell) new DamageSpellFactory()
                .createInner( toCovert, 1, new SpellMasteries());
        creatureForTesting = AbstractFractionFactory.getInstance(Fraction.TEST_FRACTION)
                .create(true, FOR_MAGIC_RESISTANCE, 5);

        spell.cast(creatureForTesting);

        assertEquals(5, creatureForTesting.getAmount());
        assertEquals(90, creatureForTesting.getCurrentHp());
    }


    @Test
    void spellShouldDealDamageIfCreatureIsInRange() {
        Creature c1 = spy(Creature.class);
        Creature c2 = spy(Creature.class);
        Creature c3 = spy(Creature.class);
        Creature c4 = spy(Creature.class);

        GameEngine engine = new GameEngine(new Hero(List.of(c1, c2, c3, c4)), new Hero(new ArrayList<>()));
        EconomySpell toCovert = new EconomySpell(SpellsStatistic.FIRE_BALL);
        DamageSpell spell = (DamageSpell) new DamageSpellFactory().createInner( toCovert, 1,
                new SpellMasteries());

        engine.castSpell(spell, new Point(0, 3));

        verify(c1).applyDamage(anyInt());
        verify(c2).applyDamage(anyInt());
        verify(c3).applyDamage(anyInt());
        verify(c4, never()).applyDamage(anyInt());


    }

    @Test
    void spellShouldDealDamageIfCreatureIsInRange2() {
        Creature c1 = spy(Creature.class);
        Creature c2 = spy(Creature.class);
        Creature c3 = spy(Creature.class);
        Creature c4 = spy(Creature.class);

        GameEngine engine = new GameEngine(new Hero(List.of(c1, c2, c3, c4)), new Hero(new ArrayList<>()));
        EconomySpell toCovert = new EconomySpell(SpellsStatistic.FIRE_BALL);
        DamageSpell spell = (DamageSpell) new DamageSpellFactory().createInner(toCovert, 1,
                new SpellMasteries());

        engine.castSpell(spell, new Point(2, 2));

        verify(c1).applyDamage(anyInt());
        verify(c2).applyDamage(anyInt());
        verify(c3).applyDamage(anyInt());
        verify(c4, never()).applyDamage(anyInt());

    }

}