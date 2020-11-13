package pl.sdk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttackCreatureTest {

    public static final int NOT_IMPORTANT = 5;

    @Test
    void creatureShouldLost10HpWhenAttackerHas20AttackAndDefenderHas10Armor(){
        attacker = new Creature("Attacker",20,NOT_IMPORTANT,100,NOT_IMPORTANT);
        attacker = new Creature("Defender",NOT_IMPORTANT,10,100,NOT_IMPORTANT);

        attacker.attack(defender);

        assertEquals(10, defender.getCurrenthp(10));
    }
}