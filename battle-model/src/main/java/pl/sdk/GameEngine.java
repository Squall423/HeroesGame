package pl.sdk;

import pl.sdk.creatures.Creature;
import pl.sdk.spells.AbstractSpell;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.stream.Collectors;

public class GameEngine {

    public final static int BOARD_WIDTH = 20;
    public final static int BOARD_HEIGHT = 15;

    public static final String CURRENT_CREATURE_CHANGED = "CURRENT_CREATURE_CHANGED";
    public static final String CREATURE_MOVED = "CREATURE_MOVED";
    public static final String CREATURE_ATTACKED = "CREATURE_ATTACKED";
    public static final String AFTER_MOVE = "AFTER_MOVE";
    public static final String AFTER_ATTACK = "AFTER_ATTACK";
    public static final String END_OF_TURN = "END_OF_TURN";
    private final Board board;
    private final TurnQueue queue;
    private final PropertyChangeSupport observerSupport;
    private Hero hero1;
    private Hero hero2;
    private boolean blockMoving;
    private boolean blockAttacking;
    private List<Creature> creatures1;
    private List<Creature> creatures2;

    public GameEngine(Hero aHero1, Hero aHero2) {
        this(aHero1, aHero2, new Board());
    }

    GameEngine(Hero aHero1, Hero aHero2, Board aBoard) {
        board = aBoard;
        hero1 = aHero1;
        hero2 = aHero2;
        queue = new TurnQueue(aHero1, aHero2);
        hero1.toSubscribeEndOfTurn(queue);
        hero2.toSubscribeEndOfTurn(queue);
        creatures1 = aHero1.getCreatures();
        creatures2 = aHero2.getCreatures();
        putCreaturesToBoard(creatures1, creatures2);

        observerSupport = new PropertyChangeSupport(this);
    }

    public void addObserver(String aEventType, PropertyChangeListener aObs) {
        if (END_OF_TURN.equals(aEventType)) {
            queue.addObserver(aObs);
        } else {
            observerSupport.addPropertyChangeListener(aEventType, aObs);
        }
    }

    public void removeObserver(PropertyChangeListener aObs) {
        observerSupport.removePropertyChangeListener(aObs);
    }

    void notifyObservers(PropertyChangeEvent aEvent) {
        observerSupport.firePropertyChange(aEvent);
    }

    public void move(Point aTargetPoint) {
        if (blockMoving) {
            return;
        }
        Point oldPosition = board.get(queue.getActiveCreature());
        board.move(queue.getActiveCreature(), aTargetPoint);
        blockMoving = true;
        notifyObservers(new PropertyChangeEvent(this, CREATURE_MOVED, oldPosition, aTargetPoint));
        observerSupport.firePropertyChange(AFTER_MOVE, null, null);
    }

    public void pass() {
        Creature oldActiveCreature = queue.getActiveCreature();
        queue.next();
        blockAttacking = false;
        blockMoving = false;
        Creature newActiveCreature = queue.getActiveCreature();
        notifyObservers(new PropertyChangeEvent(this, CURRENT_CREATURE_CHANGED, oldActiveCreature, newActiveCreature));
    }

    public void attack(int aX, int aY) {
        if (blockAttacking) {
            return;
        }
        Creature activeCreature = queue.getActiveCreature();
        boolean[][] splashRange = activeCreature.getSplashRange();
        for (int x = 0; x < splashRange.length; x++) {
            for (int y = 0; y < splashRange.length; y++) {
                if (splashRange[x][y]) {
                    activeCreature.attack(board.get(aX + x - 1, aY + y - 1));
                }
            }
        }


        blockAttacking = true;
        blockMoving = true;
        notifyObservers(new PropertyChangeEvent(this, CREATURE_ATTACKED, null, null));
        observerSupport.firePropertyChange(AFTER_ATTACK, null, null);
        observerSupport.firePropertyChange(AFTER_MOVE, null, null);
    }

    private void putCreaturesToBoard(List<Creature> aCreatures1, List<Creature> aCreatures2) {
        putCreaturesFromOneSideToBoard(aCreatures1, 0);
        putCreaturesFromOneSideToBoard(aCreatures2, GameEngine.BOARD_WIDTH - 1);
    }

    private void putCreaturesFromOneSideToBoard(List<Creature> aCreatures, int aX) {
        for (int i = 0; i < aCreatures.size(); i++) {
            board.add(new Point(aX, i * 2 + 1), aCreatures.get(i));
        }
    }

    public Creature get(int aX, int aY) {
        return board.get(aX, aY);
    }

    public Creature getActiveCreature() {
        return queue.getActiveCreature();
    }
    public Hero getActiveHero() {
        return queue.getActiveHero();
    }

    public boolean canMove(int aX, int aY) {
        return board.canMove(getActiveCreature(), aX, aY);
    }

    public boolean canAttack(int aX, int aY) {
        boolean isP1Creature = creatures1.contains(getActiveCreature());
        boolean theSamePlayerUnit;
        if (isP1Creature) {
            theSamePlayerUnit = creatures1.contains(board.get(aX, aY));
        } else {
            theSamePlayerUnit = creatures2.contains(board.get(aX, aY));
        }

        return !theSamePlayerUnit && board.get(getActiveCreature()).distance(new Point(aX, aY)) <= getActiveCreature().getAttackRange();
    }

    public boolean isHeroTwoGotCreature(Creature aCreature) {
        return creatures2.contains(aCreature);
    }

    public boolean canCastSpell() {
        return queue.getActiveHero().canCastSpell();
    }

    public boolean canCastSpell(AbstractSpell aSpell) {return queue.getActiveHero().canCastSpell();
    }
    public boolean canCastSpell(AbstractSpell aSpell, Point aPoint) {
      SpellSplashCalculator calc = new SpellSplashCalculator();
     return calc.canCast(aSpell,aPoint,this,board);
    }

    void cast(AbstractSpell aSpell, Point aTargetPoint) {
        queue.getActiveHero().castSpell(aSpell);
        SpellSplashCalculator spellSplashCalculator = new SpellSplashCalculator();
        List<Creature> creatures = (spellSplashCalculator.calc(aSpell, board, aTargetPoint, this))
                .stream()
                .map(board::get)
                .collect(Collectors.toList());
        creatures.forEach(t -> aSpell.cast(t));
    }

    boolean isAllyCreature(Point aP) {
        return queue.getActiveHero().getCreatures().contains(board.get(aP));
    }

    boolean isEnemyCreature(Point aP) {
        return board.get(aP) != null && !queue.getActiveHero().getCreatures().contains(board.get(aP));
    }
}