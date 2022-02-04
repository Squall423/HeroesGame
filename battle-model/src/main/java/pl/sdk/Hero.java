package pl.sdk;

import pl.sdk.creatures.Creature;

import java.util.List;

public class Hero {
    private final List<Creature> creatures;

   public Hero(List<Creature> aCreatures) {
        creatures = aCreatures;
    }

    public List<Creature> getCreatures() {
        return creatures;

    }
}
