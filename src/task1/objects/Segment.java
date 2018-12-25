package task1.objects;

import task1.interfaces.IShape;

import java.util.Arrays;

public class Segment extends OpenFigure {

    private Point2D start;
    private Point2D finish;

    public Segment(Point2D start, Point2D finish) {
        this.start = start.clone();
        this.finish = finish.clone();
    }

    public Point2D getStart() {
        return start.clone();
    }

    public void setStart(Point2D start) {
        this.start = start.clone();
    }

    public Point2D getFinish() {
        return finish.clone();
    }

    public void setFinish(Point2D finish) {
        this.finish = finish.clone();
    }

    @Override
    public double length() {
        return Point2D.distance(start, finish);
    }

    @Override
    public Segment shift(Point2D a) {
        Segment newLine = this.clone();
        newLine.start = new Point2D(start.add(a).getCoordinates());
        newLine.finish = new Point2D(finish.add(a).getCoordinates());
        return newLine;
    }

    @Override
    public Segment rot(double alpha) {
        Segment newLine = this.clone();
        newLine.start = start.rot(alpha);
        newLine.finish = finish.rot(alpha);
        return newLine;
    }

    @Override
    public Segment symAxis(int i) {
        Segment newLine = this.clone();
        newLine.start = new Point2D(start.symaxis(i).getCoordinates());
        newLine.finish = new Point2D(finish.symaxis(i).getCoordinates());
        return newLine;
    }

    @Override
    public boolean cross(IShape shape) {
        if (!(shape instanceof Segment)) {
            return false;
        } else {
            Segment lineCross = (Segment) shape;
            double bx1 = lineCross.start.getX();
            double by1 = lineCross.start.getY();
            double bx2 = lineCross.finish.getX();
            double by2 = lineCross.finish.getY();
            double ax1 = start.getX();
            double ay1 = start.getY();
            double ax2 = finish.getX();
            double ay2 = finish.getY();
            double v1 = (bx2 - bx1) * (ay1 - by1) - (by2 - by1) * (ax1 - bx1);
            double v2 = (bx2 - bx1) * (ay2 - by1) - (by2 - by1) * (ax2 - bx1);
            double v3 = (ax2 - ax1) * (by1 - ay1) - (ay2 - ay1) * (bx1 - ax1);
            double v4 = (ax2 - ax1) * (by2 - ay1) - (ay2 - ay1) * (bx2 - ax1);
            return ((v1 * v2 < 0) && (v3 * v4 < 0));
        }
    }

    @Override
    protected Segment clone() {
        return new Segment(this.start, this.finish);
    }

    @Override
    public String toString() {
        return "Отрезок {" + start.toString() + " ; " + finish.toString() + "}";
    }
}
