package pl.sdk;

public class CreatureWithSelfHealing extends Creature {

    private double selfHealingPercentage;

    public CreatureWithSelfHealing(CreatureStatistic aStats) {
        super(aStats);
    }

    @Override
    void performAfterAttack(int aDamageToDeal) {
        applyDamage((int) -(aDamageToDeal * selfHealingPercentage));
    }

    public static final class Builder extends Creature.Builder {

        private double selfHealingPercentage;

        public Builder selfHealingPercentage(double aSelfHealingPercentage) {
            selfHealingPercentage = aSelfHealingPercentage;
            return this;
        }

        @Override
         Creature createInstance(CreatureStatistic aStats) {
            return new CreatureWithSelfHealing(aStats);
        }

        @Override
        public Creature build() {
            CreatureWithSelfHealing ret = (CreatureWithSelfHealing) super.build();
            ret.selfHealingPercentage = selfHealingPercentage;
            return ret;
        }
    }
}
