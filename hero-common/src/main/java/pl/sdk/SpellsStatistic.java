package pl.sdk;

public enum SpellsStatistic {

    HASTE("Haste", "Increases the speed of the selected unit.", 1, SpellElement.AIR, SpellType.BUFF,
            SplashType.TARGET_ALLY, 6),
    SUMMON_AIR_ELEMENTAL("Summon Air Elemental", "Allows you to summon elementals. Once cast, no other elemental " +
            "types may be summoned.", 5, SpellElement.AIR, SpellType.SUMMON, SplashType.TARGET_MAP, 25),
    DISPEL("Dispel", "Protects the selected unit from all low level spells.", 1, SpellElement.WATER, SpellType.SPECIAL,
            SplashType.TARGET_CREATURE, 5),
    TELEPORT("Teleport", "Teleports any friendly unit to any unoccupied spot on the battlefield.", 3, SpellElement.WATER
            , SpellType.SPECIAL, SplashType.TARGET_ALLY, 15),

    FIRE_BALL("Fire Ball", "Causes the selected target to burst into flames, inflicting fire damage to the target and" +
            " any adjacent units.", 3, SpellElement.FIRE, SpellType.DAMAGE, SplashType.TARGET_SPLASH_MAP, 15),

    IMPLOSION("Implosion", "Inflicts massive damage to a single creature stack", 5, SpellElement.EARTH,
            SpellType.DAMAGE, SplashType.TARGET_ENEMY, 3),

    SLOW("Slow", "Reduces the speed of the selected enemy unit.", 1, SpellElement.EARTH, SpellType.DEBUFF,
            SplashType.TARGET_ENEMY,
            6),

    DEATH_RIPPLE("Death Ripple", "Sends a wave of death across the battlefield which damages all non-undead units.",
            2, SpellElement.EARTH, SpellType.DAMAGE, SplashType.TARGET_ENEMY, 10),

    MAGIC_ARROW("Magic Arrow", "Causes a bolt of magical energy to strike the selected unit.", 1, SpellElement.WATER,
            SpellType.DAMAGE, SplashType.TARGET_ENEMY
            , 5);


    private enum SpellElement {
        AIR, FIRE, WATER, EARTH,;
    }

    private enum SpellType {
        BUFF, DEBUFF, DAMAGE, SPECIAL, SUMMON, MAP_CHANGE;
    }

    private enum SplashType {
        TARGET_ALLY, TARGET_ENEMY, TARGET_CREATURE, TARGET_MAP, TARGET_SPLASH_MAP, ALL, SPECIAL_DAMAGE,
    }

    private final String name;
    private final String description;
    private final int level;
    private final SpellElement element;
    private final SpellType spellType;
    private final SplashType splashType;
    private final int manaCost;

    SpellsStatistic(String aName, String aDescription, int aLevel, SpellElement aElement, SpellType aSpellType,
                    SplashType aSplashType, int aManaCost) {
        name = aName;
        description = aDescription;
        level = aLevel;
        element = aElement;
        spellType = aSpellType;
        splashType = aSplashType;
        manaCost = aManaCost;
    }
}
