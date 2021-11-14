package pl.sdk.hero;

import pl.sdk.creatures.EconomyCreature;

public class CreatureShop {
    public void buy(EconomyHero aHero, EconomyCreature aEconomyCreature) {
        aHero.substractGold(aEconomyCreature.getGoldCost() * aEconomyCreature.getAmount());
        try {
            aHero.addCreature(aEconomyCreature);
        } catch (Exception e) {
           aHero.addGold(aEconomyCreature.getGoldCost() * aEconomyCreature.getAmount());
           throw new IllegalStateException("Hero cannot consume more creature");
        }

    }
}
