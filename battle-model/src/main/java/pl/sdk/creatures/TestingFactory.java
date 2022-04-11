package pl.sdk.creatures;

public class TestingFactory extends AbstractFractionFactory {

    public static final String EXCEPTION_MESSAGE = "We support tiers from 1 to 7";

    @Override
    public Creature create(boolean aIsUpgraded, int aTier, int aAmount) {
        if (!aIsUpgraded) {
            switch (aTier) {
                default:
                    throw new IllegalArgumentException(EXCEPTION_MESSAGE);
            }
        } else {
            switch (aTier) {
                case 7:
                    return new Creature.Builder()
                            .statistic(CreatureStatistic.TEST_TIER_7_UPGRADED)
                            .amount(aAmount)
                            .build();

                default:
                    throw new IllegalArgumentException(EXCEPTION_MESSAGE);
            }
        }
    }
}
