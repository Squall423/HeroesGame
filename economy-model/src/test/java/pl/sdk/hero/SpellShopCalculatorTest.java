package pl.sdk.hero;

import org.junit.jupiter.api.Test;
import pl.sdk.Fraction;
import pl.sdk.spells.AbstractEconomySpellFactory;
import pl.sdk.spells.EconomySpell;
import pl.sdk.spells.SpellFactoryType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.sdk.SpellsStatistic.TEST_HASTE;
import static pl.sdk.SpellsStatistic.TEST_IMPLOSION;

class SpellShopCalculatorTest {

    private final AbstractEconomySpellFactory spellFactory =
            AbstractEconomySpellFactory.getInstance(SpellFactoryType.TEST);
    EconomySpell spell;

    @Test
    void shouldCorrectlyCountSpellGoldCost() {
        spell = spellFactory.create(TEST_IMPLOSION.getName());
        assertEquals(1500, spell.getGoldCost());
    }

    @Test
    void shouldReturn1IfPlayerCanAffordToFewSpellPurchase() {
        spell = spellFactory.create(TEST_HASTE.getName());
        Player player = new Player(Fraction.NECROPOLIS, 3000);
        SpellShopCalculator calculator = new SpellShopCalculator();
        assertEquals(1, calculator.calculateMaxAmount(player.getGold(), spell.getGrowth(), spell.getGoldCost()));
    }

    @Test
    void shouldReturn1IfPlayerCanAffordToExactlyOneSpellPurchase() {
        spell = spellFactory.create(TEST_HASTE.getName());
        Player player = new Player(Fraction.NECROPOLIS, 300);
        SpellShopCalculator calculator = new SpellShopCalculator();
        assertEquals(1, calculator.calculateMaxAmount(player.getGold(), spell.getGrowth(), spell.getGoldCost()));
    }

    @Test
    void shouldReturn0IfPlayerCanNotAfforToSpellPurchase() {
        spell = spellFactory.create(TEST_HASTE.getName());
        Player player = new Player(Fraction.NECROPOLIS, 100);
        SpellShopCalculator calculator = new SpellShopCalculator();
        assertEquals(0, calculator.calculateMaxAmount(player.getGold(), spell.getGrowth(), spell.getGoldCost()));
    }

}