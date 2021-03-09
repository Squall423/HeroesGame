package pl.sdk.creatures;

import java.util.Random;

class DefaultCalculateStrategy extends AbstractCalculateDamageStrategy {
    DefaultCalculateStrategy() {
        this(new Random());
    }

    DefaultCalculateStrategy(Random aRandomizer) {
        super(aRandomizer);
    }

    @Override
    double changeDamageAfter(double aWholeStackDamageToDeal, Creature aAttacker) {
        return aWholeStackDamageToDeal;
    }
}
