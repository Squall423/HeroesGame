package pl.sdk.creatures;

public class EconomyCreature {

    private final CreatureStatistic stats;
    private final int amount;
    private final int goldCost;
    private final String description;

    public EconomyCreature(CreatureStatistic aStats, int aAmount, int aGoldCost, String aDescription) {
        stats = aStats;
        amount = aAmount;
        goldCost = aGoldCost;
        description = aDescription;
    }
    
}
