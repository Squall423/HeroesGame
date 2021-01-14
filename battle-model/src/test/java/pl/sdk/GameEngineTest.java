package pl.sdk;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameEngineTest {

    private Creature attacker;
    private Creature defender;
    GameEngine engine;

    @BeforeEach
    void init() {

        attacker = new Creature("DefName", 1, 1, 10, 10);
        defender = new Creature("DefName", 1, 1, 10, 10);
        engine = new GameEngine(List.of(attacker), List.of(defender));
    }

    @Test
    void creatureCanAttack() {

      engine.move(new Point(4, 5));
      assertTrue(engine.canAttack(5, 5));
      engine.pass();
      assertFalse(engine.canAttack(5, 5));
    }

}