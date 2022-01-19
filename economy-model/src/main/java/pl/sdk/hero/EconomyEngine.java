package pl.sdk.hero;

import pl.sdk.creatures.EconomyCreature;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EconomyEngine {
    public static final String HERO_BOUGHT_CREATURE = "HERO_BOUGHT_CREATURE";
    public static final String ACTIVE_HERO_CHANGED = "ACTIVE_HERO_CHANGED";
    public static final String NEXT_ROUND = "NEXT_ROUND";
    public static final String END_OF_TURN = "END_OF_TURN";
    private final EconomyHero hero1;
    private final EconomyHero hero2;
    private EconomyHero activeHero;
    private final CreatureShop creatureShop;
    private int roundNumber;
    private final PropertyChangeSupport observerSupport;
    private int turnNumber;

    public EconomyEngine(EconomyHero aHero1, EconomyHero aHero2) {
        hero1 = aHero1;
        hero2 = aHero2;
        activeHero = hero1;
        roundNumber = 1;
        turnNumber = 1;
        creatureShop = new CreatureShop();
        observerSupport = new PropertyChangeSupport(this);
        addObserver(EconomyEngine.ACTIVE_HERO_CHANGED, creatureShop);
        addObserver(EconomyEngine.NEXT_ROUND, creatureShop);

    }

    public EconomyEngine(EconomyHero aHero1, EconomyHero aHero2, CreatureShop aShop) {
        hero1 = aHero1;
        hero2 = aHero2;
        activeHero = hero1;
        roundNumber = 1;
        turnNumber = 1;
        creatureShop = aShop;
        observerSupport = new PropertyChangeSupport(this);
        addObserver(EconomyEngine.ACTIVE_HERO_CHANGED, creatureShop);
        addObserver(EconomyEngine.NEXT_ROUND, creatureShop);
    }

    public void buy(EconomyCreature aEconomyCreature) {
        creatureShop.buy(activeHero, aEconomyCreature);
        observerSupport.firePropertyChange(HERO_BOUGHT_CREATURE, null, null);
    }

    public int calculateMaxAmount(EconomyHero aHero, EconomyCreature aCreature) {
        return creatureShop.calculateMaxAmount(aHero, aCreature);
    }

    public EconomyHero getActiveHero() {
        return activeHero;
    }

    public void pass() {
        if (activeHero == hero1) {
            activeHero = hero2;
            observerSupport.firePropertyChange(ACTIVE_HERO_CHANGED, hero1, activeHero);
        } else {
            activeHero = hero1;
            observerSupport.firePropertyChange(ACTIVE_HERO_CHANGED, hero2, activeHero);
            nextRound();
        }
    }

    //Round = 2 passes
    private void nextRound() {
        roundNumber += 1;
        if (roundNumber == 4) {
            endTurn();
        } else {
            hero1.addGold(2000 * roundNumber);
            hero2.addGold(2000 * roundNumber);
            observerSupport.firePropertyChange(NEXT_ROUND, roundNumber - 1, roundNumber);
        }
    }

    private void endTurn() {
        turnNumber += 1;
        roundNumber = 1;
        observerSupport.firePropertyChange(END_OF_TURN, -1, turnNumber);
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void addObserver(String aPropertyName, PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aPropertyName, aObserver);
    }

    public EconomyHero getPlayer1() {
        //TODO make copy
        return hero1;
    }

    public EconomyHero getPlayer2() {
        //TODO make copy
        return hero2;
    }

    int getTurnNumber() {
        return turnNumber;
    }

    public int getCurrentPopulation(int aTier) {
        return creatureShop.getCurrentPopulation(aTier);
    }
}
