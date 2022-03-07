package pl.sdk;

import pl.sdk.creatures.Creature;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

class TurnQueue {
    private final Collection<Creature> creatures;
    private final Queue<Creature> creaturesQueue;
    private final Hero hero1;
    private final Hero hero2;
    private Creature activeCreature;
    private final PropertyChangeSupport observerSupport;

    public TurnQueue(Hero aHero1, Hero aHero2) {
        observerSupport = new PropertyChangeSupport(this);
        creaturesQueue = new LinkedList<>();
        hero1 = aHero1;
        hero2 = aHero2;
        List<Creature> twoSidesCreatures = new ArrayList<>();
        twoSidesCreatures.addAll(hero1.getCreatures());
        twoSidesCreatures.addAll(hero2.getCreatures());
        twoSidesCreatures.sort((c1, c2) -> c2.getMoveRange() - c1.getMoveRange());
        twoSidesCreatures.forEach(this::addObserver);
        creatures = twoSidesCreatures;
        initQueue();
        next();
    }

    void addObserver(PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(GameEngine.END_OF_TURN, aObserver);
    }

    void removeObserver(PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aObserver);
    }

    void notifyObservers() {
        observerSupport.firePropertyChange(new PropertyChangeEvent(this, GameEngine.END_OF_TURN, null, null));
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

    Hero getActiveHero() {
        if (hero1.getCreatures().contains(activeCreature)) {
            return hero1;
        } else {
            return hero2;
        }
    }
}
