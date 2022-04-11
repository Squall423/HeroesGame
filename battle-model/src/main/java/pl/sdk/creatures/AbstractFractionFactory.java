package pl.sdk.creatures;

import pl.sdk.Fraction;

public abstract class AbstractFractionFactory {

    private static final String INVALID_FRACTION_NAME = "Invalid fraction name";


    public static AbstractFractionFactory getInstance(Fraction aFraction) {
        switch (aFraction) {
            case NECROPOLIS:
                return new NecropolisFactory();
            case TEST_FRACTION:
                return new TestingFactory();
            default:
                throw new IllegalArgumentException(INVALID_FRACTION_NAME);
        }
    }

    abstract public Creature create(boolean aIsUpgraded, int aTier, int aAmount);
}

