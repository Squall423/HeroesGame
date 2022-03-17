package pl.sdk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdk.creatures.Creature;
import pl.sdk.creatures.NecropolisFactory;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;
    private Creature creature;
    private Creature creature2;

    @BeforeEach
    void init() {

        board = new Board();
        creature = NecropolisFactory.createDefaultForTests();
    }

    @Test
    void shouldAddCreature() {
        Board board = new Board();
        board.add( new Point(0, 0), creature);
        Creature creatureFromBoard = board.get(0, 0);
        assertEquals(creature, creatureFromBoard);
    }

    @Test
    void shouldReturnNullWhenFiledIsEmpty() {
        Creature creatureFromBoard = board.get(0, 0);
        assertNull(creatureFromBoard);
    }


    @Test
    void shouldThrowIllegalArgumentExceptionXWhenYouTryAddCreatureToNotEmptyField() {
        board.add( new Point(0, 0), creature);
        Creature creature2 = NecropolisFactory.createDefaultForTests();
        assertThrows(IllegalArgumentException.class, () -> board.add( new Point(0, 0), creature2));
        Creature creatureFromBoard = board.get(0, 0);
        assertEquals(creature, creatureFromBoard);
    }

    @Test
    void shouldReturnCorrectLocationForByCreature() {
        board.add( new Point(5, 5), creature);
        Point result = board.get(creature);
        assertEquals(new Point(5, 5), result);
    }

    @Test
    void shouldReturnNullIfTheresNoCreatureOnMap() {
        Point result = board.get(creature);
        assertEquals(null, result);
    }

}