package pl.sdk.converter.spells;

import pl.sdk.SpellsStatistic;
import pl.sdk.converter.SpellMasteries;
import pl.sdk.spells.AbstractSpell;
import pl.sdk.spells.BuffOrDebuffSpell;
import pl.sdk.spells.BuffOrDebuffStatistic;
import pl.sdk.spells.EconomySpell;

class BuffOrDebuffSpellFactory extends SpellFactory {

    private BuffOrDebuffStatistic stats;

    @Override
    public AbstractSpell createInner(EconomySpell aEs, int aHeroPower, SpellMasteries aMasteries) {
        switch (aEs.getSpellStatistic()) {
            case HASTE:
                switch (aMasteries.getAir()) {
                    case BASIC:
                        stats = BuffOrDebuffStatistic.builder().moveRange(3).build();
                        return new BuffOrDebuffSpell(aEs.getName(), aEs.getManaCost(), aHeroPower,
                                SpellsStatistic.TargetType.ALLY, aEs.getElement(), stats);
                    case ADVANCED:
                        stats = BuffOrDebuffStatistic.builder().moveRange(5).build();
                        return new BuffOrDebuffSpell(aEs.getName(), aEs.getManaCost(), aHeroPower,
                                SpellsStatistic.TargetType.ALLY, aEs.getElement(), stats);
                    case MASTER:
                        stats = BuffOrDebuffStatistic.builder().moveRange(5).build();
                        return new BuffOrDebuffSpell(aEs.getName(), aEs.getManaCost(), aHeroPower,
                                SpellsStatistic.TargetType.ALLY, aEs.getElement(), stats);
                    default:
                        throw new UnsupportedOperationException("Cannot recognize spell");
                }
            case SLOW:
                switch (aMasteries.getEarth()) {
                    case BASIC:
                        stats = BuffOrDebuffStatistic.builder().moveRangePercentage(-0.25).build();
                        return new BuffOrDebuffSpell(aEs.getName(), aEs.getManaCost(), aHeroPower,
                                SpellsStatistic.TargetType.ENEMY, aEs.getElement(), stats);
                    case ADVANCED:
                        stats = BuffOrDebuffStatistic.builder().moveRangePercentage(-0.5).build();
                        return new BuffOrDebuffSpell(aEs.getName(), aEs.getManaCost(), aHeroPower,
                                SpellsStatistic.TargetType.ENEMY, aEs.getElement(), stats);
                    case MASTER:
                        stats = BuffOrDebuffStatistic.builder().moveRangePercentage(-0.5).build();
                        return new BuffOrDebuffSpell(aEs.getName(), aEs.getManaCost(), aHeroPower,
                                SpellsStatistic.TargetType.ENEMY, aEs.getElement(), stats);
                    default:
                        throw new UnsupportedOperationException("Cannot recognize spell");
                }
            default:
                throw new UnsupportedOperationException("Cannot recognize spell");
        }
    }
}