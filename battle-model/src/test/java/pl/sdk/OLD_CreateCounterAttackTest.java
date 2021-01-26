package pl.sdk;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OLD_CreateCounterAttackTest {

    private static final int NOT_IMPORTANT = 5;
    public static final Range<Integer> NOT_IMPORTANT_RANGE = Range.closed(5, 5);

    @Test
    void creatureShouldCounterAttack(){
        Creature attacker = new Creature("Attacker",NOT_IMPORTANT,10,100,NOT_IMPORTANT,NOT_IMPORTANT_RANGE, new DamageCalculator());
        Creature defender = new Creature("Defender",20,NOT_IMPORTANT,100,NOT_IMPORTANT,NOT_IMPORTANT_RANGE, new DamageCalculator());

        attacker.attack(defender);

        assertEquals(90, attacker.getCurrentHp());

    }
    @Test
    void creatureShouldCounterAttackOnlyOnceAtTurn(){
        Creature attacker = new Creature("Attacker",NOT_IMPORTANT,10,100,NOT_IMPORTANT,NOT_IMPORTANT_RANGE, new DamageCalculator());
        Creature attacker2 = new Creature("Attacker",NOT_IMPORTANT,10,100,NOT_IMPORTANT,NOT_IMPORTANT_RANGE, new DamageCalculator());
        Creature defender = new Creature("Defender",20,NOT_IMPORTANT,100,NOT_IMPORTANT,NOT_IMPORTANT_RANGE, new DamageCalculator());

        attacker.attack(defender);
        attacker2.attack(defender);

        assertEquals(90, attacker.getCurrentHp());
        assertEquals(100, attacker2.getCurrentHp());
    }
}