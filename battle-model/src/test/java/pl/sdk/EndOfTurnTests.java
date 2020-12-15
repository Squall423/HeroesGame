package pl.sdk;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EndOfTurnTests {

    @Test
    void shouldResetCounterAttackFlagAfterEndOfTurn(){
        Creature attacker = new Creature();
        Creature defender = new Creature();
        GameEngine engine = new GameEngine(List.of(attacker), List.of(defender));

        assertEquals(true, defender.canCounterAttack());
        attacker.attack(defender);
        assertEquals(false, defender.canCounterAttack());

        engine.pass();
        engine.pass();
        assertEquals(true, defender.canCounterAttack());

    }
}

