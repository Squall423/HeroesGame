package pl.sdk.hero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdk.creatures.EconomyNecropolisFactory;

import static org.junit.jupiter.api.Assertions.*;
import static pl.sdk.hero.Fraction.NECROPOLIS;

class EconomyHeroTest {
    private Player player;


    @BeforeEach
    void init() {
        player = new Player(NECROPOLIS,3000);
    }

    @Test
    void shouldThrowExceptionWhileHeroHas7CreatureAnTryToAddNextOne() {
        EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        player.addCreature(factory.create(true, 1, 1));
        player.addCreature(factory.create(true, 1, 1));
        player.addCreature(factory.create(true, 1, 1));
        player.addCreature(factory.create(true, 1, 1));
        player.addCreature(factory.create(true, 1, 1));
        player.addCreature(factory.create(true, 1, 1));
        player.addCreature(factory.create(true, 1, 1));

        assertThrows(IllegalStateException.class, () -> player.addCreature(factory.create(true, 1, 1)));
    }

    @Test
    void shouldThrowExceptionWhileYouTrySubtractMoreGoldThanHeroHas() {
        assertThrows(IllegalStateException.class, () -> player.subtractGold(3001));
    }
}