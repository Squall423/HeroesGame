package pl.sdk.spells;

import pl.sdk.SpellsStatistic;

public class EconomySpell {
    private final SpellsStatistic spellStats;
    private final int goldCost;

    EconomySpell(SpellsStatistic aSpellStats, int aGoldCost) {
        spellStats = aSpellStats;

        goldCost = aGoldCost;
    }
}
