package task1.objects;

import task1.interfaces.IShape;

import java.util.Arrays;

public class NGon implements IShape {

    private int n;
    private Point2D[] coordinates;

    public NGon(Point2D... coordinates) {
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
        Point2D[] newCoordinates = new Point2D[n];
        for (int i = 0; i < n; i++) {
            newCoordinates[i] = coordinates[i].clone();
        }
        return newCoordinates;
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
    public double square() { //х умножается со след у, у умножается со след х, эти числа вычисляются и деляться по полам
        double xy = 0;
        double yx = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1 == n ? 0 : i + 1;
            xy += coordinates[i].getX() * coordinates[j].getY();
            yx += coordinates[i].getY() * coordinates[j].getX();
        }

        return Math.abs((xy - yx) / 2);
    }

    @Override
    public double length() {
        double result = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1 == n ? 0 : i + 1;
            result += Point2D.distance(coordinates[i], coordinates[j]);
        }
        return result;
    }

    @Override
    public IShape shift(Point2D a) {
        NGon newNGon = this.clone();
        for (int i = 0; i < n; i++) {
            newNGon.coordinates[i] = new Point2D(coordinates[i].add(a).getCoordinates());
        }
        return newNGon;
    }

    @Override
    public IShape rot(double alpha) {
        NGon newNGon = this.clone();
        for (int i = 0; i < n; i++) {
            newNGon.coordinates[i] = coordinates[i].rot(alpha);
        }
        return newNGon;
    }

    @Override
    public IShape symAxis(int i) {
        NGon newNGon = this.clone();
        for (int j = 0; j < n; j++) {
            newNGon.coordinates[j] = new Point2D(coordinates[j].symaxis(i).getCoordinates());
        }
        return newNGon;
    }

    @Override
    public boolean cross(IShape shape) { //определение пересечения с помощью деления на отрезки и сравнивания их
        if (!(shape instanceof NGon)) {
            return false;
        } else {
            NGon shape2 = (NGon) shape;
            boolean result = false;
            for (int i = 0; i < n; i++) {
                int j = i + 1 == n ? 0 : i + 1;
                Segment section = new Segment(coordinates[i], coordinates[j]);
                for (int z = 0; z < shape2.n; z++) {
                    int w = z + 1 == shape2.n ? 0 : z + 1;
                    Segment section2 = new Segment(shape2.coordinates[z], shape2.coordinates[w]);
                    result = result || section.cross(section2);
                }
            }
            return result;
        }
    }

    @Override
    protected NGon clone() {
        return new NGon(this.coordinates);
    }

    @Override
    public String toString() {
        return "Многоугольник { размер: " + n +
                ", координаты: " + Arrays.toString(coordinates) + "}";
    }
}
