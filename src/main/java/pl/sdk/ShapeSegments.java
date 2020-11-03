package pl.sdk;

import java.util.List;

public class ShapeSegments {
    private  List<Segment> segments;
    private  List<SelfishSegment> segmentsSelfish;

    public ShapeSegments(List<Segment> aSegment) {
            segments = aSegment;
        }

//    public ShapeSegments(List<SelfishSegment> asegmentsSelfish) {
//
//        segmentsSelfish = asegmentsSelfish;
//    }

    void draw() {
        for (int i = 0; i < segments.size() - 1; i++) {
            Segment s = new Segment(segments.get(i).getStartPoint(), segments.get(i + 1).getEndPoint());
            s.draw();

        }
//        Segment s = new Segment(segments.get(segments.size() - 1), segments.get(0));
//        s.draw();
    }

    void drawSS() {
        for (int i = 0; i < segments.size(); i++) {
            SelfishSegment s = new SelfishSegment(segments.get(i).getStartPoint(), segments.get(i).getEndPoint());
            s.draw();
        }
    }

}
