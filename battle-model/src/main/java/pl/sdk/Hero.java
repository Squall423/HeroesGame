package pl.sdk;

import pl.sdk.creatures.Creature;
import pl.sdk.spells.AbstractSpell;

import java.util.ArrayList;
import java.util.List;


public class Hero {

    public enum Site {
        LEFT, RIGHT
    }

    private final List<Creature> creatures;
    private final SpellBook spellBook;


    public Hero(List<Creature> aCreatures) {
        this(aCreatures, new SpellBook(10, new ArrayList<>()));
    }

    public Hero(List<Creature> aCreatures, SpellBook aSpellBook) {
        creatures = aCreatures;
        spellBook = aSpellBook;
    }

    public List<Creature> getCreatures() {
        return creatures;

    }

    public List<AbstractSpell> getSpells() {
        return spellBook.getSpells();
    }

    public boolean canCastSpell() {
        return spellBook.canCastSpell();
    }

    void castSpell(AbstractSpell aMagicArrow) {
        spellBook.cast(aMagicArrow);
    }

    void toSubscribeEndOfTurn(TurnQueue aQueue) {
        aQueue.addObserver(spellBook);
    }

}
