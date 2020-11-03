package pl.sdk;

import java.util.Objects;

public class Segment {
    private Point startPoint;
    private  Point endPoint;

    public Segment(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    void draw(){
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Segment{" +
                "startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                ", length=" + calculateLenght(this) +
                '}';
    }

    @Override
    public boolean equals(Object aO) {
        return calculateLenght(this) == calculateLenght((Segment) aO);
    }

    private double calculateLenght(Segment aSegment){
        int diffX = aSegment.startPoint.getX() - aSegment.endPoint.getX();
        int diffY = aSegment.startPoint.getY() - aSegment.endPoint.getY();
        return Math.sqrt(diffX * diffX + diffY * diffY);


    }
    @Override
    public int hashCode() {
        return Objects.hash(startPoint, endPoint);
    }
}
