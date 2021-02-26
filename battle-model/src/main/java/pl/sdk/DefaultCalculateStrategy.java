package pl.sdk;

import java.util.Random;

public class DefaultCalculateStrategy extends AbstractCalculateDamageStrategy {

    public DefaultCalculateStrategy() {
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
