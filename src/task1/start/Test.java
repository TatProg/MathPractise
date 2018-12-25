package task1.start;

import task1.interfaces.IShape;
import task1.objects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Test {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<IShape> list = new ArrayList<>();
        List<Integer> shapeTypes = new ArrayList<>();

        System.out.print("Введите количество фигур: ");
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "  Выберите тип фигуры:\n" +
                    "    1)Отрезок\n" +
                    "    2)Ломаная\n" +
                    "    3)Круг\n" +
                    "    4)Треугольник\n" +
                    "    5)Четырехугольник\n" +
                    "    6)Прямоугольник\n" +
                    "    7)Трапеция\n" +
                    "    8)Многоугольник");
            int type = scanner.nextInt();
            shapeTypes.add(type);
            list.add(createShape(type));
        }

        double sumArea = 0;
        double sumLength = 0;

        for (IShape shape : list) {
            sumArea += shape.square();
            sumLength += shape.length();
        }
        double averageArea = sumArea / list.size();
        System.out.println("Суммарная площадь: " + sumArea + "\n" +
                "Суммарная длина: " + sumLength + "\n" +
                "Средняя площадь: " + averageArea);
        System.out.println("-----------------------------------");
        List<IShape> list2 = new ArrayList<>();
        IntStream.range(0, n).forEachOrdered(i -> {
            list2.add(createShape(shapeTypes.get(i)));
            System.out.println(list.get(i).cross(list2.get(i)) ? "Фигуры пересекаются" : "Фигуры не пересекаются");
        });

        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "  Выберите тип движения:\n" +
                    "    1)Поворот\n" +
                    "    2)Сдвиг\n" +
                    "    3)Симметрия");
            int type = scanner.nextInt();

            IShape newShape;
            switch (type) {
                case 1:
                    System.out.println("Введите угол поворота: ");
                    double alpha = scanner.nextDouble();
                    newShape = list2.get(i).rot(alpha);
                    System.out.println(list.get(i).cross(newShape) ? "Фигуры пересекаются" : "Фигуры не пересекаются");
                    break;
                case 2:
                    System.out.println("Введите координаты вектора: ");
                    double x = scanner.nextDouble();
                    double y = scanner.nextDouble();
                    newShape = list2.get(i).shift(new Point2D(x, y));
                    System.out.println(list.get(i).cross(newShape) ? "Фигуры пересекаются" : "Фигуры не пересекаются");
                    break;
                case 3:
                    System.out.println("Введите номер оси (1 или 2): ");
                    int j = scanner.nextInt();
                    newShape = list2.get(i).symAxis(j - 1);
                    System.out.println(list.get(i).cross(newShape) ? "Фигуры пересекаются" : "Фигуры не пересекаются");
                    break;
            }

        }
    }

    public static IShape createShape(int type) {
        IShape shape = null;
        Point2D[] coordinates;
        switch (type) {
            case 1:
                System.out.println("-----Создание отрезка-----");
                coordinates = readCoordinates(2);
                shape = new Segment(coordinates[0], coordinates[1]);
                break;
            case 2:
                System.out.println("-----Создание ломанной-----");
                System.out.print("Введите количество точек ломанной: ");
                int n = scanner.nextInt();
                coordinates = readCoordinates(n);
                shape = new Polyline(coordinates);
                break;
            case 3:
                System.out.println("-------Создание круга-------");
                System.out.print("Введите координаты центра: ");
                double x = scanner.nextDouble();
                double y = scanner.nextDouble();
                System.out.print("Введите значение радиуса: ");
                double radius = scanner.nextDouble();
                shape = new Circle(new Point2D(x, y), radius);
                break;
            case 4:
                System.out.println("----Создание треугольника----");
                coordinates = readCoordinates(3);
                shape = new TGon(coordinates);
                break;
            case 5:
                System.out.println("----Создание четырехугольника----");
                coordinates = readCoordinates(4);
                shape = new QGon(coordinates);
                break;
            case 6:
                System.out.println("----Создание прямоугольника----");
                coordinates = readCoordinates(4);
                shape = new Rectangle(coordinates);
                break;
            case 7:
                System.out.println("-----Создание трапеции-----");
                coordinates = readCoordinates(4);
                shape = new Trapeze(coordinates);
                break;
            case 8:
                System.out.println("-----Создание многоугольника------");
                System.out.print("Введите количество вершин: 1");
                int c = scanner.nextInt();
                coordinates = readCoordinates(c);
                shape = new NGon(coordinates);
                break;
        }
        System.out.println("--------------------------");
        return shape;
    }

    public static Point2D[] readCoordinates(int n) {
        Point2D[] coordinates = new Point2D[n];
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + ")Введите координаты x и y: ");
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            coordinates[i] = new Point2D(x, y);
        }
        return coordinates;
    }
}
