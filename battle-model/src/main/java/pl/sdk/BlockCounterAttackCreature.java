package pl.sdk;

public class BlockCounterAttackCreature extends Creature {

    BlockCounterAttackCreature() {
        super();
    }

    public BlockCounterAttackCreature(CreatureStatistic aStats) {
        super(aStats);

    }

    @Override
    void counterAttack(Creature aDefender) {

    }

    public static final class Builder extends Creature.Builder {
        @Override
        Creature createInstance(CreatureStatistic aStats) {
            return new BlockCounterAttackCreature(aStats);
        }
    }

}
