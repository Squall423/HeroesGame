package pl.sdk.converter.spells;

import pl.sdk.SpellsStatistic;
import pl.sdk.converter.SpellMasteries;
import pl.sdk.spells.AbstractSpell;
import pl.sdk.spells.BuffOrDebuffSpell;
import pl.sdk.spells.EconomySpell;

class BuffOrDebuffSpellFactory extends SpellFactory {

    @Override
    public AbstractSpell createInner(String aName, EconomySpell aEs, int aHeroPower, SpellMasteries aMasteries) {
        switch (aEs.getSpellStatistic()) {
            case HASTE:
                switch (aMasteries.getAir()) {
                    case BASIC:
                        return new BuffOrDebuffSpell(aName, aEs.getManaCost(), aHeroPower,
                                SpellsStatistic.TargetType.ALLY, aEs.getElement());
                    case ADVANCED:
                        return new BuffOrDebuffSpell(aName, aEs.getManaCost(), aHeroPower,
                                SpellsStatistic.TargetType.ALLY, aEs.getElement());
                    case MASTER:
                        return new BuffOrDebuffSpell(aName, aEs.getManaCost(), aHeroPower,
                                SpellsStatistic.TargetType.ALLY, aEs.getElement());
                    default:
                        throw new UnsupportedOperationException("Cannot recognize spell");
                }
            case SLOW:
                switch (aMasteries.getEarth()) {
                    case BASIC:
                        return new BuffOrDebuffSpell(aName, aEs.getManaCost(), aHeroPower,
                                SpellsStatistic.TargetType.ENEMY, aEs.getElement());
                    case ADVANCED:
                        return new BuffOrDebuffSpell(aName, aEs.getManaCost(), aHeroPower,
                                SpellsStatistic.TargetType.ENEMY, aEs.getElement());
                    case MASTER:
                        return new BuffOrDebuffSpell(aName, aEs.getManaCost(), aHeroPower,
                                SpellsStatistic.TargetType.ENEMY, aEs.getElement());
                    default:
                        throw new UnsupportedOperationException("Cannot recognize spell");
                }
            default:
                throw new UnsupportedOperationException("Cannot recognize spell");
        }
    }
}