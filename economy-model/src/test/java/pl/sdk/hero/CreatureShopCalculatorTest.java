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
        EconomyHero hero1 = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, 3000);
        rand = mock(Random.class);
        when(rand.nextDouble()).thenReturn(0.5);
        CreatureShopCalculator calculator = new CreatureShopCalculator(rand);
        assertEquals(9, calculator.calculateMaxAmount(hero1, creature));
    }

    @Test
    void shouldCorrectlyCalculateMaxAmountToBuyWhenGrowthIsBiggerThanPurchaseOpportunity() {
        EconomyHero hero1 = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, 600);
        rand = mock(Random.class);
        when(rand.nextDouble()).thenReturn(1.0);
        CreatureShopCalculator calculator = new CreatureShopCalculator(rand);
        assertEquals(10, calculator.calculateMaxAmount(hero1, creature));
    }

    @Test
    void shouldCorrectlyCalculateMaxAmountToBuyWhenGrowthEqualsPurchaseOpportunity() {
        EconomyHero hero1 = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, 720);
        rand = mock(Random.class);
        when(rand.nextDouble()).thenReturn(1.0);
        CreatureShopCalculator calculator = new CreatureShopCalculator(rand);
        assertEquals(12, calculator.calculateMaxAmount(hero1, creature));
    }
}