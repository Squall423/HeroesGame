package pl.sdk;

import com.google.common.collect.Range;

import java.beans.PropertyChangeEvent;

import java.beans.PropertyChangeListener;

public class Creature implements PropertyChangeListener {

    private CreatureStatistic stats;
    private int currentHp;
    private boolean counterAttackedInThisTurn;
    private DamageCalculator calc;


    public Creature() {
        this("Name", 2, 1, 10, 10, 2);
    }

    public Creature(String aName, int aAttack, int aArmor, int aMaxHp, int aMoveRange) {
        this(aName, aAttack, aArmor, aMaxHp, aMoveRange, aAttack);
    }


    public Creature(String aName, int aAttack, int aArmor, int aMaxHp, int aMoveRange, int aDamage) {
        this(aName, aAttack, aArmor, aMaxHp, aMoveRange, Range.closed(aDamage, aDamage), new NewDamageCalc());
    }

    public Creature(String aName, int aAttack, int aArmor, int aMaxHp, int aMoveRange, Range<Integer> aDamage,
                    DamageCalculator aCalc) {
        stats = new CreatureStatistic(aName, aAttack, aArmor, aMaxHp, aMoveRange, aDamage);
        currentHp = stats.getMaxHp();
        calc = aCalc;
    }


    void attack(Creature aDefender) {
        if (isAlive()) {
            int damageToDeal = calc.calculateDamage(this, aDefender);
            aDefender.currentHp = aDefender.currentHp - damageToDeal;

            if (!aDefender.counterAttackedInThisTurn) {
                int damageToDealInCounterAttack = calc.calculateDamage(aDefender, this);
                currentHp = currentHp - damageToDealInCounterAttack;
                aDefender.counterAttackedInThisTurn = true;
            }
        }
    }


    private boolean isAlive() {
        return currentHp > 0;
    }

    int getCurrentHp() {
        return currentHp;
    }

    public String getName() {
        return stats.getName();
    }

    boolean canCounterAttack() {
        return !counterAttackedInThisTurn;
    }

    int getMoveRange() {
        return stats.getMoveRange();
    }

    @Override
    public void propertyChange(PropertyChangeEvent aPropertyChangeEvent) {
        counterAttackedInThisTurn = false;
    }

    int getAttack() {
        return stats.getAttack();
    }

    int getArmor() {
        return stats.getArmor();
    }

    Range<Integer> getDamage() {
        return stats.getDamage();
    }
}

