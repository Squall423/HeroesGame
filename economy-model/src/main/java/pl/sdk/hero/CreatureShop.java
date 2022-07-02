package pl.sdk.hero;

import pl.sdk.Fraction;
import pl.sdk.creatures.AbstractEconomyFractionFactory;
import pl.sdk.creatures.EconomyCreature;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Random;

import static pl.sdk.hero.EconomyEngine.NEXT_ROUND;

 class CreatureShop extends AbstractShop implements PropertyChangeListener {

    private CreatureShopCalculator calculator;
    private HashMap<Integer, Integer> creaturePopulation;
    private final AbstractEconomyFractionFactory creatureFactory;

    CreatureShop(Fraction aFraction) {
        calculator = new CreatureShopCalculator();
        creatureFactory = AbstractEconomyFractionFactory.getInstance(aFraction);
        creaturePopulation = new HashMap<>();
        createPopulation(creaturePopulation);
    }

    CreatureShop(Random aRand, Fraction aFraction) {
        calculator = new CreatureShopCalculator(aRand);
        creatureFactory = AbstractEconomyFractionFactory.getInstance(aFraction);
        creaturePopulation = new HashMap<>();
        createPopulation(creaturePopulation);
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

    private int calculatePopulation(int aTier) {
        return calculator.randomize(creatureFactory.create(false, aTier, 1).getGrowth());

    }

     void buy(Player aPlayer, EconomyCreature aEconomyCreature) {
        aPlayer.subtractGold(aEconomyCreature.getGoldCost() * aEconomyCreature.getAmount());
        subtractPopulation(aEconomyCreature.getTier(), aEconomyCreature.getAmount());
        try {
            aPlayer.addCreature(aEconomyCreature);
        } catch (Exception e) {
            aPlayer.addGold(aEconomyCreature.getGoldCost() * aEconomyCreature.getAmount());
            restorePopulation(aEconomyCreature.getTier(), aEconomyCreature.getAmount());
            throw new IllegalStateException("Hero cannot consume more creature");
        }

    }

    private void subtractPopulation(int aTier, int aAmount) {
        if (creaturePopulation.get(aTier) >= aAmount) {
            creaturePopulation.put(aTier, creaturePopulation.get(aTier) - aAmount);
        } else {
            throw new IllegalStateException("hero cannot buy more creatures than population is");
        }
    }

    private void restorePopulation(int aTier, int aAmount) {
        creaturePopulation.put(aTier, creaturePopulation.get(aTier) + aAmount);
    }

    public int calculateMaxAmount(Player aPlayer, EconomyCreature aCreature) {
        return calculator.calculateMaxAmount(aPlayer.getGold(), creaturePopulation.get(aCreature.getTier()),
                aCreature.getGoldCost());
    }

     void generateRandom() {
        calculator.generateRandomFactor();
    }

     int getCurrentPopulation(int aTier) {
        return creaturePopulation.get(aTier);
    }


    @Override
    public void propertyChange(PropertyChangeEvent aPropertyChangeEvent) {
        if (aPropertyChangeEvent.getPropertyName().equals(NEXT_ROUND))
        {
            generateRandom();
            addPopulation(creaturePopulation);
        }
    }

     @Override
     protected void handlePopulation() {
        addPopulation(creaturePopulation);

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
