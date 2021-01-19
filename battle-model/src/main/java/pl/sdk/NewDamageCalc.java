package pl.sdk;

import java.util.Random;

public class NewDamageCalc extends DamageCalculator {

    int calculateDamage(Creature aAttacker, Creature aDefender) {

        Random rand = new Random();
        int randValue = rand.nextInt(aAttacker.getDamage().upperEndpoint() - aAttacker.getDamage().lowerEndpoint() + 1) + aAttacker.getDamage().lowerEndpoint() ;

        double ret;
        if (aAttacker.getAttack() >= aDefender.getArmor()) {
            int attackPoints = aAttacker.getAttack() - aDefender.getArmor();
            if (attackPoints > 60) {
                attackPoints = 60;
            }
            ret = randValue * (1 + (attackPoints) * 0.05);
        } else {
            int defencePoints = aDefender.getArmor() - aAttacker.getAttack();
            if (defencePoints > 12) {
                defencePoints = 12;
            }
            ret = randValue * (1 - defencePoints * 0.025);
        }
        return (int) ret;
    }

}
