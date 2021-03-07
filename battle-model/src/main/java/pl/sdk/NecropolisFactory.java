package pl.sdk;

import com.google.common.collect.Range;

import java.util.ArrayList;
import java.util.List;

public class NecropolisFactory {

    public static final String SKELETON = "Skeleton";
    public static final String WALKING_DEAD = "Walking Dead";
    public static final String WIGHT = "Wight";
    public static final String VAMPIRE = "Vampire";
    public static final String LICH = "Lich";
    public static final String BLACK_KNIGHT = "Black Knight";
    public static final String BONE_DRAGON = "Bone Dragon";
    public static final String SKELETON_WARRIOR = "Skeleton Warrior";
    public static final String ZOMBIE = "Zombie";
    public static final String WRAITH = "Wraith";
    public static final String VAMPIRE_LORD = "Vampire Lord";
    public static final String POWER_LICH = "Power Lich";
    public static final String DREAD_KNIGHT = "Dread Knight";
    public static final String GHOST_DRAGON = "Ghost Dragon";
    public static final String EXCEPTION_MESSAGE = "We support tiers from 1 to 7";

    public Creature create(boolean aIsUpgraded, int aTier) {
        if (aIsUpgraded) {
            switch (aTier) {
                case 1:
                    return new Creature.Builder()
                            .name(SKELETON)
                            .maxHp(6)
                            .attack(5)
                            .armor(4)
                            .damage(Range.closed(1, 3))
                            .moveRange(4)
                            .amount(70)
                            .build();
                case 2:
                    return new Creature.Builder()
                            .name(WALKING_DEAD)
                            .maxHp(15)
                            .attack(5)
                            .armor(5)
                            .damage(Range.closed(2, 3))
                            .moveRange(3)
                            .amount(60)
                            .build();
                case 3:
                    return new Creature.Builder()
                            .name(WIGHT)
                            .maxHp(18)
                            .attack(7)
                            .armor(7)
                            .damage(Range.closed(3, 5))
                            .moveRange(5)
                            .amount(50)
                            .build();
                case 4:
                    return new BlockCounterAttackCreatureDecorator.Builder()
                            .name(VAMPIRE)
                            .maxHp(30)
                            .attack(10)
                            .armor(9)
                            .damage(Range.closed(5, 8))
                            .moveRange(6)
                            .amount(40)
                            .build();
                case 5:
                    Creature lich = new Creature.Builder()
                            .name(LICH)
                            .maxHp(30)
                            .attack(13)
                            .armor(10)
                            .damage(Range.closed(11, 13))
                            .moveRange(6)
                            .amount(30)
                            .build();
                    return new BlockCounterAttackCreatureDecorator(new ShootingCreatureDecorator(new SplashDamageCreatureDecorator(lich, getSplashForLich())));

                case 6:
                    return new Creature.Builder()
                            .name(BLACK_KNIGHT)
                            .maxHp(120)
                            .attack(16)
                            .armor(16)
                            .damage(Range.closed(15, 30))
                            .damageCalculator(new CalculateDamageIncreaseWithRandomChanceStrategy(0.2, 2.0))
                            .moveRange(7)
                            .amount(20)
                            .build();
                case 7:
                    return new Creature.Builder()
                            .name(BONE_DRAGON)
                            .maxHp(150)
                            .attack(17)
                            .armor(15)
                            .damage(Range.closed(25, 50))
                            .moveRange(9)
                            .amount(10)
                            .build();
                default:
                    throw new IllegalArgumentException(EXCEPTION_MESSAGE);
            }
        } else {
            switch (aTier) {
                case 1:
                    return new Creature.Builder()
                            .name(SKELETON_WARRIOR)
                            .maxHp(6)
                            .attack(6)
                            .armor(6)
                            .damage(Range.closed(1, 3))
                            .moveRange(5)
                            .amount(75)
                            .build();
                case 2:
                    return new Creature.Builder()
                            .name(ZOMBIE)
                            .maxHp(20)
                            .attack(5)
                            .armor(5)
                            .damage(Range.closed(2, 3))
                            .moveRange(4)
                            .amount(65)
                            .build();
                case 3:
                    return new RegenerationOnEndOfTurnCreatureDecorator(new Creature.Builder()
                            .name(WRAITH)
                            .maxHp(20)
                            .attack(5)
                            .armor(5)
                            .damage(Range.closed(2, 3))
                            .moveRange(4)
                            .amount(65)
                            .build());
                case 4:
                    return new Creature.Builder()
                            .name(VAMPIRE_LORD)
                            .maxHp(40)
                            .attack(10)
                            .armor(10)
                            .damage(Range.closed(5, 8))
                            .moveRange(9)
                            .amount(45)
                            .build();
                case 5:
                    Creature c = new Creature.Builder()
                            .name(POWER_LICH)
                            .maxHp(40)
                            .attack(13)
                            .armor(10)
                            .damage(Range.closed(11, 15))
                            .moveRange(7)
                            .amount(35)
                            .build();
                    boolean[][] splashDamageTable = getSplashForLich();
                    return new SplashDamageCreatureDecorator(new BlockCounterAttackCreatureDecorator(new ShootingCreatureDecorator(c)), splashDamageTable);

                case 6:
                    return new Creature.Builder()
                            .name(DREAD_KNIGHT)
                            .maxHp(120)
                            .attack(18)
                            .armor(18)
                            .damage(Range.closed(15, 30))
                            .damageCalculator(new CalculateDamageIncreaseWithRandomChanceStrategy(0.2, 2.0))
                            .moveRange(9)
                            .amount(25)
                            .build();
                case 7:
                    return new Creature.Builder()
                            .name(GHOST_DRAGON)
                            .maxHp(200)
                            .attack(19)
                            .armor(17)
                            .damage(Range.closed(25, 50))
                            .moveRange(14)
                            .amount(15)
                            .build();

                default:
                    throw new IllegalArgumentException(EXCEPTION_MESSAGE);
            }
        }
    }

    private boolean[][] getSplashForLich() {
        boolean[][] splashDamageTable = new boolean[3][3];
        splashDamageTable[0][1] = true;
        splashDamageTable[2][1] = true;
        splashDamageTable[1][1] = true;
        splashDamageTable[1][2] = true;
        splashDamageTable[1][0] = true;
        return splashDamageTable;
    }
}
