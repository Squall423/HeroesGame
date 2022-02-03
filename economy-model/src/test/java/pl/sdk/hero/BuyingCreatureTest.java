package pl.sdk.hero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdk.creatures.EconomyTestFractionFactory;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.sdk.hero.Fraction.NECROPOLIS;

public class BuyingCreatureTest {

    private EconomyHero hero1;
    private EconomyHero hero2;
    private final EconomyTestFractionFactory creatureFactory = new EconomyTestFractionFactory();
    private EconomyEngine economyEngine;
    private Player player1;
    private Player player2;
    private Fraction fraction = NECROPOLIS;


    @BeforeEach
    void init() {
        Random rand = mock(Random.class);
        when(rand.nextDouble()).thenReturn(1.0);
        CreatureShop shop1 = new CreatureShop(rand, NECROPOLIS);
        CreatureShop shop2 = new CreatureShop(rand, NECROPOLIS);
        hero1 = new EconomyHero();
        hero2 = new EconomyHero();
        player1 = new Player(hero1, shop1, 1000);
        player2 = new Player(hero2, shop2, 1000);
        economyEngine = new EconomyEngine(player1, player2);
    }

    @Test
    void heroShouldCanBuyCreature() {
        economyEngine.buy(creatureFactory.create(false, 1, 1));

        assertEquals(940, player1.getGold());
    }

    @Test
    void heroShouldCanBuyMoreThanOneCreatureInOneStack() {
        economyEngine.buy(creatureFactory.create(false, 1, 2));

        assertEquals(880, player1.getGold());
    }

    @Test
    void heroShouldCanBuyMoreThanOneCreatureInFewStack() {
        economyEngine.buy(creatureFactory.create(false, 1, 2));
        economyEngine.buy(creatureFactory.create(true, 2, 2));

        assertEquals(630, player1.getGold());
    }

    @Test
    void heroCannotBuyCreatureWhenHasNotEnoughtGold() {
        assertThrows(IllegalStateException.class, (() -> economyEngine.buy(creatureFactory.create(false, 1, 100))));
        assertEquals(1000, player1.getGold());
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

        assertEquals(580, player1.getGold());
        assertEquals(7, hero1.getCreatures().size());
    }
}
