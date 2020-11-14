package pl.sdk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateCounterAttackTest {

    private static final int NOT_IMPORTANT = 5;

    @Test
    void creatureShouldCounterAttack(){
        Creature attacker = new Creature("Attacker",NOT_IMPORTANT,10,100,NOT_IMPORTANT);
        Creature defender = new Creature("Defender",20,NOT_IMPORTANT,100,NOT_IMPORTANT);

        attacker.attack(defender);

        assertEquals(90, attacker.getCurrentHp());

    }
    @Test
    void creatureShouldCounterAttackOnlyOnceAtTurn(){
        Creature attacker = new Creature("Attacker",NOT_IMPORTANT,10,100,NOT_IMPORTANT);
        Creature attacker2 = new Creature("Attacker",NOT_IMPORTANT,10,100,NOT_IMPORTANT);
        Creature defender = new Creature("Defender",20,NOT_IMPORTANT,100,NOT_IMPORTANT);

        attacker.attack(defender);

        assertEquals(90, attacker.getCurrentHp());
        assertEquals(100, attacker2.getCurrentHp());
    }
}