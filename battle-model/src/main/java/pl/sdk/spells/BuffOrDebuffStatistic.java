package pl.sdk.spells;

import com.google.common.collect.Range;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class BuffOrDebuffStatistic {
    private int moveRange;
    private int attack;
    private int armor;
    private int maxHp;
    private Range<Integer> damage;

    private double moveRangePercentage;
    private double attackPercentage;
    private double armorPercentage;
    private double maxHpPercentage;
    private double damagePercentage;

    public BuffOrDebuffStatistic(int aMoveRange) {
        moveRange = aMoveRange;
    }

    public int getMoveRange() {
        return moveRange;
    }
}
