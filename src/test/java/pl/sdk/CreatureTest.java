package pl.sdk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreatureTest {
   private Creature a;
   private Creature b;
   private Creature c;
   private List<Creature> creatureList;

   @BeforeEach
    void init(){
       a = new Creature();
       a = new Creature();
       a = new Creature();
       creatureList = new ArrayList<>();
       creatureList.add(a);
       creatureList.add(b);
       creatureList.add(c);
   }
@Test
void shouldChangeActiveCreature(){
    CreatureTurnQueue creatureTurnQueue = new CreatureTurnQueue(creatureList);

    assertEquals(a, creatureTurnQueue.getActiveCreature());
    creatureTurnQueue.next();


   assertEquals(b, creatureTurnQueue.getActiveCreature());
   creatureTurnQueue.next();

   assertEquals(c, creatureTurnQueue.getActiveCreature());
   creatureTurnQueue.next();
}

}