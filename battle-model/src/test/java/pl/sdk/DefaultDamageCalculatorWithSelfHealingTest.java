package pl.sdk;

import com.google.common.collect.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultDamageCalculatorWithSelfHealingTest {
    private static final int NON_IMPORTANT = 5;
    private static final int THE_SAME_FOR_BOTH_CREATURES = 10;
    private Creature defender;
    private Creature attacker;
    private Random rand;

    @BeforeEach
    void init() {
        rand = mock(Random.class);
        when(rand.nextInt(anyInt())).thenReturn(0);
        attacker = new Creature.Builder()
                .name("Selfheal Test Unit")
                .attack(THE_SAME_FOR_BOTH_CREATURES)
                .armor(THE_SAME_FOR_BOTH_CREATURES)
                .maxHp(30)
                .amount(1)
                .moveRange(NON_IMPORTANT)
                .damage(Range.closed(10, 20))
                .damageCalculator(new DefaultDamageCalculatorWithSelfHealing(0.5, rand))
                .amount(10)
                .build();

        defender = new Creature.Builder()
                .name("Defender")
                .attack(THE_SAME_FOR_BOTH_CREATURES)
                .armor(THE_SAME_FOR_BOTH_CREATURES)
                .maxHp(NON_IMPORTANT)
                .moveRange(NON_IMPORTANT)
                .damage(Range.closed(0, 0))
                .amount(NON_IMPORTANT)
                .build();
        rand = mock(Random.class);
        when(rand.nextInt(anyInt())).thenReturn(0);
    }

    @Test
    void shouldHeal50HpBecauseAttackedFor100() {
        attacker.attack(defender);

        assertEquals(11, attacker.getAmount());
        assertEquals(20, attacker.getCurrentHp());
    }

}
