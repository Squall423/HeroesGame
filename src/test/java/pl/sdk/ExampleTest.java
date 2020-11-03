package pl.sdk;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class ExampleTest {

    @Test
    void example() {
        //given
        Point point = new Point(1, 5);
        //when
        int x = point.getX();
        //then
        assertEquals(1, x);
    }

    @Test
    void distanceShouldBeServer() {
        //given
        Point point = new Point(0, 7);
        Point point2 = new Point(0, 8);
        //when
        double result = point.getDistanceToCenter();
        double result2 = point2.getDistanceToCenter();
        //then
        assertEquals(7.0, result);
        assertEquals(8.0, result2);

    }

    @Test
    void blabla() {
        Point point = new Point(1, 1);
        Point point2 = new Point(1, 1);

        assertEquals(point, point2);
    }
}
