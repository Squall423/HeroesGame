package pl.sdk;

import com.google.common.collect.Range;

import java.beans.PropertyChangeEvent;

public class HealAfterAttackCreatureDecorator extends Creature {

    private double selfHealingPercentage;
    private Creature decorated;

    HealAfterAttackCreatureDecorator(Creature aDecorated, double aSelfHealingPercentage) {
        decorated = aDecorated;
        selfHealingPercentage = aSelfHealingPercentage;
    }

    @Override
    void attack(Creature aDefender) {
        if (decorated.isAlive()){
            int damageToDeal = decorated.calculateDamage(this, aDefender);
            aDefender.applyDamage(damageToDeal);
            healAfterAttack(damageToDeal);
            decorated.counterAttack(aDefender);
        }
    }

    private void healAfterAttack(int aDamageToDeal) {
        applyDamage((int)(-aDamageToDeal * selfHealingPercentage));
    }

    @Override
    protected int calculateDamage(Creature aAttacker, Creature aDefender) {
        return decorated.calculateDamage(aAttacker, aDefender);
    }

    @Override
    protected void setCurrentHpToMaximum() {

    }

    @Override
    protected void counterAttack(Creature aDefender) {
        decorated.counterAttack(aDefender);
    }

    @Override
    void applyDamage(int aDamageToApply) {
        decorated.applyDamage(aDamageToApply);
    }

    @Override
    int getCurrentHp() {
        return decorated.getCurrentHp();
    }

    @Override
    public String getName() {
        return decorated.getName();
    }

    @Override
    boolean canCounterAttack() {
        return decorated.canCounterAttack();
    }

    @Override
    int getMoveRange() {
        return decorated.getMoveRange();
    }

    @Override
    public void propertyChange(PropertyChangeEvent aPropertyChangeEvent) {
        decorated.propertyChange(aPropertyChangeEvent);
    }

    @Override
    int getAttack() {
        return decorated.getAttack();
    }

    @Override
    int getArmor() {
        return decorated.getArmor();
    }

    @Override
    Range<Integer> getDamage() {
        return decorated.getDamage();
    }

    @Override
    public int getAmount() {
        return decorated.getAmount();
    }

    @Override
    public String currentHealth() {
        return decorated.currentHealth();
    }

    @Override
    public String toString() {
        return decorated.toString();
    }

    @Override
    double getAttackRange() {
        return decorated.getAttackRange();
    }


}
