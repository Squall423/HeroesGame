package pl.sdk.hero;

import pl.sdk.Fraction;
import pl.sdk.creatures.EconomyCreature;
import pl.sdk.spells.EconomySpell;

import java.util.List;
import java.util.stream.Collectors;

public class Player {


    EconomyHero hero;
    CreatureShop creatureShop;
    SpellShop spellShop;
    private List<AbstractShop> shops;
    private int gold;
    Fraction fraction;
    private String ALREADY_BOUGHT = "already bought ";

    public Player(Fraction aFraction, int aGold) {
        hero = new EconomyHero(new HeroStats(5, 5, 15, 3));
        creatureShop = new CreatureShop(aFraction);
        spellShop = new SpellShop();
        shops = List.of(creatureShop, spellShop);
        gold = aGold;
        fraction = aFraction;
    }

    Player(Fraction aFraction, int aGold, EconomyHero aEconomyHero) {
        hero = aEconomyHero;
        creatureShop = new CreatureShop(aFraction);
        spellShop = new SpellShop();
        shops = List.of(creatureShop, spellShop);
        gold = aGold;
        fraction = aFraction;
    }

    Player(EconomyHero aHero, CreatureShop aCreatureShop, int aGold) {
        hero = aHero;
        creatureShop = aCreatureShop;
        shops = List.of(creatureShop);
        gold = aGold;
    }

    Player(EconomyHero aHero, SpellShop aSpellShop, int aGold) {
        hero = aHero;
        spellShop = aSpellShop;
        shops = List.of(spellShop);
        gold = aGold;
    }


    EconomyHero getHero() {
        return hero;
    }

    CreatureShop getCreatureShop() {
        return creatureShop;
    }

    void subtractGold(int aAmount) {
        if (aAmount > gold) {
            throw new IllegalStateException("Player has not enough money");
        }
        gold -= aAmount;
    }

    void addGold(int aAmount) {
        gold += aAmount;
    }

    void addCreature(EconomyCreature aEconomyCreature) {
        hero.addCreature(aEconomyCreature);
    }

    public List<EconomyCreature> getCreatures() {
        return hero.getCreatures();
    }

    public int getGold() {
        return gold;
    }

    public void buyCreature(Player aActivePlayer, EconomyCreature aEconomyCreature) {
        creatureShop.buy(aActivePlayer, aEconomyCreature);
    }

    public void buySpell(Player aActivePlayer, EconomySpell aEconomySpell) {
        if (!hasSpell(aEconomySpell.getName())) {
            spellShop.buy(aActivePlayer, aEconomySpell);
        } else {
            throw new IllegalStateException(ALREADY_BOUGHT);
        }
    }


    public int calculateMaxAmount(EconomyCreature aCreature) {
        return creatureShop.calculateMaxAmount(this, aCreature);
    }

    public int getCurrentPopulation(int aTier) {
        return creatureShop.getCurrentPopulation(aTier);
    }

    public List<EconomySpell> getCurrentSpellPopulation() {
        return spellShop.getCurrentSpellPopulation();
    }

    void addSpell(EconomySpell aEconomySpell) {
        hero.addSpell(aEconomySpell);
    }

    public List<EconomySpell> getSpells() {
        return hero.getSpells();
    }

    public int getPower() {
        return hero.getPower();
    }

    public int getWisdom() {
        return hero.getWisdom();
    }

    public boolean hasSpell(String aName) {
        return getSpells().stream().map(EconomySpell::getName).collect(Collectors.toList()).contains(aName);
    }

    List<AbstractShop> getShops() {
        return shops;
    }

    public Fraction getFraction() {
        return fraction;
    }

    int calculateSpellMaxAmount(EconomySpell aEconomySpell) {
        return spellShop.calculateMaxAmount(this, aEconomySpell);
    }
}


