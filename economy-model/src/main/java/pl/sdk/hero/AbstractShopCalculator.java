package pl.sdk.hero;

import java.util.Random;

public abstract class AbstractShopCalculator {

    private final Random rand;
    private double randomFactor;

    public AbstractShopCalculator() {
        rand = new Random();
        generateRandomFactor();
    }

    public AbstractShopCalculator(Random aRand) {
        rand = aRand;
        generateRandomFactor();
    }

    abstract int calculateMaxAmount(int aHeroGold, Integer aAmount, int aGoldCost);

    void generateRandomFactor() {
        randomFactor = 0.5 + (1 - 0.5) * rand.nextDouble();
    }

    int randomize(int aPopulation) {
        return (int) (aPopulation * randomFactor);
    }
}
