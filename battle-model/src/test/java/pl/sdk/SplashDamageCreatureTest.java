package pl.sdk;

import org.junit.jupiter.api.Test;
import pl.sdk.creatures.Creature;
import pl.sdk.creatures.NecropolisFactory;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class SplashDamageCreatureTest {

    @Test
    void checkSplashDamage(){
        Creature splashCreature = new NecropolisFactory().create(true,5);
        Creature defender = spy(Creature.class);
        Creature splashMock1 = spy(Creature.class);
        Creature splashMock2 = spy(Creature.class);
        Creature splashMock3 = spy(Creature.class);
        Creature splashMock4 = spy(Creature.class);
        Creature notSplashMock1 = spy(Creature.class);
        Creature notSplashMock2 = spy(Creature.class);
        Creature notSplashMock3 = spy(Creature.class);

        Board board = new Board();
        board.add(new Point(10,10),defender);
        board.add(new Point(10,9),splashMock1);
        board.add(new Point(10,11),splashMock2);
        board.add(new Point(9,10),splashMock3);
        board.add(new Point(11,10),splashMock4);
        board.add(new Point(11,11),splashMock4);
        board.add(new Point(11,13),splashMock4);
        board.add(new Point(0,2),splashMock4);

        GameEngine gameEngine = new GameEngine(List.of(splashCreature), Collections.emptyList(), board);
        gameEngine.attack(10,10);

        verify(defender).applyDamage(anyInt());
        verify(notSplashMock1,never()).applyDamage(anyInt());
        verify(notSplashMock2,never()).applyDamage(anyInt());
        verify(notSplashMock3,never()).applyDamage(anyInt());
        verify(splashMock1).applyDamage(anyInt());
        verify(splashMock2).applyDamage(anyInt());
        verify(splashMock3).applyDamage(anyInt());
        verify(splashMock4).applyDamage(anyInt());

    }
}
