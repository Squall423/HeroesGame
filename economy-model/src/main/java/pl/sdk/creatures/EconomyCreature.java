package pl.sdk.creatures;


import com.google.common.collect.Range;

public class EconomyCreature {

    private final CreatureStatistic stats;
    private final int amount;
    private final int goldCost;


    public EconomyCreature(CreatureStatistic aStats, int aAmount, int aGoldCost) {
        stats = aStats;
        amount = aAmount;
        goldCost = aGoldCost;
    }

    public int getAmount() {
        return amount;
    }

    public int getGoldCost() {
        return goldCost;
    }

    public String getName() {
        return stats.getTranslatedName();
    }

    public boolean isUpgraded() {
        return stats.isUpgrade();
    }

    public int getTier() {
        return stats.getTier();
    }

    public int getAttack() {
        return stats.getAttack();

    }

    public int getArmor() {
        return stats.getArmor();

    }

    public int getMaxHp() {
        return stats.getMaxHp();

    }

    public int getMoveRange() {
        return stats.getMoveRange();

    }

    public Range<Integer> getDamage() {
        return stats.getDamage();

    }

    public String getDescription() {
        return stats.getDescription();

    }

    public int getGrowth() {
        return stats.getGrowth();
    }
}
