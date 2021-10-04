package pl.sdk.creatures;

import com.google.common.collect.Range;

import java.beans.PropertyChangeEvent;

 class RegenerationOnEndOfTurnCreatureDecorator extends Creature{

    private Creature decorated;

    RegenerationOnEndOfTurnCreatureDecorator(Creature aDecorated) {
        decorated = aDecorated;
    }

    @Override
    public void attack(Creature aDefender) {
        decorated.attack(aDefender);
    }

    @Override
    protected int calculateDamage(Creature aAttacker, Creature aDefender) {
        return decorated.calculateDamage(aAttacker, aDefender);
    }

     @Override
     void performAfterAttack(int aDamageToDeal) {
         decorated.performAfterAttack(aDamageToDeal);
     }

     @Override
    protected void counterAttack(Creature aDefender) {
        decorated.counterAttack(aDefender);
    }

    @Override
    public void applyDamage(int aDamageToApply) {
        decorated.applyDamage(aDamageToApply);
    }

     @Override
     public boolean isAlive() {
         return decorated.isAlive();
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
        decorated.setCurrentHpToMaximum();
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
    public Range<Integer> getDamage() {
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

     @Override
     void setCurrentHpToMaximum() {
         decorated.setCurrentHpToMaximum();
     }

     @Override
     public boolean[][] getSplashRange() {
         return decorated.getSplashRange();
     }


 }
