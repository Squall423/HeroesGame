package pl.sdk.converter.spells;

import pl.sdk.converter.SpellMasteries;
import pl.sdk.spells.AbstractSpell;
import pl.sdk.spells.EconomySpell;

public abstract class SpellFactory {

    private static String NOT_IMPLEMENTED = "not implemented YET: ";
    private static String DONT_RECOGNIZE_TYPE = "Cannot recognize type: ";

//TODO aName for refactor
    public static AbstractSpell create(EconomySpell aEs, int aHeroPower, SpellMasteries aMasteries) {
        switch (aEs.getSpellType()) {
            case DAMAGE:
                return new DamageSpellFactory().createInner(aEs, aHeroPower, aMasteries);
            case BUFF:
                return new BuffOrDebuffSpellFactory().createInner(aEs, aHeroPower, aMasteries);
            case DEBUFF:
                return new BuffOrDebuffSpellFactory().createInner(aEs, aHeroPower, aMasteries);
            case SUMMON:
                return new SummonSpellFactory().createInner( aEs, aHeroPower, aMasteries);
            case SPECIAL:
                return new SpecialSpellFactory().createInner( aEs, aHeroPower, aMasteries);
            case MAP_CHANGE:
                throw new UnsupportedOperationException(NOT_IMPLEMENTED + aEs.getSpellType());
            default:
                throw new UnsupportedOperationException(DONT_RECOGNIZE_TYPE + aEs.getSpellType());
        }
    }

    public abstract AbstractSpell createInner( EconomySpell aEs, int aHeroPower,
                                              SpellMasteries aMasteries);

}
