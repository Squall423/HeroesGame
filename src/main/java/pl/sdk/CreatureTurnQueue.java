package pl.sdk;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

class CreatureTurnQueue {
    private final Collection<Creature> creatures;
    private final Queue<Creature> creaturesQueue;
    private Creature activeCreature;

    public CreatureTurnQueue(Collection<Creature> aCreatureList) {
        creatures = aCreatureList;
        creaturesQueue = new LinkedList<>();
        initQueue();
        next();

    }

    private void initQueue() {
        creaturesQueue.addAll(creatures);

    }

    Creature getActiveCreature() {
        return activeCreature;
    }

    void next() {
        if (creaturesQueue.isEmpty()) {
            initQueue();
            creatures.forEach(c -> c.reset());
        }

        activeCreature = creaturesQueue.poll();


    }
}
