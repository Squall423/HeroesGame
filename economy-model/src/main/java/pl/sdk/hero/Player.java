package pl.sdk.hero;

import pl.sdk.creatures.EconomyCreature;

import java.util.List;

public class Player {

    EconomyHero hero;
    CreatureShop creatureShop;
    private int gold;

    public Player(Fraction aFraction, int aGold) {
        hero = new EconomyHero();
        creatureShop = new CreatureShop(aFraction);
        gold = aGold;
    }

    Player(EconomyHero aHero, CreatureShop aCreatureShop, int aGold) {
        hero = aHero;
        creatureShop = aCreatureShop;
        gold = aGold;
    }

    EconomyHero getHero() {
        return hero;
    }

    CreatureShop getCreatureShop() {
        return creatureShop;
    }

    void subtractGold(int aAmount) {
        if (aAmount > gold) {
            throw new IllegalStateException("Player has not enough money");
        }
        gold -= aAmount;
    }

    void addGold(int aAmount) {
        gold += aAmount;
    }

    void addCreature(EconomyCreature aEconomyCreature) {
        hero.addCreature(aEconomyCreature);
    }

    public List<EconomyCreature> getCreatures() {
        return hero.getCreatures();
    }

    public int getGold() {
        return gold;
    }

    public void buy(Player aActivePlayer, EconomyCreature aEconomyCreature) {
        creatureShop.buy(aActivePlayer, aEconomyCreature);
    }

    public int calculateMaxAmount(EconomyCreature aCreature) {
        return creatureShop.calculateMaxAmount(this, aCreature);
    }

    public int getCurrentPopulation(int aTier) {
        return creatureShop.getCurrentPopulation(aTier);
    }
}

