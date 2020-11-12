package pl.sdk;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class CreatureTurnQueue {
    private final Collection<Creature> creatures;
    private final Queue<Creature> creaturesQueue;
    private Creature activeCreature;

    public CreatureTurnQueue(Collection<Creature> aCreatureList) {
        creatures = aCreatureList;
        creaturesQueue = new LinkedList<>();
        creaturesQueue.addAll(creatures);
        next();

    }

    Creature getActiveCreature() {
        return activeCreature;
    }

    void next() {
        activeCreature = creaturesQueue.poll();

    }
}
