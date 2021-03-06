package pl.sdk.creatures;

import com.google.common.collect.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DoubleDamageChanceCalculatorTest {

    private static final int NOT_IMPORTANT = 5;
    private static final int THE_SAME_FOR_BOTH_CREATURES = 10;
    private Creature defender;
    private Creature attacker;
    private Random rand;

    @BeforeEach
    void init() {
        attacker = new Creature.BuilderForTesting()
                .name("Double Damage Chance Test Unit")
                .attack(THE_SAME_FOR_BOTH_CREATURES)
                .armor(THE_SAME_FOR_BOTH_CREATURES)
                .maxHp(NOT_IMPORTANT)
                .amount(1)
                .moveRange(NOT_IMPORTANT)
                .damage(Range.closed(10, 20))
                .amount(10)
                .build();

        defender = new Creature.BuilderForTesting()
                .name("Defender")
                .attack(THE_SAME_FOR_BOTH_CREATURES)
                .armor(THE_SAME_FOR_BOTH_CREATURES)
                .maxHp(NOT_IMPORTANT)
                .moveRange(NOT_IMPORTANT)
                .damage(Range.closed(NOT_IMPORTANT, NOT_IMPORTANT))
                .amount(NOT_IMPORTANT)
                .build();
        rand = mock(Random.class);
        when(rand.nextInt(anyInt())).thenReturn(0);
    }

    @Test
    void shouldDealDoubleDamageIfRandomPositive() {
        when(rand.nextDouble()).thenReturn(0.2);
        CalculateDamageStrategy calc = new CalculateDamageIncreaseWithRandomChanceStrategy(0.2,2,rand);

        int result = calc.calculateDamage(attacker, defender);

        assertEquals(200, result);
    }

    @Test
    void shouldDealNormalDamageIfRandomPositive() {
        when(rand.nextDouble()).thenReturn(0.21);
        CalculateDamageStrategy calc = new CalculateDamageIncreaseWithRandomChanceStrategy(0.2,2,rand);

        int result = calc.calculateDamage(attacker, defender);

        assertEquals(100, result);
    }
}