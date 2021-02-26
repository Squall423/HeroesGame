package pl.sdk;

import java.util.Random;

public abstract class AbstractCalculateDamageStrategy implements CalculateDamageStrategy {

    private final Random rand;

    AbstractCalculateDamageStrategy(Random aRand) {
        rand = aRand;
    }

    protected Random getRand() {
        return rand;
    }

    public int calculateDamage(Creature aAttacker, Creature aDefender) {

        int randValue =
                rand.nextInt(aAttacker.getDamage().upperEndpoint() - aAttacker.getDamage().lowerEndpoint() + 1) + aAttacker.getDamage().lowerEndpoint();

        double oneCreatureDamageToDeal;
        if (aAttacker.getAttack() >= aDefender.getArmor()) {
            int attackPoints = aAttacker.getAttack() - aDefender.getArmor();
            if (attackPoints > 60) {
                attackPoints = 60;
            }
            oneCreatureDamageToDeal = randValue * (1 + (attackPoints) * 0.05);
        } else {
            int defencePoints = aDefender.getArmor() - aAttacker.getAttack();
            if (defencePoints > 12) {
                defencePoints = 12;
            }
            oneCreatureDamageToDeal = randValue * (1 - defencePoints * 0.025);
        }
        if (oneCreatureDamageToDeal < 0) {
            oneCreatureDamageToDeal = 0;
        }
        double wholeStackDamageToDeal = aAttacker.getAmount() * oneCreatureDamageToDeal;
        double wholeStackDamageToDealAfterChange = changeDamageAfter(wholeStackDamageToDeal, aAttacker);
        return (int) wholeStackDamageToDealAfterChange;
    }

    abstract double changeDamageAfter(double aWholeStackDamageToDeal, Creature aAttacker);
}
