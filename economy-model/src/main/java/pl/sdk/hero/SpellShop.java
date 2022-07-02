package pl.sdk.hero;

import pl.sdk.spells.AbstractEconomySpellFactory;
import pl.sdk.spells.EconomySpell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static pl.sdk.spells.SpellFactoryType.DEFAULT;

public class SpellShop extends AbstractShop {

    public static final String EXCEPTION_MESSAGE = "hero cannot consume more spells";
    public static final String SPELL_US_UNAVAILABLE = "spell is unavailable";
    private final AbstractEconomySpellFactory spellFactory;
    private List<EconomySpell> spellPopulation;

    SpellShop() {
        calculator = new SpellShopCalculator();
        spellFactory = AbstractEconomySpellFactory.getInstance(DEFAULT);
        spellPopulation = new ArrayList<>();
        createPopulation();
    }

    SpellShop(Random aRand, AbstractEconomySpellFactory aSpellFactory) {
        calculator = new SpellShopCalculator(aRand);
        spellFactory = aSpellFactory;
        spellPopulation = new ArrayList<>();
        createPopulation();
    }

    void buy(Player aActivePlayer, EconomySpell aEconomySpell) {
        aActivePlayer.subtractGold(aEconomySpell.getGoldCost());
        subtractPopulation(aEconomySpell);
        try {
            aActivePlayer.addSpell(aEconomySpell);
        } catch (Exception e) {
            aActivePlayer.addGold(aEconomySpell.getGoldCost());
            restorePopulation(aEconomySpell);
            throw new IllegalStateException(EXCEPTION_MESSAGE);
        }

    }

    private void restorePopulation(EconomySpell aEconomySpell) {
        spellPopulation.add(aEconomySpell);
    }

    private void subtractPopulation(EconomySpell aEconomySpell) {
        if (!spellPopulation.stream().map(EconomySpell::getName).collect(Collectors.toList()).contains(aEconomySpell.getName())) {
            throw new IllegalStateException(SPELL_US_UNAVAILABLE);
        }
        for (int i = 0; i < spellPopulation.size(); i++) {
            if (spellPopulation.get(i).getName().equals(aEconomySpell.getName()))
                spellPopulation.remove(i);
        }
    }

    private void createPopulation() {
        List<EconomySpell> allSpells = spellFactory.getAllSpells();
        int populationSize = calculatePopulation(allSpells.size());
        Random rand = new Random();
        for (int i = 0; i < populationSize; i++) {
            int randomIndex = rand.nextInt(allSpells.size());
            spellPopulation.add(allSpells.get(randomIndex));
            allSpells.remove(randomIndex);

        }

    }

    int calculateMaxAmount(Player aPlayer, EconomySpell aSpell) {
        return calculator.calculateMaxAmount(aPlayer.getGold(), aSpell.getGrowth(), aSpell.getGoldCost());
    }

    private int calculatePopulation(int aSize) {
        return calculator.randomize(aSize);
    }

    List<EconomySpell> getCurrentSpellPopulation() {
        return spellPopulation;
    }

    @Override
    protected void handlePopulation() {
        spellPopulation = new ArrayList<>();
        createPopulation();
    }
}
