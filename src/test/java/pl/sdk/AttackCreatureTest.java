package pl.sdk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttackCreatureTest {

    public static final int NOT_IMPORTANT = 5;

    @Test
    void creatureShouldLost10HpWhenAttackerHas20AttackAndDefenderHas10Armor(){
        Creature attacker = new Creature("Attacker",20,NOT_IMPORTANT,100,NOT_IMPORTANT);
        Creature defender = new Creature("Defender",NOT_IMPORTANT,10,100,NOT_IMPORTANT);

        attacker.attack(defender);

        assertEquals(90, defender.getCurrentHp());
    }

    @Test
    void creatureShouldNotSelfHealthWhenAttackerHasLowerAttackThanDefenderArmor(){
        Creature attacker = new Creature("Attacker",20,NOT_IMPORTANT,100,NOT_IMPORTANT);
        Creature defender = new Creature("Defender",NOT_IMPORTANT,30,100,NOT_IMPORTANT);

        attacker.attack(defender);

        assertEquals(100, defender.getCurrentHp());
    }


}