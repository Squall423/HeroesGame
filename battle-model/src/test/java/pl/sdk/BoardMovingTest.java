package pl.sdk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardMovingTest {
    private Board board;
    private Creature creature;
    private Creature creature2;

    @BeforeEach
    void init() {

        board = new Board();
        creature = new Creature();
        creature2 = new Creature();
        board.add(new Point(0, 0), creature);
    }

    @Test
    void creatureShouldMoveCorrectly() {
        board.move(new Point(0, 0), new Point(0, 1));

        Creature creatureFromBoard = board.get(0, 1);

        assertEquals(creature, creatureFromBoard);
        assertNull(board.get(0, 0));
    }

    @Test
    void shouldThrowExceptionWhenCreatureTryingToMoveToNotEmptyField() {
        board.add(new Point(0, 1), new Creature());

        assertThrows(IllegalArgumentException.class, () -> board.move(new Point(0, 0), new Point(0, 0)));

        Creature creatureFromBoard = board.get(0, 0);

        assertEquals(creature, creatureFromBoard);

    }

    @Test
    void canMoveWhenCreaturesHasEnoughtMovePoint() {

        board.add(new Point(5, 5), new Creature("Defname", 1, 1, 10, 5));
        
        assertTrue(board.canMove(creature, 6, 5));
        assertTrue(board.canMove(creature, 4, 5));
        assertTrue(board.canMove(creature, 5, 4));
        assertTrue(board.canMove(creature, 5, 6));
    }

    @Test
    void cannotMoveWhenCreaturesHasNotEnoughtMovePoint() {

        board.add(new Point(5, 5), new Creature("Defname", 1, 1, 10, 5));

        assertFalse(board.canMove(creature, 6, 6));
    }


}