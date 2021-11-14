package pl.sdk.creatures;

import com.google.common.collect.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AttackCreatureTest {

    public static final int NOT_IMPORTANT = 5;
    public static final Range<Integer> NOT_IMPORTANT_RANGE = Range.closed(5, 5);
    private Random randomizer;


    @BeforeEach
    void init() {
        randomizer = mock(Random.class);
        when(randomizer.nextInt(anyInt())).thenReturn(4);

    }

    @Test
    void creatureShouldDealExactlyDamageWhenAttackAndArmorAreEquals(){
        Creature attacker = new Creature.BuilderForTesting()
                .name("Attacker")
                .attack(10)
                .armor(NOT_IMPORTANT)
                .maxHp(100)
                .moveRange(NOT_IMPORTANT)
                .damage(Range.closed(20,20))
                .build();
        Creature defender = new Creature.BuilderForTesting()
                .name("Defender")
                .attack(NOT_IMPORTANT)
                .armor(10)
                .maxHp(100)
                .moveRange(NOT_IMPORTANT)
                .damage(Range.closed(5,5))
                .build();

        attacker.attack(defender);

        assertEquals(80,defender.getCurrentHp());
    }

    @Test
    void creatureShouldDealDamagePlus10PercentWhenAttackIsGreaterThenArmorBy2Points(){
        Creature attacker = new Creature.BuilderForTesting()
                .name("Attacker")
                .attack(10)
                .armor(NOT_IMPORTANT)
                .maxHp(100)
                .moveRange(NOT_IMPORTANT)
                .damage(Range.closed(20,20))
                .build();
        Creature defender = new Creature.BuilderForTesting()
                .name("Defender")
                .attack(NOT_IMPORTANT)
                .armor(8)
                .maxHp(100)
                .moveRange(NOT_IMPORTANT)
                .damage(Range.closed(NOT_IMPORTANT,NOT_IMPORTANT))
                .build();

        attacker.attack(defender);

        assertEquals(78,defender.getCurrentHp());
    }

    @Test
    void creatureShouldDealDamageMinus5PercentWhenArmorIsGreaterThenAttackBy2Points(){
        Creature attacker = new Creature.BuilderForTesting()
                .name("Attacker")
                .attack(10)
                .armor(NOT_IMPORTANT)
                .maxHp(100).moveRange(NOT_IMPORTANT)
                .damage(Range.closed(10,20))
                .damageCalculator(new DefaultCalculateStrategy(randomizer))
                .build();
        Creature defender = new Creature.BuilderForTesting()
                .name("Defender")
                .attack(NOT_IMPORTANT)
                .armor(12)
                .maxHp(100)
                .moveRange(NOT_IMPORTANT)
                .damage(Range.closed(NOT_IMPORTANT,NOT_IMPORTANT))
                .build();
        attacker.attack(defender);

        assertEquals(87,defender.getCurrentHp());
    }

    @Test
    void shouldDealTwoXMoreDamageWhenStackHasTwoUnits(){
        Creature attacker = new Creature.BuilderForTesting()
                .name("Attacker")
                .attack(10).armor(NOT_IMPORTANT)
                .maxHp(100).moveRange(NOT_IMPORTANT)
                .damage(Range.closed(10,20))
                .damageCalculator(new DefaultCalculateStrategy(randomizer))
                .amount(2)
                .build();
        Creature defender = new Creature.BuilderForTesting()
                .name("Defender")
                .attack(NOT_IMPORTANT)
                .armor(12)
                .maxHp(100)
                .moveRange(NOT_IMPORTANT)
                .damage(Range.closed(NOT_IMPORTANT,NOT_IMPORTANT))
                .build();
        attacker.attack(defender);

        assertEquals(74,defender.getCurrentHp());
    }
}
