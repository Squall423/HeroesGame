package pl.sdk.creatures;

import com.google.common.collect.Range;

import java.beans.PropertyChangeEvent;

class BlockCounterAttackCreatureDecorator extends Creature {

    private final Creature decorated;

    public BlockCounterAttackCreatureDecorator(Creature aDecorated) {
        decorated = aDecorated;
    }

    @Override
    public void attack(Creature aDefender) {
        if (decorated.isAlive()) {
            int damageToDeal = decorated.calculateDamage(this, aDefender);
            aDefender.applyDamage(damageToDeal);

            decorated.performAfterAttack(damageToDeal);

        }
    }


    @Override
    void performAfterAttack(int aDamageToDeal) {
        decorated.performAfterAttack(aDamageToDeal);
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
    public int getCurrentHp() {
        return decorated.getCurrentHp();
    }

    @Override
    public String getName() {
        return decorated.getName();
    }

    @Override
    public boolean canCounterAttack() {
        return decorated.canCounterAttack();
    }

    @Override
    public int getMoveRange() {
        return decorated.getMoveRange();
    }

    @Override
    public void propertyChange(PropertyChangeEvent aPropertyChangeEvent) {
        decorated.propertyChange(aPropertyChangeEvent);
    }

    @Override
    public int getAttack() {
        return decorated.getAttack();
    }

    @Override
    public int getArmor() {
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
    public double getAttackRange() {
        return decorated.getAttackRange();
    }


}
