package pl.sdk.creatures;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.sdk.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ShootingAndBlockCounterAttackCreatureTest {

    int NOT_IMPORTANT = 5;
    int MORE_THAN_SECOND_CREATURE = NOT_IMPORTANT + 1;


    @Test
    void creatureCanAttackEvenDistanceToOpponentIsMoreThanOne() {
        Creature shootingCreature = new Creature.Builder()
                .name("shooter")
                .maxHp(NOT_IMPORTANT)
                .attack(NOT_IMPORTANT)
                .armor(NOT_IMPORTANT)
                .damage(Range.closed(NOT_IMPORTANT, NOT_IMPORTANT))
                .moveRange(MORE_THAN_SECOND_CREATURE)
                .amount(NOT_IMPORTANT)
                .build();
        Creature normalCreature = new Creature.Builder()
                .name("Normal unit")
                .maxHp(NOT_IMPORTANT)
                .attack(NOT_IMPORTANT)
                .armor(NOT_IMPORTANT)
                .damage(Range.closed(NOT_IMPORTANT, NOT_IMPORTANT))
                .moveRange(MORE_THAN_SECOND_CREATURE)
                .amount(NOT_IMPORTANT)
                .build();

        GameEngine engine = new GameEngine(List.of(shootingCreature), List.of(normalCreature));

        assertTrue(engine.canAttack(GameEngine.BOARD_WIDTH - 1, 1));
        engine.pass();
        assertFalse(engine.canAttack(0, 1));
    }

    @Test
    void defenderShouldNotCounterAttackForShootingCreature() {
        Creature shootingCreature = Mockito.spy(ShootingCreatureDecorator.class);
        Creature normalCreature = spy(Creature.class);

        shootingCreature.attack(normalCreature);
        verify(shootingCreature, never()).applyDamage((anyInt()));
        verify(normalCreature, atMost(1)).applyDamage(anyInt());

        normalCreature.attack(normalCreature);
        verify(shootingCreature, atMost(1)).applyDamage(anyInt());
        verify(shootingCreature, atMost(2)).applyDamage(anyInt());

    }

    @Test
    void defenderShouldNotCounterAttackForBlockCounterAttackCreature() {
        Creature blockCounterAttackCreature = new Creature.Builder()
                .name("shooter")
                .maxHp(100)
                .attack(10)
                .armor(10)
                .damage(Range.closed(10, 10))
                .moveRange(NOT_IMPORTANT)
                .amount(1)
                .build();
        Creature normalCreature = new Creature.Builder()
                .name("Normal unit")
                .maxHp(100)
                .attack(10)
                .armor(10)
                .damage(Range.closed(10, 10))
                .moveRange(10)
                .amount(1)
                .build();

        blockCounterAttackCreature = new ShootingCreatureDecorator(new BlockCounterAttackCreatureDecorator(blockCounterAttackCreature));
        blockCounterAttackCreature.attack(normalCreature);
        blockCounterAttackCreature.getAttackRange();

        assertEquals(100,blockCounterAttackCreature.getCurrentHp());
        assertEquals(1,blockCounterAttackCreature.getAmount());
    }
}
