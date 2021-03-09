package pl.sdk;

import org.junit.jupiter.api.Test;
import pl.sdk.creatures.Creature;
import pl.sdk.creatures.NecropolisFactory;

import java.beans.PropertyChangeEvent;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class EndOfTurnTests {

    @Test
    void shouldResetCounterAttackFlagAfterEndOfTurn() {
        Creature attacker = NecropolisFactory.createDefaultForTests();;
        Creature defender = NecropolisFactory.createDefaultForTests();;
        GameEngine engine = new GameEngine(List.of(attacker), List.of(defender));

        assertEquals(true, defender.canCounterAttack());
        attacker.attack(defender);
        assertEquals(false, defender.canCounterAttack());

        engine.pass();
        engine.pass();
        assertEquals(true, defender.canCounterAttack());

    }

    @Test
    void shouldCallPropertyChangeAfterEndOfTurn() {
        Creature attacker = spy(Creature.class);
        Creature defender = NecropolisFactory.createDefaultForTests();;
        GameEngine engine = new GameEngine(List.of(attacker), List.of(defender));

        engine.pass();
        engine.pass();
        verify(attacker).propertyChange(any(PropertyChangeEvent.class));

    }
}

