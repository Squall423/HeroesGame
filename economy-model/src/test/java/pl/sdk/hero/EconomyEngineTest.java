package pl.sdk.hero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdk.creatures.EconomyNecropolisFactory;

import static org.junit.jupiter.api.Assertions.*;

class EconomyEngineTest {

    private EconomyEngine economyEngine;
    private EconomyHero h1;
    private EconomyHero h2;
    private EconomyNecropolisFactory creatureFactory;
    private CreatureShop creatureShop;

    @BeforeEach
    void init() {
        h1 = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, 1000);
        h2 = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, 1000);
        creatureShop = new CreatureShop();
        economyEngine = new EconomyEngine(h1, h2, creatureShop);
        creatureFactory = new EconomyNecropolisFactory();
    }

    @Test
    void shouldChangeActiveHeroAfterPass() {
        assertEquals(h1, economyEngine.getActiveHero());
        economyEngine.pass();
        assertEquals(h2, economyEngine.getActiveHero());
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
        assertEquals(940, h1.getGold());
        assertEquals(1000, h2.getGold());
        economyEngine.pass();
        economyEngine.buy(creatureFactory.create(false, 2, 1));
        assertEquals(940, h1.getGold());
        assertEquals(900, h2.getGold());
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
        assertEquals(1000, h1.getGold());
        assertEquals(1000, h2.getGold());
        economyEngine.pass();
        economyEngine.pass();
        assertEquals(5000, h1.getGold());
        assertEquals(5000, h2.getGold());
        economyEngine.pass();
        economyEngine.pass();
        assertEquals(11000, h1.getGold());
        assertEquals(11000, h2.getGold());
        economyEngine.pass();
        economyEngine.pass();
        assertEquals(11000, h1.getGold());
        assertEquals(11000, h2.getGold());

    }
}