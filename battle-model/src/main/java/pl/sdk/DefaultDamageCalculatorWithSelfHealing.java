package pl.sdk;

import java.util.Random;

public class DefaultDamageCalculatorWithSelfHealing extends DamageCalculator {
    private final Random rand;

    private final double selfHealingPercentage;

    DefaultDamageCalculatorWithSelfHealing(double aSelfHealingPercentage) {
        this(aSelfHealingPercentage, new Random());
    }

    public DefaultDamageCalculatorWithSelfHealing(double aSelfHealingPercentage, Random aRand) {
        rand = aRand;
        selfHealingPercentage = aSelfHealingPercentage;
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
        ret = ret * aAttacker.getAmount();

        if (ret < 0) {
            ret = 0;
        }

        aAttacker.applyDamage((int) -(ret * selfHealingPercentage));

        return (int) ret;
    }
}
