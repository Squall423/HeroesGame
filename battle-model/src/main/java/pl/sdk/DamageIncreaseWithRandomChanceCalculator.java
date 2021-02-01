package pl.sdk;

import java.util.Random;

public class DamageIncreaseWithRandomChanceCalculator extends DamageCalculator {

    private final Random rand;
    private final double chanceToIncrease;
    private final double increaseFactor;

    DamageIncreaseWithRandomChanceCalculator(double aChance, double aIncreaseFactor) {
        this(aChance, aIncreaseFactor, new Random());
    }

    public DamageIncreaseWithRandomChanceCalculator(double aChance, double aIncreaseFactor, Random aRand) {
        chanceToIncrease = aChance;
        increaseFactor = aIncreaseFactor;
        rand = aRand;
    }

    int calculateDamage(Creature aAttacker, Creature aDefender) {

        int randValue =
                rand.nextInt(aAttacker.getDamage().upperEndpoint() - aAttacker.getDamage().lowerEndpoint() + 1) + aAttacker.getDamage().lowerEndpoint();

        double ret;
        if (aAttacker.getAttack() >= aDefender.getArmor()) {
            int attackPoints = aAttacker.getAttack() - aDefender.getArmor();
            if (attackPoints > 60) {
                attackPoints = 60;
            }
            ret = randValue * (1 + (attackPoints) * 0.05);
        } else {
            int defencePoints = aDefender.getArmor() - aAttacker.getAttack();
            if (defencePoints > 12) {
                defencePoints = 12;
            }
            ret = randValue * (1 - defencePoints * 0.025);
        }
        if (rand.nextDouble() <= chanceToIncrease) {
            ret = ret * increaseFactor;
        }


        if (ret < 0) {
            ret = 0;
        }
        return aAttacker.getAmount() * (int) ret;
    }
}
