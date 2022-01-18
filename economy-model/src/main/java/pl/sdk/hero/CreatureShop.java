package pl.sdk.hero;

import pl.sdk.creatures.EconomyCreature;
import pl.sdk.creatures.EconomyNecropolisFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Random;

import static pl.sdk.hero.EconomyEngine.ACTIVE_HERO_CHANGED;
import static pl.sdk.hero.EconomyEngine.NEXT_ROUND;

public class CreatureShop implements PropertyChangeListener {

    private CreatureShopCalculator calculator;
    private final HashMap<Integer, Integer> heroOnePopulation;
    private final HashMap<Integer, Integer> heroTwoPopulation;
    private HashMap<Integer, Integer> currentPopulation;
    private final EconomyNecropolisFactory creatureFactory = new EconomyNecropolisFactory();

    public CreatureShop() {
        calculator = new CreatureShopCalculator();
        heroOnePopulation = new HashMap<>();
        heroTwoPopulation = new HashMap<>();
        currentPopulation = new HashMap<>();
        createPopulation(heroOnePopulation);
        createPopulation(heroTwoPopulation);
        currentPopulation = heroOnePopulation;
    }

    public CreatureShop(Random aRand) {
        calculator = new CreatureShopCalculator(aRand);
        heroOnePopulation = new HashMap<>();
        heroTwoPopulation = new HashMap<>();
        currentPopulation = new HashMap<>();
        createPopulation(heroOnePopulation);
        createPopulation(heroTwoPopulation);
        currentPopulation = heroOnePopulation;
    }

    private void createPopulation(HashMap<Integer, Integer> aPopulation) {
        aPopulation.put(1, calculatePopulation(1));
        aPopulation.put(2, calculatePopulation(2));
        aPopulation.put(3, calculatePopulation(3));
        aPopulation.put(4, calculatePopulation(4));
        aPopulation.put(5, calculatePopulation(5));
        aPopulation.put(6, calculatePopulation(6));
        aPopulation.put(7, calculatePopulation(7));
    }

    private Integer calculatePopulation(int aTier) {
        return creatureFactory.create(false, aTier, 1).getGrowth();

    }

    public void buy(EconomyHero aHero, EconomyCreature aEconomyCreature) {
        aHero.substractGold(aEconomyCreature.getGoldCost() * aEconomyCreature.getAmount());
        subtractPopulation(aEconomyCreature.getTier(), aEconomyCreature.getAmount());
        try {
            aHero.addCreature(aEconomyCreature);
        } catch (Exception e) {
            aHero.addGold(aEconomyCreature.getGoldCost() * aEconomyCreature.getAmount());
            restorePopulation(aEconomyCreature.getTier(), aEconomyCreature.getAmount());
            throw new IllegalStateException("Hero cannot consume more creature");
        }

    }

    private void subtractPopulation(int aTier, int aAmount) {
        if (currentPopulation.get(aTier) >= aAmount) {
            currentPopulation.put(aTier, currentPopulation.get(1) - aAmount);
        } else {
            throw new IllegalStateException("hero cannot buy more creatures than population is");
        }
    }

    private void restorePopulation(int aTier, int aAmount) {
        currentPopulation.put(aTier, currentPopulation.get(aTier) + aAmount);
    }

    public int calculateMaxAmount(EconomyHero aHero, EconomyCreature aCreature) {
        return calculator.calculateMaxAmount(aHero, aCreature);
    }

    public void generateRandom() {
        calculator.generateRandomFactor();
    }

    int getCurrentPopulation(int aTier) {
        return currentPopulation.get(aTier);
    }

    void changeCurrentPopulation() {
        if (currentPopulation == heroOnePopulation) {
            currentPopulation = heroTwoPopulation;
        } else {
            currentPopulation = heroOnePopulation;
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent aPropertyChangeEvent) {
        if (aPropertyChangeEvent.getPropertyName().equals(ACTIVE_HERO_CHANGED)) {
            changeCurrentPopulation();
        } else if (aPropertyChangeEvent.getPropertyName().equals(NEXT_ROUND)) {
            addPopulation(heroTwoPopulation);
            addPopulation(heroOnePopulation);
        }
    }

    private void addPopulation(HashMap<Integer, Integer> aPopulationMap) {
        aPopulationMap.put(1, aPopulationMap.get(1) + calculatePopulation(1));
        aPopulationMap.put(2, aPopulationMap.get(2) + calculatePopulation(2));
        aPopulationMap.put(3, aPopulationMap.get(3) + calculatePopulation(3));
        aPopulationMap.put(4, aPopulationMap.get(4) + calculatePopulation(4));
        aPopulationMap.put(5, aPopulationMap.get(5) + calculatePopulation(5));
        aPopulationMap.put(6, aPopulationMap.get(6) + calculatePopulation(6));
        aPopulationMap.put(7, aPopulationMap.get(7) + calculatePopulation(7));
    }
}
