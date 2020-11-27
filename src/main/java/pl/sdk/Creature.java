package pl.sdk;

class Creature {

    private CreatureStatistic stats;
    private int currentHp;
    boolean counterAttackedInThisTurn;

    Creature() {
        stats = new CreatureStatistic("DefName", 1, 100, 1, 1);
    }

    Creature(String aName, int aAttack, int aArmor, int aMaxHp, int aMoveRange) {
        stats = new CreatureStatistic(aName, aAttack, aArmor, aMaxHp, aMoveRange);
        currentHp = stats.getMaxHp();
    }

    void attack(Creature aDefender) {
        if (isAlive()) {
            int damageToDeal = calculateDamage(aDefender);
            aDefender.currentHp = aDefender.currentHp - damageToDeal;
            counterAttack(aDefender);
        }
    }

    private void counterAttack(Creature aDefender) {
        if (!aDefender.counterAttackedInThisTurn) {
            int damageToDealInCounterAttack = aDefender.calculateDamage(this);
            currentHp = currentHp - damageToDealInCounterAttack;
            aDefender.counterAttackedInThisTurn = true;

        }
    }


    private int calculateDamage(Creature aDefender) {
        int damageToDeal = this.stats.getAttack() - aDefender.stats.getArmor();
        if (damageToDeal < 0) {
            damageToDeal = 0;
        }
        return damageToDeal;
    }

    private boolean isAlive() {
        return currentHp > 0;
    }

    int getCurrentHp() {
        return currentHp;
    }


    void reset() {
        counterAttackedInThisTurn = false;

    }
}

