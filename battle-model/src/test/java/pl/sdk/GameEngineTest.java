package pl.sdk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static pl.sdk.AttackCreatureTest.NOT_IMPORTANT;

class GameEngineTest {

    private List<Creature> creatureList;


    @BeforeEach
    void init() {
        creatureList = new ArrayList<>();

    }

    @Test
    void creatureShouldResetCounterAttackAfterNewTurn() {

        CreatureTurnQueue creatureturnqueue = new CreatureTurnQueue(creatureList);
        Creature a = new Creature("Attacker", 20, 10, 100, NOT_IMPORTANT);
        Creature b = new Creature("Attacker", 20, 10, 100, NOT_IMPORTANT);
        creatureList.add(a);
        creatureList.add(b);


        a.attack(b);
        assertTrue(b.counterAttackedInThisTurn);

        creatureturnqueue.next();
        assertFalse(b.counterAttackedInThisTurn);
        
    }
}

