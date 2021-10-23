package pl.sdk.creatures;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreatureCounterAttackTest {

    private static final int NOT_IMPORTANT = 5;

    @Test
    void creatureShouldCounterAttack(){
        Creature attacker = new Creature.BuilderForTesting()
                .name("Attacker")
                .attack(NOT_IMPORTANT)
                .armor(NOT_IMPORTANT)
                .maxHp(100)
                .moveRange(NOT_IMPORTANT)
                .damage(Range.closed(NOT_IMPORTANT,NOT_IMPORTANT))
                .build();
        Creature defender = new Creature.BuilderForTesting()
                .name("Defender")
                .attack(NOT_IMPORTANT)
                .armor(10)
                .maxHp(100)
                .moveRange(NOT_IMPORTANT)
                .damage(Range.closed(10,10))
                .build();

        attacker.attack(defender);

        assertEquals(90,attacker.getCurrentHp());
    }

    @Test
    void creatureShouldCounterAttackOnlyOnceAtTurn(){
        Creature attacker = new Creature.BuilderForTesting()
                .name("Attacker")
                .attack(NOT_IMPORTANT)
                .armor(NOT_IMPORTANT)
                .maxHp(100)
                .moveRange(NOT_IMPORTANT)
                .damage(Range.closed(NOT_IMPORTANT,NOT_IMPORTANT))
                .build();
        Creature attacker2 = new Creature.BuilderForTesting()
                .name("Attacker")
                .attack(NOT_IMPORTANT)
                .armor(NOT_IMPORTANT)
                .maxHp(100)
                .moveRange(NOT_IMPORTANT)
                .damage(Range.closed(NOT_IMPORTANT,NOT_IMPORTANT))
                .build();
        Creature defender = new Creature.BuilderForTesting()
                .name("Defender")
                .attack(NOT_IMPORTANT)
                .armor(10)
                .maxHp(100)
                .moveRange(NOT_IMPORTANT)
                .damage(Range.closed(10,10))
                .build();

        attacker.attack(defender);
        attacker2.attack(defender);

        assertEquals(90,attacker.getCurrentHp());
        assertEquals(100,attacker2.getCurrentHp());
    }
}
