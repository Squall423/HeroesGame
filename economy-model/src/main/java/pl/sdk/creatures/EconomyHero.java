package pl.sdk.creatures;

import java.util.List;

public class EconomyHero {


    public enum Fraction {
        NECROPOLIS
    }

    private final Fraction fraction;
    private final List<EconomyCreature> creatureList;
    private int gold;

    public EconomyHero(Fraction aFraction, List<EconomyCreature> aCreatureList, int aGold) {
        fraction = aFraction;
        creatureList = aCreatureList;
        gold = aGold;
    }

    void addCreature(EconomyCreature aCreature) {
        if (creatureList.size() >= 7) {
            throw new IllegalStateException("Hero has not empty slot for creature");
        }
        creatureList.add(aCreature);
    }

    void addGold(int aAmount) {
        gold += aAmount;
    }

    void substrac(int aAmount) {
        if (aAmount > gold) {
            throw new IllegalStateException("Hero has not enougjt money");
        }
        gold -= aAmount;
    }

}