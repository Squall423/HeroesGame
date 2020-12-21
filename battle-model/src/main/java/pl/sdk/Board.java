package pl.sdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

 class Board {
    private final Map<Point, Creature> map;
    public final static int WIDTH = 20;
    public final static int HEIGHT = 15;

     Board() {
        map = new HashMap<>();
    }

    void add(Point aPoint, Creature aCreature) {
        throwExceptionWhenFieldIsTakenOrIsOutsideMap(aPoint);
        map.put(aPoint, aCreature);
    }

    private void throwExceptionWhenFieldIsTakenOrIsOutsideMap(Point aPoint) {
        if (aPoint.getX() < 0 || aPoint.getX() > WIDTH || aPoint.getY() < 0 || aPoint.getY() > HEIGHT || map.containsKey(aPoint)) {
            throw new IllegalArgumentException();
        }
    }

    Creature get(int aX, int aY) {
        return map.get(new Point(aX, aY));
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
        throwExceptionWhenFieldIsTakenOrIsOutsideMap(aTargetPoint1);
        Creature creatureFromSourcePoint = map.get(aSourcePoint);
        map.remove(aSourcePoint);
        map.put(aTargetPoint1, creatureFromSourcePoint);
    }

     boolean canMove(Creature aActiveCreature, int aX, int aY) {
         return true;
     }
 }
