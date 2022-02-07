package pl.sdk.hero;

import pl.sdk.creatures.EconomyCreature;
import pl.sdk.spells.EconomySpell;

import java.util.ArrayList;
import java.util.List;

class EconomyHero {

    private final List<EconomyCreature> creatureList;
    private final HeroStats stats;
    private final List<EconomySpell> spellList;

    EconomyHero() {
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

    List<EconomyCreature> getCreatures() {
        return creatureList;
    }

    void addSpell(EconomySpell aEconomySpell) {
        spellList.add(aEconomySpell);
    }

    List<EconomySpell> getSpells() {
        return List.copyOf(spellList);
    }

     int getPower() {
        return stats.getPower();
    }

     int getWisdom() {
        return stats.getWisdom();
    }
}