package pl.sdk.creatures;

import com.google.common.collect.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ApplyDamageTest {
    public static final int NOT_IMPORTANT = 5;
    private static final int IMMORTAL = 99999;
    private Creature defender;

    @BeforeEach
    void init() {
        defender = new Creature.BuilderForTesting()
                .name("Name")
                .attack(NOT_IMPORTANT)
                .armor(NOT_IMPORTANT)
                .maxHp(100)
                .amount(10)
                .moveRange(NOT_IMPORTANT)
                .damage(Range.closed(NOT_IMPORTANT, NOT_IMPORTANT))
                .build();
    }

    @Test
    void shouldLostOneCreatureFromStackAndHasFullHp() {
        Creature attacker = new Creature.BuilderForTesting()
                .name("Name")
                .attack(NOT_IMPORTANT)
                .armor(NOT_IMPORTANT)
                .maxHp(NOT_IMPORTANT)
                .moveRange(1)
                .damage(Range.closed(100, 100))
                .build();

        attacker.attack(defender);
        assertEquals(9, defender.getAmount());
        assertEquals(100, defender.getCurrentHp());

    }

    @Test
    void shouldLostTwoCreatureFromStackAndHasFullHp() {
        Creature attacker = new Creature.BuilderForTesting()
                .name("Name")
                .attack(NOT_IMPORTANT)
                .armor(NOT_IMPORTANT)
                .maxHp(NOT_IMPORTANT)
                .moveRange(1)
                .damage(Range.closed(200, 200))
                .build();
        attacker.attack(defender);
        assertEquals(8, defender.getAmount());
        assertEquals(100, defender.getCurrentHp());
    }

    @Test
    void shouldLostOneCreatureFromnStackAndHas1Hp() {
        Creature attacker = new Creature.BuilderForTesting()
                .name("Name")
                .attack(NOT_IMPORTANT)
                .armor(NOT_IMPORTANT)
                .maxHp(NOT_IMPORTANT)
                .moveRange(1)
                .damage(Range.closed(199, 199))
                .build();
        attacker.attack(defender);
        assertEquals(9, defender.getAmount());
        assertEquals(1, defender.getCurrentHp());
    }

    @Test
    void shouldLost99HpButWithoutCreatureFromStack() {
        Creature attacker = new Creature.BuilderForTesting()
                .name("Name")
                .attack(NOT_IMPORTANT)
                .armor(NOT_IMPORTANT)
                .maxHp(NOT_IMPORTANT)
                .moveRange(1)
                .damage(Range.closed(99, 99))
                .build();
        attacker.attack(defender);
        assertEquals(10, defender.getAmount());
        assertEquals(1, defender.getCurrentHp());
    }

    @Test
    void shouldLost198HpBecauseAttackTwiceShouldBe9StackAnd2Hp() {
        Creature attacker = new Creature.BuilderForTesting()
                .name("Name")
                .attack(NOT_IMPORTANT)
                .armor(NOT_IMPORTANT)
                .maxHp(IMMORTAL)
                .moveRange(1)
                .damage(Range.closed(99, 99))
                .build();
        attacker.attack(defender);
        attacker.attack(defender);
        assertEquals(9, defender.getAmount());
        assertEquals(2, defender.getCurrentHp());
    }
}
