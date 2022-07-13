package pl.sdk.hero;

import java.util.Random;

public class CreatureShopCalculator extends AbstractShopCalculator  {

    CreatureShopCalculator() {
        super();
    }

    CreatureShopCalculator(Random aRand) {
        super(aRand);
    }

    int calculateMaxAmount(int aHeroGold, Integer aAmount, int aGoldCost) {
        return Math.min(aHeroGold / aGoldCost, aAmount);
    }

}
