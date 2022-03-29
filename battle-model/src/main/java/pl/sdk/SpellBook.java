package pl.sdk;

import pl.sdk.spells.AbstractSpell;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;


public class SpellBook implements PropertyChangeListener {

    private boolean spellWasCastedInThisTurn;
    private static final String CANNOT_CAST_MORE_SPELLS = "You cannot cast more spells in this turn!";
    private int mana;
    private List<AbstractSpell> spells;


    public SpellBook(int aMana, List<AbstractSpell> aSpells) {
        mana = aMana;
        spells = aSpells;

    }

    public List<AbstractSpell> getSpells() {
        return spells;

    }

    public void cast(AbstractSpell aSpell) {
        if (spellWasCastedInThisTurn) {
            throw new IllegalStateException(CANNOT_CAST_MORE_SPELLS);
        }
        spellWasCastedInThisTurn = true;
        mana = mana - aSpell.getManaCost();

    }

    public boolean canCastSpell() {
        return !spellWasCastedInThisTurn;
    }

    public boolean canCastSpell(AbstractSpell aSpell) {
        return mana >= aSpell.getManaCost();
    }

    public void endOfTurn() {
        spellWasCastedInThisTurn = false;
    }

    @Override
    public void propertyChange(PropertyChangeEvent aPropertyChangeEvent) {
        endOfTurn();
    }

    public int getMana() {
        return mana;
    }
}
