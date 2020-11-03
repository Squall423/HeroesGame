package pl.sdk;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ShapeTest {

    @Test
    void shapeTest(){
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1,1));
        points.add(new Point(1,4));
        points.add(new Point(4,4));
        points.add(new Point(4,1));
        Shape s = new Shape(points);

        s.draw();

    }
}
