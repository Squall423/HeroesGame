package pl.sdk;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ShootingAndBlockCounterAttackCreatureTest {

    int NOT_IMPORTANT = 5;
    int MORE_THAN_SECOND_CREATURE = NOT_IMPORTANT + 1;


    @Test
    void creatureCanAttackEvenDistanceToOpponentIsMoreThanOne() {
        Creature shootingCreature = new ShootingCreature.Builder()
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

        assertTrue(engine.canAttack(Board.WIDTH - 1, 1));
        engine.pass();
        assertFalse(engine.canAttack(0, 1));
    }

    @Test
    void defenderShouldNotCounterAttackForShootingCreature() {
        Creature shootingCreature = spy(ShootingCreature.class);
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
        Creature blockCreature = spy(BlockCounterAttackCreature.class);
        Creature normalCreature = spy(Creature.class);

        blockCreature.attack(normalCreature);
        verify(blockCreature, never()).applyDamage((anyInt()));
        verify(normalCreature, atMost(1)).applyDamage(anyInt());

        normalCreature.attack(normalCreature);
        verify(blockCreature, atMost(1)).applyDamage(anyInt());
        verify(normalCreature, atMost(2)).applyDamage(anyInt());

    }
}
