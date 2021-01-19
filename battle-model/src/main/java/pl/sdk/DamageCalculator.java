package pl.sdk;

public class DamageCalculator {

    int calculateDamage(Creature aAttacker, Creature aDefender) {
        int damageToDeal = aAttacker.getAttack() - aDefender.getArmor();
        if (damageToDeal < 0) {
            damageToDeal = 0;
        }
        return damageToDeal;
    }
}
