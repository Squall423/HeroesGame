package pl.sdk.hero;

import pl.sdk.creatures.EconomyCreature;
import pl.sdk.spells.EconomySpell;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class EconomyEngine {
    public static final String PLAYER_BOUGHT_CREATURE = "PLAYER_BOUGHT_CREATURE";
    public static final String ACTIVE_PLAYER_CHANGED = "ACTIVE_PLAYER_CHANGED";
    public static final String NEXT_ROUND = "NEXT_ROUND";
    public static final String END_OF_TURN = "END_OF_TURN";
    private final Player player1;
    private final Player player2;
    private Player activePlayer;

    private int roundNumber;
    private final PropertyChangeSupport observerSupport;
    private int turnNumber;


    public EconomyEngine(Player aPlayer1, Player aPlayer2) {
        player1 = aPlayer1;
        player2 = aPlayer2;
        activePlayer = player1;
        roundNumber = 1;
        turnNumber = 1;


        observerSupport = new PropertyChangeSupport(this);
//        addObserver(EconomyEngine.NEXT_ROUND, player1.getCreatureShop());
//        addObserver(EconomyEngine.NEXT_ROUND, player2.getCreatureShop());

        player1.getShops().forEach(shop -> addObserver(EconomyEngine.NEXT_ROUND, shop));
        player2.getShops().forEach(shop -> addObserver(EconomyEngine.NEXT_ROUND, shop));

    }


    public void buy(EconomyCreature aEconomyCreature) {
        activePlayer.buyCreature(activePlayer, aEconomyCreature);
        observerSupport.firePropertyChange(PLAYER_BOUGHT_CREATURE, null, null);
    }

    public int calculateMaxAmount(EconomyCreature aCreature) {
        return activePlayer.calculateMaxAmount(aCreature);
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void pass() {
        if (activePlayer == player1) {
            activePlayer = player2;
            observerSupport.firePropertyChange(ACTIVE_PLAYER_CHANGED, player1, player2);
        } else {
            activePlayer = player1;
            observerSupport.firePropertyChange(ACTIVE_PLAYER_CHANGED, player2, player1);
            nextRound();
        }
    }

    //Round = 2 passes
    private void nextRound() {
        roundNumber += 1;
        if (roundNumber == 4) {
            endTurn();
        } else {
            player1.addGold(2000 * roundNumber);
            player2.addGold(2000 * roundNumber);
            observerSupport.firePropertyChange(NEXT_ROUND, roundNumber - 1, roundNumber);
        }
    }

    private void endTurn() {
        turnNumber += 1;
        roundNumber = 1;
        observerSupport.firePropertyChange(END_OF_TURN, -1, turnNumber);
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void addObserver(String aPropertyName, PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aPropertyName, aObserver);
    }

    public Player getPlayer1() {
        //TODO make copy
        return player1;
    }

    public Player getPlayer2() {
        //TODO make copy
        return player2;
    }

    public String heroToString() {
        if (activePlayer == player1) {
            return "Player I";
        } else {
            return "Player II";
        }

    }

    int getTurnNumber() {
        return turnNumber;
    }

    public int getCurrentPopulation(int aTier) {
        return activePlayer.getCurrentPopulation(aTier);
    }

    public List<EconomySpell> getCurrentSpellPopulation() {
        return activePlayer.getCurrentSpellPopulation();
    }

    void buySpell(EconomySpell aEconomySpell) {
        getActivePlayer().buySpell(activePlayer, aEconomySpell);
    }

    boolean hasSpell(String aName) {
        return getActivePlayer().hasSpell(aName);
    }
}
