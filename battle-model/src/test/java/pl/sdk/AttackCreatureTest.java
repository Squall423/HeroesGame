package pl.sdk;

import com.google.common.collect.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttackCreatureTest {

    public static final int NOT_IMPORTANT = 5;
    public static final Range<Integer> NOT_IMPORTANT_RANGE = Range.closed(5, 5);

    @Test
    void creatureShouldLost10HpWhenAttackerHas20AttackAndDefenderHas10Armor() {
        Creature attacker = new Creature("Attacker",20,NOT_IMPORTANT,100,NOT_IMPORTANT,NOT_IMPORTANT_RANGE, new DamageCalculator());
        Creature defender = new Creature("Defender",NOT_IMPORTANT,10,100,NOT_IMPORTANT,NOT_IMPORTANT_RANGE, new DamageCalculator());

        attacker.attack(defender);

        assertEquals(90, defender.getCurrentHp());
    }

    @Test
    void creatureShouldNotSelfHealthWhenAttackerHasLowerAttackThanDefenderArmor() {
        Creature attacker = new Creature("Attacker",20,NOT_IMPORTANT,100,NOT_IMPORTANT,NOT_IMPORTANT_RANGE, new DamageCalculator());
        Creature defender = new Creature("Defender",NOT_IMPORTANT,30,100,NOT_IMPORTANT,NOT_IMPORTANT_RANGE, new DamageCalculator());

        attacker.attack(defender);

        assertEquals(100, defender.getCurrentHp());
    }

    @Test
    void creatureShouldDealDamagePlus10PercentWhenAttackIsGreaterThenArmorBy2Points() {
        Creature attacker = new Creature("Attacker", 10, NOT_IMPORTANT, 100, NOT_IMPORTANT, 20);
        Creature defender = new Creature("Defender", NOT_IMPORTANT, 8, 100, NOT_IMPORTANT, NOT_IMPORTANT);

        attacker.attack(defender);

        assertEquals(78, defender.getCurrentHp());
    }

    @Test
    void creatureShouldDealDamageMinus5PercentWhenArmorIsGreaterThenAttackBy2Points() {
        Creature attacker = new Creature("Attacker", 10, NOT_IMPORTANT, 100, NOT_IMPORTANT, 20);
        Creature defender = new Creature("Defender", NOT_IMPORTANT, 12, 100, NOT_IMPORTANT, NOT_IMPORTANT);

        attacker.attack(defender);

        assertEquals(81, defender.getCurrentHp());

    }
}