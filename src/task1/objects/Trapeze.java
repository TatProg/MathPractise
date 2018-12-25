package task1.objects;

import java.util.Scanner;

public class Trapeze extends QGon {
    public Trapeze(Point2D... coordinates) {
        super(coordinates);

        Point2D p1 = getCoordinates(0);
        Point2D p2 = getCoordinates(1);
        Point2D p3 = getCoordinates(2);
        Point2D p4 = getCoordinates(3);

        Point2D v1 = new Point2D((p2.sub(p1)).getCoordinates());
        Point2D v2 = new Point2D((p3.sub(p2)).getCoordinates());
        Point2D v3 = new Point2D((p4.sub(p3)).getCoordinates());
        Point2D v4 = new Point2D((p1.sub(p4)).getCoordinates());

        boolean isParallel13 = (v3.getX() / v1.getX() == v3.getY() / v1.getY()) || (v3.getX() == 0) || (v3.getY() == 0);
        boolean isParallel24 = (v4.getX() / v2.getX() == v4.getY() / v2.getY()) || (v4.getX() == 0) || (v4.getY() == 0);
        boolean isDistance13 = Point2D.distance(p1, p2) != Point2D.distance(p3, p4);
        boolean isDistance24 = Point2D.distance(p2, p3) != Point2D.distance(p4, p1);
        Scanner scanner = new Scanner(System.in);

        while (!(isParallel13 && isDistance13) && !(isParallel24 && isDistance24)) {
            System.out.println("Ошибка! Вы ввели не трепецию.\n" +
                    "Введите новые координаты");
            Point2D[] newCoordinates = new Point2D[4];
            for (int i = 0; i < 4; i++) {
                System.out.print((i + 1) + ") Введите x,y: ");
                double x = scanner.nextDouble();
                double y = scanner.nextDouble();
                newCoordinates[i] = new Point2D(x, y);
            }

            p1 = newCoordinates[0];
            p2 = newCoordinates[1];
            p3 = newCoordinates[2];
            p4 = newCoordinates[3];

            v1 = new Point2D((p2.sub(p1)).getCoordinates());
            v2 = new Point2D((p3.sub(p2)).getCoordinates());
            v3 = new Point2D((p4.sub(p3)).getCoordinates());
            v4 = new Point2D((p1.sub(p4)).getCoordinates());

            isParallel13 = v3.getX() / v1.getX() == v3.getY() / v1.getY();
            isParallel24 = v4.getX() / v2.getX() == v4.getY() / v2.getY();
            isDistance13 = Point2D.distance(p1, p2) != Point2D.distance(p3, p4);
            isDistance24 = Point2D.distance(p2, p3) != Point2D.distance(p4, p1);
            setCoordinates(newCoordinates);
        }

    }

    @Override
    public double square() {
        return super.square();
    }

}
