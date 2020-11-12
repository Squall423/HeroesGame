package pl.sdk;

import java.util.Collection;
import java.util.List;

class CreatureTurnQueue {
    private final Collection<Creature> creatures;

    public CreatureTurnQueue(List<Creature> aCreatureList) {
        creatures = aCreatureList;
    }
}
