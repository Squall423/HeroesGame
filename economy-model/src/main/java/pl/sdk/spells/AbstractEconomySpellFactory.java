package pl.sdk.spells;

import java.util.List;

public abstract class AbstractEconomySpellFactory {

    private static final String INVALID_FACTORY_NAME = "Invalid spell type name";

    public static AbstractEconomySpellFactory getInstance(SpellFactoryType aFactory) {
        switch (aFactory) {
            case DEFAULT:
                return new EconomySpellFactory();
            case TEST:
                return new EconomyTestSpellFactory();
            default:
                throw new IllegalArgumentException(INVALID_FACTORY_NAME);
        }
    }

    public abstract EconomySpell create(String aName);

    public abstract List<EconomySpell> getAllSpells();
}
