package pl.sdk.creatures;


import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CreatureBuilderTest {

    private static final int NOT_IMPORTANT = 5;

    @Test
    void shouldCreateCorrectCreatureFromBuilder() {
        Creature creature = new Creature.Builder()
                .name("Attacker")
                .armor(5)
                .attack(10)
                .damage(Range.closed(10, 20))
                .maxHp(100)
                .moveRange(5)

                .build();
        Creature creatureFromBuilder = new Creature.Builder()
                .name("Defender")
                .armor(5)
                .attack(10)
                .damage(Range.closed(10, 20))
                .maxHp(100)
                .moveRange(5)
              
                .build();


        assertEquals(creature.getCurrentHp(),creatureFromBuilder.getCurrentHp());
        assertEquals(creatureFromBuilder.getName(),creatureFromBuilder.getName());
        assertEquals(creature.getArmor(),creatureFromBuilder.getArmor());
        assertEquals(creature.getAttack(),creatureFromBuilder.getAttack());
        assertEquals(creature.getDamage(),creatureFromBuilder.getDamage());
        assertEquals(creature.getMoveRange(),creatureFromBuilder.getMoveRange());

    }

}