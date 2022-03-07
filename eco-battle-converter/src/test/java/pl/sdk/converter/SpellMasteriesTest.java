package pl.sdk.converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.sdk.converter.SpellMasteries.SpellMasterLevel.*;

class SpellMasteriesTest {

    @Test
    void shouldFindingMaxCorrectly() {
        SpellMasteries bbbb = new SpellMasteries(BASIC, BASIC, BASIC, BASIC);
        assertEquals(BASIC, bbbb.findMaxLevel());

        SpellMasteries aaaa = new SpellMasteries(ADVANCED, ADVANCED, ADVANCED, ADVANCED);
        assertEquals(ADVANCED, aaaa.findMaxLevel());

        SpellMasteries mmmm = new SpellMasteries(MASTER, MASTER, MASTER, MASTER);
        assertEquals(MASTER, mmmm.findMaxLevel());

        SpellMasteries babb = new SpellMasteries(BASIC, ADVANCED, BASIC, BASIC);
        assertEquals(ADVANCED, babb.findMaxLevel());

        SpellMasteries bbbm = new SpellMasteries(BASIC, BASIC, BASIC, MASTER);
        assertEquals(MASTER, bbbm.findMaxLevel());

        SpellMasteries mbab = new SpellMasteries(MASTER, BASIC, ADVANCED, BASIC);
        assertEquals(MASTER, mbab.findMaxLevel());

        SpellMasteries bmbm = new SpellMasteries(BASIC, MASTER, BASIC, MASTER);
        assertEquals(MASTER, bmbm.findMaxLevel());


    }

}