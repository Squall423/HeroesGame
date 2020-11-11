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
        board.add(new Point(0, 0),creature);
    }
    @Test
}