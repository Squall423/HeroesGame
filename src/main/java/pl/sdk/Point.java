package pl.sdk;

import java.util.Objects;

public class Point {
    private double distanceToCenter;
    private int x;
    private int y;

    public Point(int aX, int aY) {
        this.x = aX;
        this.y = aY;
       distanceToCenter =  Math.sqrt(x*x+y*y);
    }
    public Point(Point aPoint1){
        this(aPoint1.x,aPoint1.y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Point(x, y);
    }

    public double getDistanceToCenter() {
        return getDistanceToCenter();
    }

    @Override
    public boolean equals(Object aO) {
        if (this == aO) return true;
        if (aO == null || getClass() != aO.getClass()) return false;
        Point point = (Point) aO;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
