package pl.sdk.creatures;

import com.google.common.collect.Range;

public interface CreatureStatisticIf {

    String getTranslatedName();

    int getAttack();

    int getArmor();

    int getMaxHp();

    int getMoveRange();

    Range<Integer> getDamage();

    int getTier();

    String getDescription();
}
