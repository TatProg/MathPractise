package task1.interfaces;

import task1.objects.Point2D;

public interface IShape {
    double square();

    double length();

    IShape shift(Point2D a);

    IShape rot(double alpha);

    IShape symAxis(int i);

    boolean cross(IShape shape);
}
