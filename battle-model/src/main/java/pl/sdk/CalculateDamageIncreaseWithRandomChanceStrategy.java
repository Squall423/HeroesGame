package pl.sdk;

import java.util.Random;

public class CalculateDamageIncreaseWithRandomChanceStrategy extends AbstractCalculateDamageStrategy {


    private final double chanceToIncrease;
    private final double increaseFactor;

    CalculateDamageIncreaseWithRandomChanceStrategy(double aChance, double aIncreaseFactor) {
        this(aChance, aIncreaseFactor, new Random());
    }

    public CalculateDamageIncreaseWithRandomChanceStrategy(double aChance, double aIncreaseFactor, Random aRand) {
        super(aRand);
        chanceToIncrease = aChance;
        increaseFactor = aIncreaseFactor;

    }

    @Override
    double changeDamageAfter(double aDamageToChange, Creature aAttacker) {

        if (getRand().nextDouble() <= chanceToIncrease) {
            aDamageToChange = aDamageToChange * increaseFactor;

        }
        return aDamageToChange;
    }
}
