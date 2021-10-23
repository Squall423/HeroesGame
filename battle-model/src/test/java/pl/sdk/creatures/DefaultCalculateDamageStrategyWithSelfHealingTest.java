package pl.sdk.creatures;

import com.google.common.collect.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultCalculateDamageStrategyWithSelfHealingTest {

    private static final int NON_IMPORTANT = 5;
    private static final int THE_SAME_FOR_BOTH_CREATURES = 10;
    private Creature defender;
    private Creature attacker;
    private Random rand;

    @BeforeEach
    void init(){
        rand = mock(Random.class);
        when(rand.nextInt(anyInt())).thenReturn(0);
        attacker = new Creature.BuilderForTesting()
                .name("Selfheal Test Unit")
                .maxHp(30)
                .attack(THE_SAME_FOR_BOTH_CREATURES)
                .armor(THE_SAME_FOR_BOTH_CREATURES)
                .damage(Range.closed(10, 20))
                .damageCalculator(new DefaultCalculateStrategy(rand))
                .moveRange(NON_IMPORTANT)
                .amount(10)
                .build();
        attacker = new HealAfterAttackCreatureDecorator(attacker, 0.5);
        defender = new Creature.BuilderForTesting()
                .name("Defender")
                .maxHp(NON_IMPORTANT)
                .attack(THE_SAME_FOR_BOTH_CREATURES)
                .armor(THE_SAME_FOR_BOTH_CREATURES)
                .damage(Range.closed(0, 0))
                .moveRange(NON_IMPORTANT)
                .amount(NON_IMPORTANT)
                .build();
    }

    @Test
    void shouldHeal50HpBecauseAttackedFor100() {
        attacker.attack(defender);

        assertEquals(11, attacker.getAmount());
        assertEquals(20, attacker.getCurrentHp());
    }

}
