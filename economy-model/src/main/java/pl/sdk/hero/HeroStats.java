package pl.sdk.hero;

public class HeroStats {

    private int attack;
    private int defence;
    private int wisdom;
    private int power;

    HeroStats(int aAttack, int aDefence, int aWisdom, int aPower) {
        attack = aAttack;
        defence = aDefence;
        wisdom = aWisdom;
        power = aPower;
    }

    int getAttack() {
        return attack;
    }

    int getDefence() {
        return defence;
    }

    int getWisdom() {
        return wisdom;
    }

    int getPower() {
        return power;
    }
}
