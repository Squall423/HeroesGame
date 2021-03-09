package pl.sdk;

import org.junit.jupiter.api.Test;
import pl.sdk.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointTest {

    @Test
    void shouldCountDistanceCorrectly() {
        Point p00 = new Point(0, 0);
        Point p10 = new Point(1, 0);
        Point p01 = new Point(0, 1);
        Point p11 = new Point(1, 1);
        Point p1010 = new Point(10, 10);

        assertEquals(1.0, p00.distance(p10));
        assertEquals(1.0, p00.distance(p01));
        assertEquals(Math.sqrt(2), p00.distance(p11));
        assertEquals(Math.sqrt(200), p00.distance(p1010));
    }
}
