package pl.sdk;

import com.google.common.collect.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreatureCounterAttackTest {

    private static final int NON_IMPORTANT = 10;
    private Creature defender;
    private Creature attacker;

    @BeforeEach
    void init() {
         defender = new Creature.Builder()
                .name("Name")
                .attack(10)
                .armor(NON_IMPORTANT)
                .maxHp(100)
                .amount(1)
                .moveRange(NON_IMPORTANT)
                .damage(Range.closed(NON_IMPORTANT, NON_IMPORTANT))
                .build();

         attacker = new Creature.Builder()
                .name("Name")
                .attack(10)
                .armor(NON_IMPORTANT)
                .maxHp(100)
                .moveRange(1)
                .damage(Range.closed(100, 100))
                .build();
    }

    @Test
    void creatureShouldCounterAttack() {

        attacker.attack(defender);
        assertEquals(90, attacker.getCurrentHp());

    }

    @Test
    void creatureShouldCounterAttackOnlyOnceAtTurn() {

        attacker.attack(defender);
        attacker.attack(defender);

        assertEquals(90, attacker.getCurrentHp());
        assertEquals(90, attacker.getCurrentHp());
    }
}
