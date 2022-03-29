package pl.sdk.converter.spells;

import pl.sdk.converter.SpellMasteries;
import pl.sdk.spells.AbstractSpell;
import pl.sdk.spells.DebuffSpell;
import pl.sdk.spells.EconomySpell;

class DebuffSpellFactory extends SpellFactory {

    @Override
    public AbstractSpell createInner(EconomySpell aEs, int aHeroPower, SpellMasteries aMasteries) {
        switch (aEs.getSpellStatistic()) {
            case SLOW:
                return new DebuffSpell(aEs.getManaCost(), aHeroPower, aEs.getElement() );
            default:
                throw new UnsupportedOperationException("Cannot recognize spell");

        }
    }
}
