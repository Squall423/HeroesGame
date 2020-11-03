package pl.sdk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SegmentTest {
    @Test
    void blabla() {
        Point point1 = new Point(1, 1);
        Point point2 = new Point(3, 1);
        Point point3 = new Point(1, 3);

        Point poin1copy = new Point(point1);
//        Point point1copy = (Point) point1.clone();
        Segment ss1 = new SelfishSegment(point1, point2);
        Segment ss2 = new SelfishSegment(point1, point3);

        assertEquals(ss1, ss2);
        ss1.getStartPoint().setX(5);

        assertEquals(ss1, ss2);
    }
}

