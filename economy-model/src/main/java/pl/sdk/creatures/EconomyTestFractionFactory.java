package pl.sdk.creatures;

public class EconomyTestFractionFactory extends AbstractEconomyFractionFactory {

    private static final String EXCEPTION_MESSAGE = "We support tiers from 1 to 7)";

    public EconomyCreature create(boolean aIsUpgraded, int aTier, int aAmount) {
        if (!aIsUpgraded) {
            switch (aTier) {
                case 1:
                    return new EconomyCreature(CreatureStatistic.TEST_TIER_1_NOT_UPGRADED, aAmount, 60);
                case 2:
                    return new EconomyCreature(CreatureStatistic.TEST_TIER_2_NOT_UPGRADED, aAmount, 100);
                case 3:
                    return new EconomyCreature(CreatureStatistic.TEST_TIER_3_NOT_UPGRADED, aAmount, 200);
                case 4:
                    return new EconomyCreature(CreatureStatistic.TEST_TIER_4_NOT_UPGRADED, aAmount, 360);
                case 5:
                    return new EconomyCreature(CreatureStatistic.TEST_TIER_5_NOT_UPGRADED, aAmount, 550);
                case 6:
                    return new EconomyCreature(CreatureStatistic.TEST_TIER_6_NOT_UPGRADED, aAmount, 1200);
                case 7:
                    return new EconomyCreature(CreatureStatistic.TEST_TIER_7_NOT_UPGRADED, aAmount, 1800);
                default:
                    throw new IllegalArgumentException(EXCEPTION_MESSAGE);
            }
        } else {
            switch (aTier) {
                case 1:
                    return new EconomyCreature(CreatureStatistic.TEST_TIER_1_UPGRADED, aAmount, 60);
                case 2:
                    return new EconomyCreature(CreatureStatistic.TEST_TIER_2_UPGRADED, aAmount, 125);
                case 3:
                    return new EconomyCreature(CreatureStatistic.TEST_TIER_3_UPGRADED, aAmount, 230);
                case 4:
                    return new EconomyCreature(CreatureStatistic.TEST_TIER_4_UPGRADED, aAmount, 500);
                case 5:
                    return new EconomyCreature(CreatureStatistic.TEST_TIER_5_UPGRADED, aAmount, 600);
                case 6:
                    return new EconomyCreature(CreatureStatistic.TEST_TIER_6_UPGRADED, aAmount, 1500);
                case 7:
                    return new EconomyCreature(CreatureStatistic.TEST_TIER_7_UPGRADED, aAmount, 3000);
                default:
                    throw new IllegalArgumentException(EXCEPTION_MESSAGE);
            }
        }
    }

}
