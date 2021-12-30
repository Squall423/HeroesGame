package pl.sdk.hero;

import pl.sdk.creatures.EconomyCreature;

import java.util.Random;

public class CreatureShopCalculator {

    private final Random rand;
    private double randomFactor;

     CreatureShopCalculator() {
        rand = new Random();
        generateRandomFactor();
    }

     CreatureShopCalculator(Random aRand) {
        rand = aRand;
        generateRandomFactor();

    }

    void generateRandomFactor() {
        randomFactor = 0.5 + (1 - 0.5) * rand.nextDouble();
    }

    int calculateMaxAmount(EconomyHero aHero, EconomyCreature aCreature) {
        return randomize(getSmallerValue(aHero.getGold() / aCreature.getGoldCost(), aCreature.getGrowth()));
    }

    private int getSmallerValue(int aHero, int aCreature) {
        return Math.min(aHero, aCreature);
    }

    private int randomize(int aSmallerValue) {
        return (int) (aSmallerValue * randomFactor);
    }
}
