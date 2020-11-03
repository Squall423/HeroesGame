package pl.sdk;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ShapeSegmentsTest {


        @Test
        void drawingUsingSegment(){
            ArrayList<Segment> segments = new ArrayList<>();
            segments.add(new Segment(new Point(1,1), new Point(4,1)));
            segments.add(new Segment(new Point(4,1), new Point(4,4)));
            segments.add(new Segment(new Point(4,4), new Point(1,4)));
            segments.add(new Segment(new Point(1,4), new Point(1,1)));
            ShapeSegments ss = new ShapeSegments(segments);
            ss.draw(); // KWADRAT
            segments.get(1).getEndPoint().setX(5); // Prawy górny róg KWADRATU na x=5,y=4
            segments.get(2).getStartPoint().setX(5); // Prawy górny róg KWADRATU na x=5,y=4
            System.out.println("Segmenty po zmianie punktu ----------");
            ss.draw(); // TRAPEZ  (bok2 = sqrt 10, bok3 = 4)
            System.out.println("#############################################");
            ArrayList<SelfishSegment> segmentsSelfish = new ArrayList<>();
            segmentsSelfish.add(new SelfishSegment(new Point(1,1), new Point(4,1)));
            segmentsSelfish.add(new SelfishSegment(new Point(4,1), new Point(4,4)));
            segmentsSelfish.add(new SelfishSegment(new Point(4,4), new Point(1,4)));
            segmentsSelfish.add(new SelfishSegment(new Point(1,4), new Point(1,1)));
            ShapeSegments sss = new ShapeSegments(segmentsSelfish);
            sss.drawSS(); // KWADRAT
            segmentsSelfish.get(1).getEndPoint().setX(5); // Prawy górny róg KWADRATU na x=5,y=4
            segmentsSelfish.get(2).getStartPoint().setX(5); // Prawy górny róg KWADRATU na x=5,y=4
            System.out.println("SelfischSegmenty po zmianie punktu ----------");
            sss.drawSS(); // to samo ??
        }
    }

