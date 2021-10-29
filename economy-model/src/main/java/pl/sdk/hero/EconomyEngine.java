package pl.sdk.hero;

import pl.sdk.creatures.EconomyCreature;

public class EconomyEngine {
    private final EconomyHero hero1;
    private final EconomyHero hero2;
    private final CreatureShop creatureShop = new CreatureShop();

    public EconomyEngine(EconomyHero aHero1, EconomyHero aHero2) {
        hero1 = aHero1;
        hero2 = aHero2;
    }

    void buy(EconomyCreature aEconomyCreature) {
        creatureShop.buy(hero1, aEconomyCreature);
    }
}
