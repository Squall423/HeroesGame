package pl.sdk.hero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdk.creatures.EconomyCreature;
import pl.sdk.creatures.EconomyNecropolisFactory;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreatureShopCalculatorTest {
    private final EconomyNecropolisFactory creatureFactory = new EconomyNecropolisFactory();
    private Random rand;
    EconomyCreature creature;

    @BeforeEach
    void init() {
        creature = creatureFactory.create(false, 1, 1);
    }

    @Test
    void shouldCorrectlyCalculateMaxAmountToBuyWhenGrowthIsSmallerThanPurchaseOpportunity() {
        // randomFactor - 0.5 + (1 - 0.5) * rand.nextDouble();
        // smallerValue + randomFactor
        EconomyHero hero = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, 3000);
        rand = mock(Random.class);
        when(rand.nextDouble()).thenReturn(0.5);
        CreatureShopCalculator calculator = new CreatureShopCalculator(rand);
        assertEquals(250, calculator.calculateMaxAmount(hero.getGold(), creature.getGrowth()));
    }

    @Test
    void shouldCorrectlyCalculateMaxAmountToBuyWhenGrowthIsBiggerThanPurchaseOpportunity() {
        EconomyHero hero = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, 600);
        rand = mock(Random.class);
        when(rand.nextDouble()).thenReturn(1.0);
        CreatureShopCalculator calculator = new CreatureShopCalculator(rand);
        assertEquals(50, calculator.calculateMaxAmount(hero.getGold(), creature.getGrowth()));
    }

    @Test
    void shouldCorrectlyCalculateMaxAmountToBuyWhenGrowthEqualsPurchaseOpportunity() {
        EconomyHero hero = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, 720);
        rand = mock(Random.class);
        when(rand.nextDouble()).thenReturn(1.0);
        CreatureShopCalculator calculator = new CreatureShopCalculator(rand);
        assertEquals(60, calculator.calculateMaxAmount(hero.getGold(), creature.getGrowth()));
    }
}