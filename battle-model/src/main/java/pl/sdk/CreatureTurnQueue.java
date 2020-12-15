package pl.sdk;

import java.util.*;

class CreatureTurnQueue {
    private final Collection<Creature> creatures;
    private final Queue<Creature> creaturesQueue;
    private Creature activeCreature;
    private List<Creature> observers;

    public CreatureTurnQueue(Collection<Creature> aCreatureList) {
        creatures = aCreatureList;
        creaturesQueue = new LinkedList<>();
        observers = new ArrayList<>();
        creatures.forEach(c -> addObserver(c));
        initQueue();
        next();
    }

    void addObserver(Creature aObserver) {
        observers.add(aObserver);
    }

    void removeObserver(Creature aObserver) {
        observers.remove(aObserver);
    }

    void notifyObservers() {
        observers.forEach(o -> o.update());
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
            notifyObservers();
        }
        activeCreature = creaturesQueue.poll();
    }
}
