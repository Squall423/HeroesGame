package pl.sdk;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

class CreatureTurnQueue {
    private final Collection<Creature> creatures;
    private final Queue<Creature> creaturesQueue;
    private Creature activeCreature;
    private final PropertyChangeSupport observerSupport;

    public CreatureTurnQueue(Collection<Creature> aCreatureList) {
        creatures = aCreatureList;
        creaturesQueue = new LinkedList<>();
        observerSupport = new PropertyChangeSupport(this);
        initQueue();
        next();
    }

    void addObserver(PropertyChangeListener aObserver){
        observerSupport.addPropertyChangeListener(GameEngine.END_OF_TURN, aObserver);
    }

    void removeObserver(PropertyChangeListener aObserver){
        observerSupport.addPropertyChangeListener(aObserver);
    }

    void notifyObservers() {
        observerSupport.firePropertyChange(new PropertyChangeEvent( this, GameEngine.END_OF_TURN, null, null));
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
