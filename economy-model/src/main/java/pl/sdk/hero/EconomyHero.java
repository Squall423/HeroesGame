package pl.sdk.hero;

import pl.sdk.creatures.EconomyCreature;
import pl.sdk.spells.EconomySpell;

import java.util.ArrayList;
import java.util.List;

public class EconomyHero {

    private final List<EconomyCreature> creatureList;
    private final HeroStats stats;
    private final List<EconomySpell> spellList;


    public EconomyHero() {
        this(new HeroStats(0, 0, 0, 0));
    }

    EconomyHero(HeroStats aStats) {
        creatureList = new ArrayList<>();
        spellList = new ArrayList<>();
        stats = aStats;
    }


    void addCreature(EconomyCreature aCreature) {
        if (creatureList.size() >= 7) {
            throw new IllegalStateException("Hero has not empty slot for creature");
        }
        creatureList.add(aCreature);
    }

    public List<EconomyCreature> getCreatures() {
        return creatureList;
    }

    void addSpell(EconomySpell aEconomySpell) {
        spellList.add(aEconomySpell);
    }

    public List<EconomySpell> getSpells() {
        return List.copyOf(spellList);
    }

    public int getPower() {
        return stats.getPower();
    }

    public int getWisdom() {
        return stats.getWisdom();
    }
}