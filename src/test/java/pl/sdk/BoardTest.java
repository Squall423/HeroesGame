package pl.sdk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;
    private Creature creature;
    private Creature creature2;

    @BeforeEach
    void init() {

         board = new Board();
         creature = new Creature();
         creature2 = new Creature();
    }

    @Test
    void shouldAddCreature() {
        Board board = new Board();

        board.add(new Point(0, 0), creature);

        Creature creatureFromBoard = board.get(0, 0);

        assertEquals(creature, creatureFromBoard);

    }

    @Test
    void shouldRedturnNullWhenFiledIsEmpty() {
        Creature creatureFromBoard = board.get(0, 0);

        assertNull(creatureFromBoard);

    }

    @Test
    void shouldThrowIllegalArgumentExceptionXWhenYouTryAddCreatureToNotEmptyField() {
        board.add(new Point(0, 0), creature);

        assertThrows(IllegalArgumentException.class, () -> board.add(new Point(0, 0), creature2));

        Creature creatureFromBoard = board.get(0, 0);

        assertEquals(creature, creatureFromBoard);
    }


}