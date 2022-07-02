package pl.sdk.hero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdk.spells.AbstractEconomySpellFactory;
import pl.sdk.spells.SpellFactoryType;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.sdk.SpellsStatistic.*;

class SpellShopTest {

    private EconomyEngine economyEngine;
    private final AbstractEconomySpellFactory spellFactory =
            AbstractEconomySpellFactory.getInstance(SpellFactoryType.TEST);
    private Player player1;


    @BeforeEach
    void init() {
        Random playerOneRand = mock(Random.class);
        when(playerOneRand.nextDouble()).thenReturn(1.0);
        Random playerTwoRand = mock(Random.class);
        when(playerTwoRand.nextDouble()).thenReturn(0.2);
        SpellShop shop1 = new SpellShop(playerOneRand, spellFactory);
        SpellShop shop2 = new SpellShop(playerTwoRand, spellFactory);
        EconomyHero hero1 = new EconomyHero();
        EconomyHero hero2 = new EconomyHero();
        player1 = new Player(hero1, shop1, 1000);
        Player player2 = new Player(hero2, shop2, 1000);
        economyEngine = new EconomyEngine(player1, player2);
    }

    @Test
    void shouldCorrectlyRandomizePopulationForBothPlayers() {

        assertEquals(9, economyEngine.getCurrentSpellPopulation().size());
        economyEngine.pass();
        assertEquals(5, economyEngine.getCurrentSpellPopulation().size());
    }

    @Test
    void afterRoundEndPopulationInShopShouldBeRandomized() {

        economyEngine.pass();
        economyEngine.pass();
        assertEquals(9, economyEngine.getCurrentSpellPopulation().size());
        economyEngine.pass();
        assertEquals(5, economyEngine.getCurrentSpellPopulation().size());
    }

    @Test
    void heroShouldCanBuySpell() {

        economyEngine.getActivePlayer().getGold();
        economyEngine.buySpell(spellFactory.create(TEST_HASTE.getName()));
        assertEquals(700, player1.getGold());
        assertEquals(1, player1.getSpells().size());
    }

    @Test
    void heroShouldCanBuyMoreThanOneSpell() {
        economyEngine.buySpell(spellFactory.create(TEST_HASTE.getName()));
        economyEngine.buySpell(spellFactory.create(TEST_MAGIC_ARROW.getName()));

        assertEquals(400, player1.getGold());
        assertEquals(2, player1.getSpells().size());
    }

    @Test
    void heroCannotBuySpellWhenHasNotEnoughGold() {
        economyEngine.buySpell(spellFactory.create(TEST_HASTE.getName()));
        assertEquals(700, player1.getGold());
        economyEngine.buySpell(spellFactory.create(TEST_MAGIC_ARROW.getName()));
        assertEquals(400, player1.getGold());
        assertThrows(IllegalStateException.class,
                () -> economyEngine.buySpell(spellFactory.create(TEST_TELEPORT.getName())));
        assertEquals(400, player1.getGold());
        assertEquals(2, player1.getSpells().size());
    }

    @Test
    void heroOnePurchaseShouldNotAffectOnHeroTwoPopulation() {
        Random playerTwoRand = mock(Random.class);
        when(playerTwoRand.nextDouble()).thenReturn(1.0);
        SpellShop shopBuy2 = new SpellShop(playerTwoRand, spellFactory);
        EconomyHero heroBuy2 = new EconomyHero();
        Player playerBuy2 = new Player(heroBuy2, shopBuy2, 1000);
        economyEngine = new EconomyEngine(player1, playerBuy2);

        economyEngine.buySpell(spellFactory.create(TEST_HASTE.getName()));

        assertEquals(8, economyEngine.getCurrentSpellPopulation().size());
        assertEquals(1, player1.getSpells().size());

        economyEngine.pass();
        economyEngine.buySpell(spellFactory.create(TEST_HASTE.getName()));

        assertEquals(8, economyEngine.getCurrentSpellPopulation().size());
        assertEquals(1, playerBuy2.getSpells().size());
    }

    @Test
    void afterPurchaseSpellPopulationShouldBeDecreasedByBought1() {
        economyEngine.buySpell(spellFactory.create(TEST_HASTE.getName()));
        assertEquals(8, economyEngine.getCurrentSpellPopulation().size());
    }

    @Test
    void shouldThrowExceptionWhenTryToPurchaseTheSameSpellAgain() {
        economyEngine.buySpell(spellFactory.create(TEST_HASTE.getName()));

        assertEquals(8, economyEngine.getCurrentSpellPopulation().size());
        assertEquals(1, player1.getSpells().size());

        assertThrows(IllegalStateException.class,
                () -> economyEngine.buySpell(spellFactory.create(TEST_HASTE.getName())));

    }

    @Test
    void shouldCorrectlyCheckIfPlayerHasSpell() {
        economyEngine.buySpell(spellFactory.create(TEST_HASTE.getName()));

        assertTrue(economyEngine.hasSpell(TEST_HASTE.getName()));
        assertFalse(economyEngine.hasSpell(TELEPORT.getName()));
    }
}