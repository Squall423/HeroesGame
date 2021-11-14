package pl.sdk.creatures;

import com.google.common.collect.Range;

public class CreatureStatisticForTesting implements CreatureStatisticIf {

    private final String name;
    private final int attack;
    private final int armor;
    private final int maxHp;
    private final int moveRange;
    private final Range<Integer> damage;

    CreatureStatisticForTesting(String aName, int aAttack, int aArmor, int aMaxHp, int aMoveRange,
                                Range<Integer> aDamage) {
        name = aName;
        attack = aAttack;
        armor = aArmor;
        maxHp = aMaxHp;
        moveRange = aMoveRange;
        damage = aDamage;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getTranslatedName() {
        return getName();
    }

    public int getAttack() {
        return attack;
    }

    public int getArmor() {
        return armor;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getMoveRange() {
        return moveRange;
    }

    public Range<Integer> getDamage() {
        return damage;
    }

    @Override
    public int getTier() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Creature for testing";
    }
}


