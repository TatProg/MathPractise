package task1.objects;

import java.util.Scanner;

public class QGon extends NGon {

    public QGon(Point2D[] coordinates) {
        super(coordinates);
        if (coordinates.length != 4) {
            Scanner scanner = new Scanner(System.in);
            Point2D[] newCoordinates = new Point2D[4];
            System.out.println("Ошибка! Неверное количество координат!\n" +
                    "Введите координаты заново:");
            for (int i = 0; i < 4; i++) {
                System.out.print((i + 1) + ") Введите x,y: ");
                double x = scanner.nextDouble();
                double y = scanner.nextDouble();
                newCoordinates[i] = new Point2D(x, y);
            }
            setCoordinates(newCoordinates);
        }
    }

    public double square() {

        return super.square();
    }
}
