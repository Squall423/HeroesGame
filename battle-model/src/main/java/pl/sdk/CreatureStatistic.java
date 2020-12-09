package pl.sdk;

 class CreatureStatistic {
    final private String name;
    final private int attack;
    final private int armor;
    final private int maxHp;
    final private int moveRange;

    CreatureStatistic(String aName, int aAttack, int aArmor, int aMaxHp, int aMoveRange) {
        name = aName;
        attack = aAttack;
        armor = aArmor;
        maxHp = aMaxHp;
        moveRange = aMoveRange;
    }

    String getName() {
        return name;
    }

    int getAttack() {
        return attack;
    }

    int getArmor() {
        return armor;
    }

    int getMaxHp() {
        return maxHp;
    }

    int getMoveRange() {
        return moveRange;
    }
}
