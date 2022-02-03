package pl.sdk.hero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdk.creatures.AbstractEconomyFractionFactory;
import pl.sdk.creatures.EconomyTestFractionFactory;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.sdk.hero.Fraction.NECROPOLIS;

class CreatureShopTest {

    private EconomyEngine economyEngine;
    private final EconomyTestFractionFactory creatureFactory = new EconomyTestFractionFactory();
    private Fraction fraction = NECROPOLIS;
    private Player player1;
    private Player player2;

    @BeforeEach
    void init() {
        Random rand = mock(Random.class);
        when(rand.nextDouble()).thenReturn(1.0);
        CreatureShop shop1 = new CreatureShop(rand, fraction);
        CreatureShop shop2 = new CreatureShop(rand, fraction);
        EconomyHero hero1 = new EconomyHero();
        EconomyHero hero2 = new EconomyHero();
        player1 = new Player(hero1, shop1, 1000);
        player2 = new Player(hero2, shop2, 1000);
        economyEngine = new EconomyEngine(player1, player2);
    }

    @Test
    void toGameStartCurrentPopulationShouldBeEqualToCreatureGrowth() {
        assertEquals(12, economyEngine.getCurrentPopulation(1));
        assertEquals(8, economyEngine.getCurrentPopulation(2));
        assertEquals(7, economyEngine.getCurrentPopulation(3));
        assertEquals(4, economyEngine.getCurrentPopulation(4));
        assertEquals(3, economyEngine.getCurrentPopulation(5));
        assertEquals(2, economyEngine.getCurrentPopulation(6));
        assertEquals(1, economyEngine.getCurrentPopulation(7));
    }

    @Test
    void toGameStartAfterPassCurrentPopulationShouldBeEqualToCreatureGrowth() {
        economyEngine.pass();
        assertEquals(12, economyEngine.getCurrentPopulation(1));
        assertEquals(8, economyEngine.getCurrentPopulation(2));
        assertEquals(7, economyEngine.getCurrentPopulation(3));
        assertEquals(4, economyEngine.getCurrentPopulation(4));
        assertEquals(3, economyEngine.getCurrentPopulation(5));
        assertEquals(2, economyEngine.getCurrentPopulation(6));
        assertEquals(1, economyEngine.getCurrentPopulation(7));
    }

    @Test
    void afterRoundEndPopulationInShopShouldIncreaseByCreatureGrowth() {
        economyEngine.pass();
        economyEngine.pass();
        assertEquals(24, economyEngine.getCurrentPopulation(1));
        assertEquals(16, economyEngine.getCurrentPopulation(2));
        assertEquals(14, economyEngine.getCurrentPopulation(3));
        assertEquals(8, economyEngine.getCurrentPopulation(4));
        assertEquals(6, economyEngine.getCurrentPopulation(5));
        assertEquals(4, economyEngine.getCurrentPopulation(6));
        assertEquals(2, economyEngine.getCurrentPopulation(7));
        economyEngine.pass();
        assertEquals(24, economyEngine.getCurrentPopulation(1));
        assertEquals(16, economyEngine.getCurrentPopulation(2));
        assertEquals(14, economyEngine.getCurrentPopulation(3));
        assertEquals(8, economyEngine.getCurrentPopulation(4));
        assertEquals(6, economyEngine.getCurrentPopulation(5));
        assertEquals(4, economyEngine.getCurrentPopulation(6));
        assertEquals(2, economyEngine.getCurrentPopulation(7));
    }

    @Test
    void afterPurchaseCreaturePopulationShouldBeDecreasedByBoughtAmount() {
        economyEngine.buy(creatureFactory.create(false, 1, 10));
        assertEquals(2, economyEngine.getCurrentPopulation(1));
        economyEngine.pass();
        economyEngine.buy(creatureFactory.create(false, 2, 8));
        assertEquals(0, economyEngine.getCurrentPopulation(2));
    }

