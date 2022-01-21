package pl.sdk.hero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdk.creatures.EconomyNecropolisFactory;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuyingCreatureTest {

    private EconomyHero hero1;
    private EconomyHero hero2;
    private final EconomyNecropolisFactory creatureFactory = new EconomyNecropolisFactory();
    private EconomyEngine economyEngine;



    @BeforeEach
    void init() {
        Random rand = mock(Random.class);
        when(rand.nextDouble()).thenReturn(1.0);
        CreatureShop creatureShop = new CreatureShop(rand);
        hero1 = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, 1000);
        hero2 = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, 1000);
        economyEngine = new EconomyEngine(hero1, hero2, creatureShop);
    }

    @Test
    void heroShouldCanBuyCreature() {
        economyEngine.buy(creatureFactory.create(false, 1, 1));

        assertEquals(940, hero1.getGold());
    }

    @Test
    void heroShouldCanBuyMoreThanOneCreatureInOneStack() {
        economyEngine.buy(creatureFactory.create(false, 1, 2));

        assertEquals(880, hero1.getGold());
    }

    @Test
    void heroShouldCanBuyMoreThanOneCreatureInFewStack() {
        economyEngine.buy(creatureFactory.create(false, 1, 2));
        economyEngine.buy(creatureFactory.create(true, 2, 2));

        assertEquals(630, hero1.getGold());
    }

    @Test
    void heroCannotBuyCreatureWhenHasNotEnoughtGold() {
        assertThrows(IllegalStateException.class, (() -> economyEngine.buy(creatureFactory.create(false, 1, 100))));
        assertEquals(1000, hero1.getGold());
        assertEquals(0, hero1.getCreatures().size());
    }

    @Test
    void heroCannotBuyCreatureIfHeIsFull() {
        economyEngine.buy(creatureFactory.create(false, 1, 1));
        economyEngine.buy(creatureFactory.create(false, 1, 1));
        economyEngine.buy(creatureFactory.create(false, 1, 1));
        economyEngine.buy(creatureFactory.create(false, 1, 1));
        economyEngine.buy(creatureFactory.create(false, 1, 1));
        economyEngine.buy(creatureFactory.create(false, 1, 1));
        economyEngine.buy(creatureFactory.create(false, 1, 1));

        assertThrows(IllegalStateException.class, (() -> economyEngine.buy(creatureFactory.create(false, 1, 1))));

        assertEquals(580, hero1.getGold());
        assertEquals(7, hero1.getCreatures().size());
    }
}
