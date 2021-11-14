package pl.sdk.creatures;

import com.google.common.collect.Range;

enum CreatureStatistic implements CreatureStatisticIf {

    //    TEST CREATURES
    TEST("name", 2, 1, 10, 1, Range.closed(2, 2), 0, "Creature for tests", false),

// NECROPOLIS FRACTION

    SKELETON("Skeleton", 5, 3, 6, 4, Range.closed(1, 3), 1, "Creature for tests", false),
    WALKING_DEAD("Walking Dead", 5, 5, 15, 3, Range.closed(2, 3), 2, "Creature for tests", false),
    WIGHT("Wight", 7, 7, 18, 5, Range.closed(3, 5), 3, "Creature for tests", false),
    VAMPIRE("Vampire", 10, 9, 30, 6, Range.closed(5, 8), 4, "Creature for tests", false),
    LICH("Lich", 13, 10, 30, 6, Range.closed(11, 13), 5, "Creature for tests", false),
    BLACK_KNIGHT("Black Knight", 16, 16, 120, 7, Range.closed(15, 30), 6, "Creature for tests", false),
    BONE_DRAGON("Bone Dragon", 17, 15, 150, 9, Range.closed(25, 50), 7, "Creature for tests", false),
    SKELETON_WARRIOR("Skeleton Warrior", 6, 6, 6, 5, Range.closed(1, 3), 1, "Creature for tests", true),
    ZOMBIE("Zombie", 5, 5, 20, 4, Range.closed(2, 3), 2, "Creature for tests", true),
    WRAITH("Wraith", 5, 5, 20, 4, Range.closed(2, 3), 3, "Creature for tests", true),
    VAMPIRE_LORD("Vampire Lord", 10, 10, 40, 9, Range.closed(5, 8), 4, "Creature for tests", true),
    POWER_LICH("Power Lich", 13, 10, 40, 7, Range.closed(11, 15), 5, "Creature for tests", true),
    DREAD_KNIGHT("Dread Knight", 18, 18, 120, 9, Range.closed(15, 30), 6, "Creature for tests", true),
    GHOST_DRAGON("Ghost Dragon", 19, 17, 200, 14, Range.closed(25, 50), 7, "Creature for tests", true);

    private final String name;
    private final int attack;
    private final int armor;
    private final int maxHp;
    private final int moveRange;
    private final Range<Integer> damage;
    private final int tier;
    private final String description;
    private final boolean isUpgrade;

    CreatureStatistic(String aName, int aAttack, int aArmor, int aMaxHp, int aMoveRange, Range<Integer> aDamage,
                      int aTier, String aDescription, boolean aIsUpgrade) {
        name = aName;
        attack = aAttack;
        armor = aArmor;
        maxHp = aMaxHp;
        moveRange = aMoveRange;
        damage = aDamage;
        tier = aTier;
        description = aDescription;
        isUpgrade = aIsUpgrade;
    }

    public String getTranslatedName() {
        return name;
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

    public int getTier() {
        return tier;
    }

    public String getDescription() {
        return description;
    }

}
