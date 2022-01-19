package pl.sdk.hero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdk.creatures.EconomyNecropolisFactory;

import static org.junit.jupiter.api.Assertions.*;

class EconomyHeroTest {
    private EconomyHero hero;

    @BeforeEach
    void init() {
        hero = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, 3000);
    }

    @Test
    void shouldThrowExceptionWhileHeroHas7CreatureAnTryToAddNextOne() {
        EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        hero.addCreature(factory.create(true, 1, 1));
        hero.addCreature(factory.create(true, 1, 1));
        hero.addCreature(factory.create(true, 1, 1));
        hero.addCreature(factory.create(true, 1, 1));
        hero.addCreature(factory.create(true, 1, 1));
        hero.addCreature(factory.create(true, 1, 1));
        hero.addCreature(factory.create(true, 1, 1));

        assertThrows(IllegalStateException.class, () -> hero.addCreature(factory.create(true, 1, 1)));
    }

    @Test
    void shouldThrowExceptionWhileYouTrySubstractMoreGoldThanHeroHas() {


        assertThrows(IllegalStateException.class, () -> hero.subtractGold(3001));
    }
}