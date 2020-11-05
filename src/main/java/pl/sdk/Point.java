package pl.sdk;

import java.util.Objects;

class Point {

    final private int x;
    final private int y;

     Point(int aX, int aY) {
        x = aX;
        y = aY;
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
