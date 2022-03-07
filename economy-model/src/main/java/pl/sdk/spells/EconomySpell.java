package pl.sdk.spells;


import pl.sdk.SpellsStatistic;

public class EconomySpell {
    private final SpellsStatistic spellStats;
    private final int goldCost;

    public EconomySpell(SpellsStatistic aSpellStats) {
        this(aSpellStats, 300);

    }

    public EconomySpell(SpellsStatistic aSpellStats, int aGoldCost) {
        spellStats = aSpellStats;
        goldCost = spellStats.getLevel() * 300;
    }

    public String getName() {
        return spellStats.getName();
    }

    public String getDescription() {
        return spellStats.getDescription();
    }

    public int getLevel() {
        return spellStats.getLevel();
    }

    public SpellsStatistic.SpellElement getElement() {
        return spellStats.getSpellElement();
    }

    public SpellsStatistic.SpellType getSpellType() {
        return spellStats.getSpellType();
    }

    public SpellsStatistic.TargetType getSplashType() {
        return spellStats.getTargetType();
    }

    public int getManaCost() {
        return spellStats.getManaCost();
    }

    public SpellsStatistic getSpellStatistic() {
        return spellStats;
    }
}
