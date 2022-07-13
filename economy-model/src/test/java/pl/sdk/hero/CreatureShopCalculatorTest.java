package pl.sdk.hero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdk.creatures.EconomyCreature;
import pl.sdk.creatures.EconomyTestFractionFactory;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.sdk.Fraction.NECROPOLIS;

class CreatureShopCalculatorTest {
    private Player player;
    private final EconomyTestFractionFactory creatureFactory = new EconomyTestFractionFactory();
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
        player = new Player(NECROPOLIS, 3000);
        rand = mock(Random.class);
        when(rand.nextDouble()).thenReturn(0.5);
        CreatureShopCalculator calculator = new CreatureShopCalculator(rand);
        assertEquals(12, calculator.calculateMaxAmount(player.getGold(), creature.getGrowth(), creature.getGoldCost()));
    }

    @Test
    void shouldCorrectlyCalculateMaxAmountToBuyWhenGrowthIsBiggerThanPurchaseOpportunity() {
        player = new Player(NECROPOLIS, 600);
        rand = mock(Random.class);
        when(rand.nextDouble()).thenReturn(1.0);
        CreatureShopCalculator calculator = new CreatureShopCalculator(rand);
        assertEquals(10, calculator.calculateMaxAmount(player.getGold(), creature.getGrowth(), creature.getGoldCost()));
    }

    @Test
    void shouldCorrectlyCalculateMaxAmountToBuyWhenGrowthEqualsPurchaseOpportunity() {
        player = new Player(NECROPOLIS, 720);
        rand = mock(Random.class);
        when(rand.nextDouble()).thenReturn(1.0);
        CreatureShopCalculator calculator = new CreatureShopCalculator(rand);
        assertEquals(12, calculator.calculateMaxAmount(player.getGold(), creature.getGrowth(), creature.getGoldCost()));
    }
}