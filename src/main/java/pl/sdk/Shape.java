package pl.sdk;

import java.util.*;

public class Shape {
    private List<Point> points;

    private Stack<Point> pointsStack;
    private Queue<Point> pointsQueue;
    private Set<Point> pointsSet;

    public Shape(List<Point> aPoints) {
        points = aPoints;
    }

    public Shape(Stack<Point> aPointsStack) {
        pointsStack = aPointsStack;
    }
    public Shape(Queue<Point> aPointsQueue) {
        pointsQueue = aPointsQueue;
    }
    public Shape(Set<Point> aPointsSet) {
        pointsSet = aPointsSet;
    }

    void draw() {
        for (int i = 0; i < points.size() - 1; i++) {
            Segment s = new Segment(points.get(i), points.get(i + 1));
            s.draw();
        }
        Segment s = new Segment(points.get(points.size() - 1), points.get(0));
        s.draw();
    }
}
