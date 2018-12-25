package task1.objects;

import java.util.Scanner;

public class TGon extends NGon {

    public TGon(Point2D... coordinates) {
        super(coordinates);
        if (coordinates.length != 3) {
            Scanner scanner = new Scanner(System.in);
            Point2D[] newCoordinates = new Point2D[3];
            System.out.println("Ошибка! Неверное количество координат!\n" +
                    "Введите координаты заново:");
            for (int i = 0; i < 3; i++) {
                System.out.print((i + 1) + ") Введите x,y: ");
                double x = scanner.nextDouble();
                double y = scanner.nextDouble();
                newCoordinates[i] = new Point2D(x, y);
            }
            setCoordinates(newCoordinates);
        }
    }

    public double square() {
        Point2D v1 = getCoordinates(0);
        Point2D v2 = getCoordinates(1);
        Point2D v3 = getCoordinates(2);

        double a = Point2D.distance(v1, v2);
        double b = Point2D.distance(v2, v3);
        double c = Point2D.distance(v3, v1);
        double p = (a + b + c) / 2;

        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}
