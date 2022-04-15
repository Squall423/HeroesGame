package pl.sdk.creatures;

public class DefaultMagicDamageApplier {
    private final int percentageSpellResistance;

     DefaultMagicDamageApplier() {
        percentageSpellResistance = 0;
    }

     DefaultMagicDamageApplier(int aPercentageSpellResistance) {
        percentageSpellResistance = aPercentageSpellResistance;
    }

    int reduceDamage(int aDamage) {
        return (int) ((aDamage * (100 - percentageSpellResistance )) /100.0);

    }
}
