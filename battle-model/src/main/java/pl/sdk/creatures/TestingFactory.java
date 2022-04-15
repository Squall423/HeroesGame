package pl.sdk.creatures;

import com.google.common.collect.Range;

public class TestingFactory extends AbstractFractionFactory {

    public static final String EXCEPTION_MESSAGE = "We support tiers from 1 to 7";
    public static final int FOR_MAGIC_RESISTANCE = 6;
    public static final int FOR_DAMAGE_MAGIC_SPELL = 7;

    @Override
    public Creature create(boolean aIsUpgraded, int aTier, int aAmount) {
        if (!aIsUpgraded) {
            switch (aTier) {
                default:
                    throw new IllegalArgumentException(EXCEPTION_MESSAGE);
            }
        } else {
            switch (aTier) {
                case FOR_MAGIC_RESISTANCE:
                    return new Creature.BuilderForTesting()
                            .attack(0)
                            .armor(0)
                            .name("")
                            .amount(aAmount)
                            .maxHp(100)
                            .moveRange(0)
                            .damage(Range.closed(10, 10))
                            .magicDamageApplier(new DefaultMagicDamageApplier(50))
                            .build();
                case FOR_DAMAGE_MAGIC_SPELL:
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
