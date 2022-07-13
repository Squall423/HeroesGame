package pl.sdk.spells;

import pl.sdk.SpellsStatistic;

import java.util.ArrayList;
import java.util.List;

import static pl.sdk.SpellsStatistic.*;

public class EconomySpellFactory extends AbstractEconomySpellFactory {

    private static final String EXCEPTION_MESSAGE = "Invalid spell name";

    @Override
    public EconomySpell create(String aName) {
        if (aName.equals(HASTE.getName())) {
            return new EconomySpell(SpellsStatistic.HASTE);
        } else if (aName.equals(MAGIC_ARROW.getName())) {
            return new EconomySpell(MAGIC_ARROW);
        } else if (aName.equals(SUMMON_AIR_ELEMENTAL.getName())) {
            return new EconomySpell(SUMMON_AIR_ELEMENTAL);
        } else if (aName.equals(IMPLOSION.getName())) {
            return new EconomySpell(IMPLOSION);
        } else if (aName.equals(DEATH_RIPPLE.getName())) {
            return new EconomySpell(DEATH_RIPPLE);
        } else if (aName.equals(DISPEL.getName())) {
            return new EconomySpell(DISPEL);
        } else if (aName.equals(TELEPORT.getName())) {
            return new EconomySpell(TELEPORT);
        } else if (aName.equals(FIRE_BALL.getName())) {
            return new EconomySpell(FIRE_BALL);
        } else if (aName.equals(SLOW.getName())) {
            return new EconomySpell(SLOW);
        } else {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
    }

    @Override
    public List<EconomySpell> getAllSpells() {
        List<EconomySpell> spellList = new ArrayList<>();
        List.of(HASTE.getName(),
                MAGIC_ARROW.getName(),
                DEATH_RIPPLE.getName(),
                IMPLOSION.getName(),
                SLOW.getName(),
                FIRE_BALL.getName(),
                SUMMON_AIR_ELEMENTAL.getName(),
                DISPEL.getName(),
                TELEPORT.getName())
                .forEach(spellName -> spellList.add(create(spellName)));
        return spellList;
    }
}
