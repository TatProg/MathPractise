package task1.objects;

import task1.interfaces.IShape;

import java.util.Arrays;

public class Polyline extends OpenFigure {

    private int n;
    private Point2D[] coordinates;

    public Polyline(Point2D... coordinates) {
        this.n = coordinates.length;
        this.coordinates = new Point2D[n];
        for (int i = 0; i < n; i++) {
            this.coordinates[i] = coordinates[i].clone();
        }
    }

    public int getN() {
        return n;
    }

    public Point2D[] getCoordinates() {
        Point2D[] newPoints = new Point2D[n];
        for (int i = 0; i < n; i++) {
            newPoints[i] = this.coordinates[i].clone();
        }
        return newPoints;
    }

    public Point2D getCoordinates(int i) {
        return coordinates[i].clone();
    }

    public void setCoordinates(Point2D... coordinates) {
        this.n = coordinates.length;
        this.coordinates = new Point2D[n];
        for (int i = 0; i < n; i++) {
            this.coordinates[i] = coordinates[i].clone();
        }
    }

    public void setCoordinates(Point2D coordinate, int i) {
        this.coordinates[i] = coordinate.clone();
    }

    @Override
    public double length() {
        double result = 0;
        for (int i = 0; i < n - 1; i++) {
            result += Point2D.distance(coordinates[i], coordinates[i + 1]);
        }
        return result;
    }

    @Override
    public Polyline shift(Point2D a) {
        Polyline newLine = this.clone();
        for (int i = 0; i < n; i++) {
            newLine.coordinates[i] = new Point2D(coordinates[i].add(a).getCoordinates());
        }
        return newLine;
    }

    @Override
    public IShape rot(double alpha) {
        Polyline newLine = this.clone();
        for (int i = 0; i < n; i++) {
            newLine.coordinates[i] = coordinates[i].rot(alpha);
        }
        return newLine;
    }

    @Override
    public IShape symAxis(int i) {
        Polyline newLine = this.clone();
        for (int j = 0; j < n; j++) {
            newLine.coordinates[j] = new Point2D(coordinates[j].symaxis(i).getCoordinates());
        }
        return newLine;
    }

    @Override
    public boolean cross(IShape shape) {  //определение пересечения с помощью деления на отрезки и сравнивания их
        if (!(shape instanceof Polyline)) {
            return false;
        } else {
            Polyline lineCross = (Polyline) shape;
            boolean result = false;
            for (int i = 0; i < n - 1; i++) {
                Segment section = new Segment(coordinates[i], coordinates[i + 1]);
                for (int j = 0; j < lineCross.n - 1; j++) {
                    Segment section2 = new Segment(lineCross.coordinates[j], lineCross.coordinates[j + 1]);
                    result = result || section.cross(section2);
                }
            }
            return result;
        }
    }

    @Override
    protected Polyline clone() {
        return new Polyline(this.coordinates);
    }

    @Override
    public String toString() {
        return "Ломаная { размер: " + n + "  координаты: " + Arrays.toString(coordinates) + "}";
    }
}
