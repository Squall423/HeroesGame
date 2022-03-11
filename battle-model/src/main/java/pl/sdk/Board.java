package pl.sdk;

import pl.sdk.creatures.Creature;

import java.util.HashMap;
import java.util.Map;

import static pl.sdk.GameEngine.BOARD_HEIGHT;
import static pl.sdk.GameEngine.BOARD_WIDTH;

class Board {

    private final Map<Point, Creature> map;

    Board() {
        map = new HashMap<>();
    }

    void add(Point aPoint, Creature aCreature) {
        throwExceptionWhenIsOutsideMap(aPoint);
        throwExceptionIfTileIsTaken(aPoint);
        map.put(aPoint, aCreature);
    }

    private boolean isTileTaken(Point aPoint) {
        return map.containsKey(aPoint);
    }


    private void throwExceptionIfTileIsTaken(Point aPoint) {
        if (isTileTaken(aPoint)) {
            throw new IllegalArgumentException("Tile isn't empty");
        }
    }

    private void throwExceptionWhenIsOutsideMap(Point aPoint) {
        if (aPoint.getX() < 0 || aPoint.getX() > BOARD_WIDTH || aPoint.getY() < 0 || aPoint.getY() > BOARD_HEIGHT) {
            throw new IllegalArgumentException("You are trying to works outside the map");
        }
    }

    Creature get(int aX, int aY) {
        return map.get(new Point(aX, aY));
    }

    Creature get(Point aPoint) {
        return map.get(aPoint);
    }

    Point get(Creature aCreature) {
        if (map.containsValue(aCreature)) {
            return map.keySet().stream().filter(p -> map.get(p).equals(aCreature)).findAny().get();

        } else {
            return null;
        }
    }

    void move(Creature aCreature, Point aTargetPoint1) {
        move(get(aCreature), aTargetPoint1);
    }

    void move(Point aSourcePoint, Point aTargetPoint1) {
        throwExceptionWhenIsOutsideMap(aTargetPoint1);
        throwExceptionIfTileIsTaken(aTargetPoint1);
        Creature creatureFromSourcePoint = map.get(aSourcePoint);
        map.remove(aSourcePoint);
        map.put(aTargetPoint1, creatureFromSourcePoint);
    }

    boolean canMove(Creature aCreature, int aX, int aY) {
        throwExceptionWhenIsOutsideMap(new Point(aX, aY));
        if (!map.containsValue(aCreature)) {
            throw new IllegalStateException("Creature isn't in board");
        }
        Point currentPosition = get(aCreature);
        double distance = currentPosition.distance(new Point(aX, aY));
        return distance <= aCreature.getMoveRange() && !isTileTaken(new Point(aX, aY));
    }


}
