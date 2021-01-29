package pl.sdk;

import com.google.common.collect.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ApplyDamageTest {
    public static final int NON_IMPORTANT = 5;
    private static final int IMMORTAL = 9999;
    private Creature defender;

    @BeforeEach
    void init() {
        defender = new Creature.Builder()
                .name("Name")
                .attack(NON_IMPORTANT)
                .armor(NON_IMPORTANT)
                .maxHp(100)
                .amount(10)
                .moveRange(NON_IMPORTANT)
                .damage(Range.closed(NON_IMPORTANT, NON_IMPORTANT))
                .build();
    }

    @Test
    void shouldLostOneCreatureFromStackAndHasFullHp() {
        Creature attacker = new Creature.Builder()
                .name("Name")
                .attack(NON_IMPORTANT)
                .armor(NON_IMPORTANT)
                .maxHp(NON_IMPORTANT)
                .moveRange(1)
                .damage(Range.closed(100, 100))
                .build();

        attacker.attack(defender);
        assertEquals(9, defender.getAmount());
        assertEquals(100, defender.getCurrentHp());

    }

    @Test
    void shouldLostTwoCreatureFromStackAndHasFullHp() {
        Creature attacker = new Creature.Builder()
                .name("Name")
                .attack(NON_IMPORTANT)
                .armor(NON_IMPORTANT)
                .maxHp(NON_IMPORTANT)
                .moveRange(1)
                .damage(Range.closed(200, 200))
                .build();
        attacker.attack(defender);
        assertEquals(8, defender.getAmount());
        assertEquals(100, defender.getCurrentHp());
    }

    @Test
    void shouldLostOneCreatureFromnStackAndHas1Hp() {
        Creature attacker = new Creature.Builder()
                .name("Name")
                .attack(NON_IMPORTANT)
                .armor(NON_IMPORTANT)
                .maxHp(NON_IMPORTANT)
                .moveRange(1)
                .damage(Range.closed(199, 199))
                .build();
        attacker.attack(defender);
        assertEquals(9, defender.getAmount());
        assertEquals(1, defender.getCurrentHp());
    }

    @Test
    void shouldLost99HpButWithoutCreatureFromStack() {
        Creature attacker = new Creature.Builder()
                .name("Name")
                .attack(NON_IMPORTANT)
                .armor(NON_IMPORTANT)
                .maxHp(NON_IMPORTANT)
                .moveRange(1)
                .damage(Range.closed(99, 99))
                .build();
        attacker.attack(defender);
        assertEquals(10, defender.getAmount());
        assertEquals(1, defender.getCurrentHp());
    }

    @Test
    void shouldLost198HpBecauseAttackTwiceShouldBe9StackAnd2Hp() {
        Creature attacker = new Creature.Builder()
                .name("Name")
                .attack(NON_IMPORTANT)
                .armor(NON_IMPORTANT)
                .maxHp(IMMORTAL)
                .moveRange(1)
                .damage(Range.closed(99, 9))
                .build();
        attacker.attack(defender);
        attacker.attack(defender);
        assertEquals(9, defender.getAmount());
        assertEquals(2, defender.getCurrentHp());
    }
}
