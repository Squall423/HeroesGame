package pl.sdk.hero;

import pl.sdk.creatures.EconomyCreature;

import java.util.ArrayList;
import java.util.List;

class EconomyHero {


    private final List<EconomyCreature> creatureList;


    EconomyHero() {
        creatureList = new ArrayList<>();
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


}