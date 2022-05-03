package pl.sdk.converter.spells;

import pl.sdk.SpellsStatistic;
import pl.sdk.converter.SpellMasteries;
import pl.sdk.spells.AbstractSpell;
import pl.sdk.spells.EconomySpell;
import pl.sdk.spells.SingleTargetDamageSpell;

class DamageSpellFactory extends SpellFactory {

    private String DONT_RECOGNIZE_SPELL = "Cannot recognize spell";
    private String DONT_RECOGNIZE_MASTERY = "Cannot recognize mastery";

    public AbstractSpell createInner(EconomySpell aEs, int aHeroPower, SpellMasteries aMasteries) {
        switch (aEs.getSpellStatistic()) {
            case FIRE_BALL:
                switch (aMasteries.getFire()) {
                    case BASIC:
                        return new SingleTargetDamageSpell(aEs.getName(),15, SpellsStatistic.TargetType.MAP, aEs.getElement(),
                                aHeroPower * 10 + 15, 3);
                    case ADVANCED:
                        return new SingleTargetDamageSpell(aEs.getName(),15, SpellsStatistic.TargetType.MAP, aEs.getElement(),
                                aHeroPower * 10 + 30, 3);

                    case MASTER:
                        return new SingleTargetDamageSpell(aEs.getName(),15, SpellsStatistic.TargetType.MAP, aEs.getElement(),
                                aHeroPower * 10 + 60, 3);
                    default:

                        throw new UnsupportedOperationException(DONT_RECOGNIZE_MASTERY);
                }

            case IMPLOSION:
                switch (aMasteries.getFire()) {
                    case BASIC:
                        return new SingleTargetDamageSpell(aEs.getName(),aEs.getManaCost(), SpellsStatistic.TargetType.ENEMY,
                                aEs.getElement(),
                                aHeroPower * 75 + 100, 0);
                    case ADVANCED:
                        return new SingleTargetDamageSpell(aEs.getName(),aEs.getManaCost(), SpellsStatistic.TargetType.ENEMY,
                                aEs.getElement(),
                                aHeroPower * 75 + 200, 0);

                    case MASTER:
                        return new SingleTargetDamageSpell(aEs.getName(),aEs.getManaCost(), SpellsStatistic.TargetType.ENEMY,
                                aEs.getElement(),
                                aHeroPower * 75 + 300, 0);
                    default:
                        throw new UnsupportedOperationException(DONT_RECOGNIZE_MASTERY);
                }

            case MAGIC_ARROW:
                switch (aMasteries.findMaxLevel()) {
                    case BASIC:
                        return new SingleTargetDamageSpell(aEs.getName(),aEs.getManaCost(), SpellsStatistic.TargetType.ENEMY,
                                aEs.getElement(),
                                aHeroPower * 10 + 10, 3);
                    case ADVANCED:
                        return new SingleTargetDamageSpell(aEs.getName(),aEs.getManaCost(), SpellsStatistic.TargetType.ENEMY,
                                aEs.getElement(),
                                aHeroPower * 10 + 10, 3);

                    case MASTER:
                        return new SingleTargetDamageSpell(aEs.getName(),aEs.getManaCost(), SpellsStatistic.TargetType.ENEMY,
                                aEs.getElement(),
                                aHeroPower * 10 + 10, 3);
                    default:
                        throw new UnsupportedOperationException(DONT_RECOGNIZE_MASTERY);
                }

            case DEATH_RIPPLE:
                switch (aMasteries.getFire()) {
                    case BASIC:
                        return new SingleTargetDamageSpell(aEs.getName(),aEs.getManaCost(), SpellsStatistic.TargetType.ALL,
                                aEs.getElement(), aHeroPower * 5 + 10, 0);
                    case ADVANCED:
                        return new SingleTargetDamageSpell(aEs.getName(),aEs.getManaCost(), SpellsStatistic.TargetType.ALL,
                                aEs.getElement(), aHeroPower * 5 + 10, 0);

                    case MASTER:
                        return new SingleTargetDamageSpell(aEs.getName(),aEs.getManaCost(), SpellsStatistic.TargetType.ALL,
                                aEs.getElement(), aHeroPower * 5 + 10, 0);
                    default:
                        throw new UnsupportedOperationException(DONT_RECOGNIZE_MASTERY);
                }

        default:
        throw new UnsupportedOperationException(DONT_RECOGNIZE_SPELL);


    }}}