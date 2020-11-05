package pl.sdk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
private Board board;
private Creature creature;

    @BeforeEach
    void init(){

        Board board = new Board();
        Creature creature = new Creature();
    }
    @Test
    void shouldAddCreature() {
        Board board = new Board();
        Creature creature = new Creature();
        board.add(new Point(0, 0), creature);

        Creature creatureFromBoard = board.get(0,0);

        assertEquals(creature, creatureFromBoard);

    }
@Test
    void shouldRedturnNullWhenFiledIsEmpty(){
        Creature creatureFromBoard = board.get(0, 0);

        assertNull(creatureFromBoard);

}
@Test
    void shouldXWhenYouTryAddCreatureToNotEmptyField(){
        board.add(new Point(0, 0),creature);
        Creature creature2 = new Creature();
        board.add(new Point(0, 0),creature);

        Creature creatureFromBoard = board.get(0, 0);

        assertEquals(creature, creatureFromBoard);
}

}