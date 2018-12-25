package task1.objects;

import java.util.Arrays;
import java.util.Scanner;

public class Rectangle extends QGon {

    public Rectangle(Point2D... coordinates) {
        super(coordinates);


        Point2D v1 = getCoordinates(0);
        Point2D v2 = getCoordinates(1);
        Point2D v3 = getCoordinates(2);
        Point2D v4 = getCoordinates(3);

        double d1 = Point2D.distance(v1, v3);
        double d2 = Point2D.distance(v2, v4);
        Scanner scanner = new Scanner(System.in);
        while ((d1 != d2) && (v1 != v3) && (v2 != v4)) {
            System.out.println("Ошибка! Вы ввели не прямоугольник.\n" +
                    "Введите новые координаты");
            Point2D[] newCoordinates = new Point2D[4];
            for (int i = 0; i < 4; i++) {
                System.out.print((i + 1) + ") Введите x,y: ");
                double x = scanner.nextDouble();
                double y = scanner.nextDouble();
                newCoordinates[i] = new Point2D(x, y);
            }
            v1 = newCoordinates[0];
            v2 = newCoordinates[1];
            v3 = newCoordinates[2];
            v4 = newCoordinates[3];
            d1 = Point2D.distance(v1, v3);
            d2 = Point2D.distance(v2, v4);
            setCoordinates(newCoordinates);
        }
    }

    @Override
    public double square() {
        Point2D v1 = getCoordinates(0);
        Point2D v2 = getCoordinates(1);
        Point2D v3 = getCoordinates(2);

        double a = Point2D.distance(v1, v2);
        double b = Point2D.distance(v2, v3);

        return a * b;
    }
}