    @Test
    void heroOnePurchaseShouldNotAffectOnHeroTwoPopulation() {
        economyEngine.buy(creatureFactory.create(false, 1, 10));
        assertEquals(2, economyEngine.getCurrentPopulation(1));
        economyEngine.pass();
        assertEquals(12, economyEngine.getCurrentPopulation(1));
    }

    @Test
    void shouldThrowExceptionWhenTryToPurchaseMoreCreatureThanPopulationIs() {
        assertThrows(IllegalStateException.class, () -> economyEngine.buy(creatureFactory.create(false, 1, 13)));
    }

    @Test
    void afterPurchaseAndRoundEndPopulationAmountShouldBeCorrect() {
        economyEngine.buy(creatureFactory.create(false, 1, 12));
        economyEngine.pass();

        economyEngine.buy(creatureFactory.create(false, 2, 3));
        economyEngine.pass();
        //heroOne
        assertEquals(12, economyEngine.getCurrentPopulation(1));
        assertEquals(16, economyEngine.getCurrentPopulation(2));
        economyEngine.pass();
        //heroTwo
        assertEquals(24, economyEngine.getCurrentPopulation(1));
        assertEquals(13, economyEngine.getCurrentPopulation(2));

    }

    @Test
    void shouldCorrectlyRandomizePopulationGrowthForBothPlayers() {
        Random rand = mock(Random.class);
        when(rand.nextDouble()).thenReturn(0.5);
        CreatureShop shop1 = new CreatureShop(rand, fraction);
        CreatureShop shop2 = new CreatureShop(rand, fraction);
        EconomyHero hero1 = new EconomyHero();
        EconomyHero hero2 = new EconomyHero();
        player1 = new Player(hero1, shop1, 1000);
        player2 = new Player(hero2, shop2, 1000);
        EconomyEngine economyEngine = new EconomyEngine(player1, player2);

        assertEquals(9, economyEngine.getCurrentPopulation(1));
        assertEquals(6, economyEngine.getCurrentPopulation(2));
        assertEquals(5, economyEngine.getCurrentPopulation(3));
        assertEquals(3, economyEngine.getCurrentPopulation(4));
        assertEquals(2, economyEngine.getCurrentPopulation(5));
        assertEquals(1, economyEngine.getCurrentPopulation(6));
        assertEquals(0, economyEngine.getCurrentPopulation(7));

        economyEngine.pass();

        assertEquals(9, economyEngine.getCurrentPopulation(1));
        assertEquals(6, economyEngine.getCurrentPopulation(2));
        assertEquals(5, economyEngine.getCurrentPopulation(3));
        assertEquals(3, economyEngine.getCurrentPopulation(4));
        assertEquals(2, economyEngine.getCurrentPopulation(5));
        assertEquals(1, economyEngine.getCurrentPopulation(6));
        assertEquals(0, economyEngine.getCurrentPopulation(7));

        economyEngine.pass();

        assertEquals(18, economyEngine.getCurrentPopulation(1));
        assertEquals(12, economyEngine.getCurrentPopulation(2));
        assertEquals(10, economyEngine.getCurrentPopulation(3));
        assertEquals(6, economyEngine.getCurrentPopulation(4));
        assertEquals(4, economyEngine.getCurrentPopulation(5));
        assertEquals(2, economyEngine.getCurrentPopulation(6));
        assertEquals(0, economyEngine.getCurrentPopulation(7));

        economyEngine.pass();

        assertEquals(18, economyEngine.getCurrentPopulation(1));
        assertEquals(12, economyEngine.getCurrentPopulation(2));
        assertEquals(10, economyEngine.getCurrentPopulation(3));
        assertEquals(6, economyEngine.getCurrentPopulation(4));
        assertEquals(4, economyEngine.getCurrentPopulation(5));
        assertEquals(2, economyEngine.getCurrentPopulation(6));
        assertEquals(0, economyEngine.getCurrentPopulation(7));
    }
}