package pl.sdk;

public enum SpellsStatistic {

    HASTE("Haste", "Increases the speed of the selected unit.", 1, SpellElement.AIR, SpellType.BUFF,
            TargetType.ALLY, 6),
    SUMMON_AIR_ELEMENTAL("Summon Air Elemental", "Allows you to summon elementals. Once cast, no other elemental " +
            "types may be summoned.", 5, SpellElement.AIR, SpellType.SUMMON, TargetType.MAP, 25),
    DISPEL("Dispel", "Protects the selected unit from all low level spells.", 1, SpellElement.WATER, SpellType.SPECIAL,
            TargetType.CREATURE, 5),
    TELEPORT("Teleport", "Teleports any friendly unit to any unoccupied spot on the battlefield.", 3, SpellElement.WATER
            , SpellType.SPECIAL, TargetType.ALLY, 15),

    FIRE_BALL("Fire Ball", "Causes the selected target to burst into flames, inflicting fire damage to the target and" +
            " any adjacent units.", 3, SpellElement.FIRE, SpellType.DAMAGE, TargetType.SPLASH_MAP, 15),

    IMPLOSION("Implosion", "Inflicts massive damage to a single creature stack", 5, SpellElement.EARTH,
            SpellType.DAMAGE, TargetType.ENEMY, 3),

    SLOW("Slow", "Reduces the speed of the selected enemy unit.", 1, SpellElement.EARTH, SpellType.DEBUFF,
            TargetType.ENEMY,
            6),

    DEATH_RIPPLE("Death Ripple", "Sends a wave of death across the battlefield which damages all non-undead units.",
            2, SpellElement.EARTH, SpellType.DAMAGE, TargetType.ENEMY, 10),

    MAGIC_ARROW("Magic Arrow", "Causes a bolt of magical energy to strike the selected unit.", 1, SpellElement.WATER,
            SpellType.DAMAGE, TargetType.ENEMY
            , 5);


    public enum SpellElement {
        AIR, FIRE, WATER, EARTH;

    }
    public enum SpellType {
        BUFF, DEBUFF, DAMAGE, SPECIAL, SUMMON, MAP_CHANGE;

    }

    public enum TargetType {
        ALLY, ALL_ALLIES, ENEMY, ALL_ENEMIES, CREATURE, MAP, SPLASH_MAP, ALL, SPECIAL;
    }
    private final String name;

    private final String description;
    private final int level;
    private final SpellElement spellElement;
    private final SpellType spellType;
    private final TargetType targetType;
    private final int manaCost;
    SpellsStatistic(String aName, String aDescription, int aLevel, SpellElement aSpellElement, SpellType aSpellType,
                    TargetType aTargetType, int aManaCost) {
        name = aName;
        description = aDescription;
        level = aLevel;
        spellElement = aSpellElement;
        spellType = aSpellType;
        targetType = aTargetType;
        manaCost = aManaCost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

     public int getLevel() {
        return level;
    }

    public SpellElement getSpellElement() {
        return spellElement;
    }

    public SpellType getSpellType() {
        return spellType;
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public int getManaCost() {
        return manaCost;
    }
}
