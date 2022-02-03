package pl.sdk.hero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdk.creatures.EconomyTestFractionFactory;

import static org.junit.jupiter.api.Assertions.*;

class EconomyEngineTest {

    private EconomyEngine economyEngine;
    private EconomyTestFractionFactory creatureFactory;
    private CreatureShop creatureShop;
    private Player player1;
    private Player player2;

    @BeforeEach
    void init() {
        player1 = new Player(Fraction.NECROPOLIS, 1000);
        player2 = new Player(Fraction.NECROPOLIS, 1000);
        creatureShop = new CreatureShop(Fraction.NECROPOLIS);
        economyEngine = new EconomyEngine(player1, player2);
        creatureFactory = new EconomyTestFractionFactory();
    }

    @Test
    void shouldChangeActiveHeroAfterPass() {
        assertEquals(player1, economyEngine.getActivePlayer());
        economyEngine.pass();
        assertEquals(player2, economyEngine.getActivePlayer());
    }

    @Test
    void shouldCountRoundCorrectly() {
        assertEquals(1, economyEngine.getRoundNumber());
        economyEngine.pass();
        assertEquals(1, economyEngine.getRoundNumber());
        economyEngine.pass();
        assertEquals(2, economyEngine.getRoundNumber());
    }

    @Test
    void shouldBuyCreatureInCorrectHero() {
        economyEngine.buy(creatureFactory.create(false, 1, 1));
        assertEquals(940, player1.getGold());
        assertEquals(1000, player2.getGold());
        economyEngine.pass();
        economyEngine.buy(creatureFactory.create(false, 2, 1));
        assertEquals(940, player1.getGold());
        assertEquals(900, player2.getGold());
    }

    @Test
    void shouldCountTurnAndRoundCorrectly() {
        assertEquals(1, economyEngine.getRoundNumber());
        assertEquals(1, economyEngine.getTurnNumber());
        economyEngine.pass();
        assertEquals(1, economyEngine.getRoundNumber());
        assertEquals(1, economyEngine.getTurnNumber());
        economyEngine.pass();
        economyEngine.pass();
        assertEquals(2, economyEngine.getRoundNumber());
        assertEquals(1, economyEngine.getTurnNumber());
        economyEngine.pass();
        economyEngine.pass();
        assertEquals(3, economyEngine.getRoundNumber());
        assertEquals(1, economyEngine.getTurnNumber());
        economyEngine.pass();
        economyEngine.pass();
        assertEquals(1, economyEngine.getRoundNumber());
        assertEquals(2, economyEngine.getTurnNumber());
    }

    @Test
    void shouldAddGoldAfterRoundEnd() {
        assertEquals(1000, player1.getGold());
        assertEquals(1000, player2.getGold());
        economyEngine.pass();
        economyEngine.pass();
        assertEquals(5000, player1.getGold());
        assertEquals(5000, player2.getGold());
        economyEngine.pass();
        economyEngine.pass();
        assertEquals(11000, player1.getGold());
        assertEquals(11000, player2.getGold());
        economyEngine.pass();
        economyEngine.pass();
        assertEquals(11000, player1.getGold());
        assertEquals(11000, player2.getGold());

    }
}