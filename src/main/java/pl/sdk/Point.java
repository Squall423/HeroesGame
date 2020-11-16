package pl.sdk;

import java.util.Objects;

public class Point {

    final private int x;
    final private int y;

    public Point(int aX, int aY) {
        x = aX;
        y = aY;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
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
