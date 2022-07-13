package pl.sdk.hero;

import java.util.Random;

public class SpellShopCalculator extends AbstractShopCalculator {

    SpellShopCalculator() {
        super();
    }

    SpellShopCalculator(Random aRand) {
        super(aRand);
    }

    @Override
    int calculateMaxAmount(int aHeroGold, Integer aAmount, int aGoldCost) {
        return Math.min(aHeroGold / aGoldCost, aAmount) >= 1 ? 1 : 0;
    }
}
