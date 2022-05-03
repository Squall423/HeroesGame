package pl.sdk.converter.spells;

import pl.sdk.converter.SpellMasteries;
import pl.sdk.spells.AbstractSpell;
import pl.sdk.spells.EconomySpell;
import pl.sdk.spells.SummonSpell;

class SummonSpellFactory extends SpellFactory {

    @Override
    public AbstractSpell createInner(EconomySpell aEs, int aHeroPower, SpellMasteries aMasteries) {
        switch (aEs.getSpellStatistic()) {
            case SUMMON_AIR_ELEMENTAL:
                return new SummonSpell(aEs.getName(),aEs.getManaCost(), aHeroPower, aEs.getElement());
            default:
                throw new UnsupportedOperationException("Cannot recognize spell");

        }
    }
}