package pl.sdk.hero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdk.creatures.EconomyNecropolisFactory;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreatureShopTest {
    private EconomyEngine economyEngine;
    private final EconomyNecropolisFactory creatureFactory = new EconomyNecropolisFactory();

    @BeforeEach
    void init() {
        Random rand = mock(Random.class);
        when(rand.nextDouble()).thenReturn(1.0);
        CreatureShop shop = new CreatureShop(rand);
        EconomyHero hero1 = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, 1000);
        EconomyHero hero2 = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, 1000);
        economyEngine = new EconomyEngine(hero1, hero2, shop);
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

//    @Test
//    void toGameStartAfterPassCurrentPopulationShouldBeEqualToCreatureGrowth() {
//        assertEquals(12, economyEngine.getCurrentPopulation(1));
//        assertEquals(8, economyEngine.getCurrentPopulation(2));
//        assertEquals(7, economyEngine.getCurrentPopulation(3));
//        assertEquals(4, economyEngine.getCurrentPopulation(4));
//        assertEquals(3, economyEngine.getCurrentPopulation(5));
//        assertEquals(2, economyEngine.getCurrentPopulation(6));
//        assertEquals(1, economyEngine.getCurrentPopulation(7));
//    }
//
//    @Test
//    void afterRoundEndPopulationInShopShouldIncreaseByCreatureGrowth() {
//        economyEngine.pass();
//        economyEngine.pass();
//        assertEquals(24, economyEngine.getCurrentPopulation(1));
//        assertEquals(16, economyEngine.getCurrentPopulation(2));
//        assertEquals(14, economyEngine.getCurrentPopulation(3));
//        assertEquals(8, economyEngine.getCurrentPopulation(4));
//        assertEquals(6, economyEngine.getCurrentPopulation(5));
//        assertEquals(4, economyEngine.getCurrentPopulation(6));
//        assertEquals(2, economyEngine.getCurrentPopulation(7));
//        economyEngine.pass();
//        assertEquals(24, economyEngine.getCurrentPopulation(1));
//        assertEquals(16, economyEngine.getCurrentPopulation(2));
//        assertEquals(14, economyEngine.getCurrentPopulation(3));
//        assertEquals(8, economyEngine.getCurrentPopulation(4));
//        assertEquals(6, economyEngine.getCurrentPopulation(5));
//        assertEquals(4, economyEngine.getCurrentPopulation(6));
//        assertEquals(2, economyEngine.getCurrentPopulation(7));
//    }
//
//    @Test
//    void afterPurchaseCreaturePopulationShouldBeDecreasedByBoughtAmount() {
//        economyEngine.buy(creatureFactory.create(false, 1, 10));
//        assertEquals(2, economyEngine.getCurrentPopulation(1));
//    }
//
//    @Test
//    void heroOnePurchaseShouldNotAffectOnHeroTwoPopulation() {
//        economyEngine.buy(creatureFactory.create(false, 1, 10));
//        assertEquals(2, economyEngine.getCurrentPopulation(1));
//        economyEngine.pass();
//        assertEquals(12, economyEngine.getCurrentPopulation(1));
//    }
//
//    @Test
//    void shouldThrowExceptionWhenTryToPurchaseMoreCreatureThanPopulationIs() {
//        assertThrows(IllegalStateException.class, () -> economyEngine.buy(creatureFactory.create(false, 1, 13)));
//    }
//
//    @Test
//    void afterPurchaseAndRoundEndPopulationAmountShouldBeCorrect() {
//        economyEngine.buy(creatureFactory.create(false, 1, 12));
//        economyEngine.pass();
//        economyEngine.buy(creatureFactory.create(false, 2, 3));
//        economyEngine.pass();
//        assertEquals(12, economyEngine.getCurrentPopulation(1));
//        assertEquals(12, economyEngine.getCurrentPopulation(2));
//
//    }
//
//    @Test
//    void shouldCorrectlyRandomizePopulationGrowthForBothPlayers() {
//        Random rand = mock(Random.class);
//        when(rand.nextDouble()).thenReturn(0.5);
//        CreatureShop shop = new CreatureShop(rand);
//        EconomyHero hero1 = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, 1000);
//        EconomyHero hero2 = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, 1000);
//        economyEngine = new EconomyEngine(hero1, hero2, shop);
//
//        assertEquals(6, economyEngine.getCurrentPopulation(1));
//        assertEquals(4, economyEngine.getCurrentPopulation(2));
//        assertEquals(3, economyEngine.getCurrentPopulation(3));
//        assertEquals(2, economyEngine.getCurrentPopulation(4));
//        assertEquals(1, economyEngine.getCurrentPopulation(5));
//        assertEquals(1, economyEngine.getCurrentPopulation(6));
//        assertEquals(0, economyEngine.getCurrentPopulation(7));
//
//        economyEngine.pass();
//
//        assertEquals(6, economyEngine.getCurrentPopulation(1));
//        assertEquals(4, economyEngine.getCurrentPopulation(2));
//        assertEquals(3, economyEngine.getCurrentPopulation(3));
//        assertEquals(2, economyEngine.getCurrentPopulation(4));
//        assertEquals(1, economyEngine.getCurrentPopulation(5));
//        assertEquals(1, economyEngine.getCurrentPopulation(6));
//        assertEquals(0, economyEngine.getCurrentPopulation(7));
//
//        economyEngine.pass();
//
//        assertEquals(12, economyEngine.getCurrentPopulation(1));
//        assertEquals(8, economyEngine.getCurrentPopulation(2));
//        assertEquals(7, economyEngine.getCurrentPopulation(3));
//        assertEquals(4, economyEngine.getCurrentPopulation(4));
//        assertEquals(3, economyEngine.getCurrentPopulation(5));
//        assertEquals(2, economyEngine.getCurrentPopulation(6));
//        assertEquals(1, economyEngine.getCurrentPopulation(7));
//
//        economyEngine.pass();
//
//        assertEquals(12, economyEngine.getCurrentPopulation(1));
//        assertEquals(8, economyEngine.getCurrentPopulation(2));
//        assertEquals(7, economyEngine.getCurrentPopulation(3));
//        assertEquals(4, economyEngine.getCurrentPopulation(4));
//        assertEquals(3, economyEngine.getCurrentPopulation(5));
//        assertEquals(2, economyEngine.getCurrentPopulation(6));
//        assertEquals(1, economyEngine.getCurrentPopulation(7));
//    }
}