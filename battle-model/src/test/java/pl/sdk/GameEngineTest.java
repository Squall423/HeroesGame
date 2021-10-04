package pl.sdk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdk.creatures.Creature;
import pl.sdk.creatures.NecropolisFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;

class GameEngineTest {


    @Test
    void shouldRecognizeFriendlyCreatureAndDoNotAttackHer() {
        NecropolisFactory factory = new NecropolisFactory();
        List<Creature> l1 = List.of(factory.create(true, 5), spy(Creature.class));
        List<Creature> l2 = List.of(factory.create(true, 5), spy(Creature.class));


        GameEngine engine = new GameEngine(l1,l2);
        assertTrue(engine.canAttack(GameEngine.BOARD_WIDTH-1, 1));
        assertFalse(engine.canAttack(0, 1));
        assertFalse(engine.canAttack(0, 1));


    }
}