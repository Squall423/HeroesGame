package pl.sdk.converter.spells;

import pl.sdk.converter.SpellMasteries;
import pl.sdk.spells.AbstractSpell;
import pl.sdk.spells.DispelSpell;
import pl.sdk.spells.EconomySpell;



class SpecialSpellFactory extends SpellFactory {
    @Override
    public AbstractSpell createInner(EconomySpell aEs, int aHeroPower, SpellMasteries aMasteries) {
        switch (aEs.getSpellStatistic()) {
            case DISPEL:
                return new DispelSpell(aEs.getManaCost(), aHeroPower, aEs.getElement());

                case TELEPORT:
                return new DispelSpell(aEs.getManaCost(), aHeroPower, aEs.getElement());

            default:
                throw new UnsupportedOperationException("Cannot recognize spell");
        }
    }
}
