package task1.objects;

import task1.interfaces.IShape;

public class Circle implements IShape {
    private Point2D center;
    private double radius;

    public Circle(Point2D center, double radius) {
        this.center = center.clone();
        this.radius = radius; //изключение конфликта по ссылкам
    }

    public Point2D getCenter() {
        return center.clone();
    }

    public void setCenter(Point2D center) {
        this.center = center.clone();
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double square() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double length() {
        return 2 * Math.PI * radius;
    }

    @Override
    public IShape shift(Point2D a) { //дополнительная точка
        Point2D newCenter = new Point2D(center.add(a).getCoordinates());
        return new Circle(newCenter, radius);
    }

    @Override
    public IShape rot(double alpha) {
        Point2D newCenter = center.rot(alpha);
        return new Circle(newCenter, radius);
    }

    @Override
    public IShape symAxis(int i) {
        Point2D newCenter = new Point2D(center.symaxis(i).getCoordinates());
        return new Circle(newCenter, radius);
    }

    @Override
    public boolean cross(IShape shape) { //определение пересечения через расстояние между центрами
        if (!(shape instanceof Circle)) {
            return false;
        } else {
            Circle circleCross = (Circle) shape;
            double d = Point2D.distance(this.center, circleCross.center);
            if (d > Math.abs(this.radius + circleCross.radius) || d < Math.abs(this.radius - circleCross.radius)) {
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public String toString() {
        return "Круг {" +
                "центр: " + center +
                ", радиус: " + radius +
                '}';
    }
}
