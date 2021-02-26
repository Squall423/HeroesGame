package pl.sdk;

public class ShootingCreature extends Creature {

    ShootingCreature(CreatureStatistic aStats) {
        super(aStats);
    }

    @Override
    double getAttackRange() {
        return Double.MAX_VALUE;
    }

    @Override
    void counterAttack(Creature aDefender) {
        
    }

    public static final class Builder extends Creature.Builder {


        @Override
        Creature createInstance(CreatureStatistic aStats) {
            return new ShootingCreature(aStats);
        }
    }


}
