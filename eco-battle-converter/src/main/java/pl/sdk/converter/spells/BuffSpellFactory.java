package pl.sdk.converter.spells;

import pl.sdk.converter.SpellMasteries;
import pl.sdk.spells.AbstractSpell;
import pl.sdk.spells.BuffSpell;
import pl.sdk.spells.EconomySpell;

class BuffSpellFactory extends SpellFactory {

    @Override
    public AbstractSpell createInner(EconomySpell aEs, int aHeroPower, SpellMasteries aMasteries) {
        switch (aEs.getSpellStatistic()) {
            case HASTE:
                return new BuffSpell(aEs.getManaCost(), aHeroPower, aEs.getElement());
            default:
                throw new UnsupportedOperationException("Cannot recognize spell");

        }
    }
}