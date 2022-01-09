package pl.sdk.hero;

import pl.sdk.creatures.EconomyCreature;

import java.util.Random;

public class CreatureShop {

    private CreatureShopCalculator calculator = new CreatureShopCalculator();

    public CreatureShop() {
        calculator = new CreatureShopCalculator();
    }

    public CreatureShop(Random aRand) {
        calculator = new CreatureShopCalculator(aRand);

    }

    public void buy(EconomyHero aHero, EconomyCreature aEconomyCreature) {
        aHero.substractGold(aEconomyCreature.getGoldCost() * aEconomyCreature.getAmount());
        try {
            aHero.addCreature(aEconomyCreature);
        } catch (Exception e) {
            aHero.addGold(aEconomyCreature.getGoldCost() * aEconomyCreature.getAmount());
            throw new IllegalStateException("Hero cannot consume more creature");
        }

    }

    public int calculateMaxAmount(EconomyHero aHero, EconomyCreature aCreature) {
        return calculator.calculateMaxAmount(aHero, aCreature);
    }

    public void generateRandom() {
        calculator.generateRandomFactor();
    }

    int getCurrentPopulation(int aTier) {
        return 0;
    }
}
