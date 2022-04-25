package pl.sdk.converter.spells;

import pl.sdk.converter.SpellMasteries;
import pl.sdk.spells.AbstractSpell;
import pl.sdk.spells.EconomySpell;

public abstract class SpellFactory {

    private static String NOT_IMPLEMENTED = "not implemented YET: ";
    private static String DONT_RECOGNIZE_TYPE = "Cannot recognize type: ";

//TODO aName for refactor
    public static AbstractSpell create(String aName, EconomySpell aEs, int aHeroPower, SpellMasteries aMasteries) {
        switch (aEs.getSpellType()) {
            case DAMAGE:
                return new DamageSpellFactory().createInner(aName,aEs, aHeroPower, aMasteries);
            case BUFF:
                return new BuffOrDebuffSpellFactory().createInner(aName,aEs, aHeroPower, aMasteries);
            case SUMMON:
                return new SummonSpellFactory().createInner(aName, aEs, aHeroPower, aMasteries);
            case SPECIAL:
                return new SpecialSpellFactory().createInner(aName, aEs, aHeroPower, aMasteries);
            case MAP_CHANGE:
                throw new UnsupportedOperationException(NOT_IMPLEMENTED + aEs.getSpellType());
            default:
                throw new UnsupportedOperationException(DONT_RECOGNIZE_TYPE + aEs.getSpellType());
        }
    }

    public abstract AbstractSpell createInner(String aName, EconomySpell aEs, int aHeroPower,
                                              SpellMasteries aMasteries);

}
