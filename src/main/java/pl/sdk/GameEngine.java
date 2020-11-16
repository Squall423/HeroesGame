package pl.sdk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GameEngine {
    private final Board board;
    private final CreatureTurnQueue queue;

    GameEngine(List<Creature> aCreatures1, List<Creature> aCreatures2) {
        board = new Board();
        putCreaturesToBoard(aCreatures1, aCreatures2);
        List<Creature> twoSidesCreatures = new ArrayList<>();
        twoSidesCreatures.addAll(aCreatures1);
        twoSidesCreatures.addAll(aCreatures2);
        queue = new CreatureTurnQueue(aCreatures1);
    }

    void move(Point aTargetPoint){
        board.move(queue.getActiveCreature(), aTargetPoint);
    }
    private void putCreaturesToBoard(List<Creature> aCreatures1, List<Creature> aCreatures2) {
        putCreaturesFromOneSideToBoard(aCreatures1, 0);
        putCreaturesFromOneSideToBoard(aCreatures2, Board.WIDTH - 1);
    }

    private void putCreaturesFromOneSideToBoard(List<Creature> aCreatures, int aX) {
        for (int i = 0; i < aCreatures.size(); i++) {
            board.add(new Point(aX, i * 2), aCreatures.get(i));
        }
    }
}