package pl.sdk;

import com.google.common.collect.Range;

import java.beans.PropertyChangeEvent;

import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Creature implements PropertyChangeListener {

    private CreatureStatistic stats;
    private int currentHp;
    private boolean counterAttackedInThisTurn;
    private CalculateDamageStrategy calculateDamageStrategy;
    private int amount;

    //Mockito constructor
    public Creature() {
        new CreatureStatistic("Name", 1, 1, 1, 1, Range.closed(2, 2));
    }

    protected Creature(CreatureStatistic aStats) {
        stats = aStats;
        currentHp = stats.getMaxHp();
    }

    //do zaorania
    public Creature(String aName, int aAttack, int aArmor, int aMaxHp, int aMoveRange) {
        this(aName, aAttack, aArmor, aMaxHp, aMoveRange, aAttack);
    }

    public Creature(String aName, int aAttack, int aArmor, int aMaxHp, int aMoveRange, int aDamage) {
        this(aName, aAttack, aArmor, aMaxHp, aMoveRange, Range.closed(aDamage, aDamage), new DefaultCalculateStrategy());
    }

    public Creature(String aName, int aAttack, int aArmor, int aMaxHp, int aMoveRange, Range<Integer> aDamage,
                    CalculateDamageStrategy aCalculateDamageStrategy) {
        stats = new CreatureStatistic(aName, aAttack, aArmor, aMaxHp, aMoveRange, aDamage);
        currentHp = stats.getMaxHp();
        calculateDamageStrategy = aCalculateDamageStrategy;
    }
////////////////////////////////////////////////

    void attack(Creature aDefender) {
        if (isAlive()) {
            int damageToDeal = calculateDamageStrategy.calculateDamage(this, aDefender);
            aDefender.applyDamage(damageToDeal);

            performAfterAttack(damageToDeal);


            counterAttack(aDefender);
        }
    }

     void performAfterAttack(int aDamageToDeal) {
    }

     void counterAttack(Creature aDefender) {
        if (!aDefender.counterAttackedInThisTurn) {
            int damageToDealInCounterAttack = calculateDamageStrategy.calculateDamage(aDefender, this);
            applyDamage(damageToDealInCounterAttack);
            aDefender.counterAttackedInThisTurn = true;
        }
    }

    void applyDamage(int aDamageToApply){
        int fullCurrentHp = (stats.getMaxHp() * (amount - 1)) + currentHp - aDamageToApply;
        if (fullCurrentHp <= 0) {
            amount = 0;
            currentHp = 0;
        }
        else
        {
            if(fullCurrentHp % stats.getMaxHp()==0)
            {
                currentHp=stats.getMaxHp();
                amount=fullCurrentHp/stats.getMaxHp();
            }
            else
            {
                currentHp = fullCurrentHp % stats.getMaxHp();
                if (aDamageToApply >= 0){
                    amount = (fullCurrentHp/stats.getMaxHp()) + 1;
                }else{
                    amount = (fullCurrentHp/stats.getMaxHp());
                }
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

    public int getAmount(){
        return amount;
    }

    public String currentHealth() {
        StringBuilder sb = new StringBuilder();
        sb.append(getCurrentHp());
        sb.append("/");
        sb.append(stats.getMaxHp());
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(stats.getName());
        sb.append(System.lineSeparator());
        sb.append(getCurrentHp());
        sb.append("/");
        sb.append(stats.getMaxHp());
        return sb.toString();
    }

    double getAttackRange() {
        return 1.0;
    }

    public static class Builder{
        private String name;
        private Integer attack;
        private Integer armor;
        private Integer maxHp;
        private Integer moveRange;
        private Range<Integer> damage;
        private CalculateDamageStrategy damageCalculator;
        private Integer amount;

        public Builder name(String aName) {
            this.name = aName;
            return this;
        }

        public Builder attack(int attack) {
            this.attack = attack;
            return this;
        }

        public Builder armor(int armor) {
            this.armor = armor;
            return this;
        }

        public Builder maxHp(int maxHp) {
            this.maxHp = maxHp;
            return this;
        }

        public Builder moveRange(int moveRange) {
            this.moveRange = moveRange;
            return this;
        }

        public Builder damage(Range<Integer> damage) {
            this.damage = damage;
            return this;
        }

        public Builder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder damageCalculator(CalculateDamageStrategy aCalculateDamageStrategy) {
            this.damageCalculator = aCalculateDamageStrategy;
            return this;
        }

        public Creature build() {
            Set<String> emptyFields = new HashSet<>();
            if (name == null) {
                emptyFields.add("name");

            }
            if (attack == null) {
                emptyFields.add("attack");
            }
            if (armor == null) {
                emptyFields.add("armor");
            }
            if (maxHp == null) {
                emptyFields.add("maxHp");
            }
            if (moveRange == null) {
                emptyFields.add("moveRange");
            }
            if (damage == null) {
                emptyFields.add("damage");
            }
            if (!emptyFields.isEmpty()) {
                throw new IllegalStateException("These fields: " + Arrays.toString(emptyFields.toArray()) + "cannot " +
                        "be empty");
            }

            CreatureStatistic stats = new CreatureStatistic(name, attack, armor, maxHp, moveRange, damage);
            Creature ret = createInstance(stats);
            if(amount == null){
                ret.amount = 1;
            }else{
                ret.amount = amount;
            }
            if (damageCalculator != null) {
                ret.calculateDamageStrategy = damageCalculator;
            } else {
                ret.calculateDamageStrategy = new DefaultCalculateStrategy();
            }
            return ret;
        }

          Creature createInstance(CreatureStatistic aStats) {
            return new Creature(aStats);
        }
    }
}



