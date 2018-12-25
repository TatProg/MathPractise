package task1.objects;

import java.util.Arrays;
import java.util.Scanner;

public class Point {
    private int dim;
    private double[] coordinates;

    public Point(int dim) {
        this.dim = dim;
        this.coordinates = new double[dim];
    }

    public Point(int dim, double... coordinates) {
        this.dim = dim;
        if (dim < coordinates.length) {
            System.out.println("--Ошибка! Размерность меньше количества координат, размерность была уменьшена (Размерность = " + dim + ")--");
            this.coordinates = new double[dim];
            System.arraycopy(coordinates, 0, this.coordinates, 0, dim);
        }
        Scanner scanner = new Scanner(System.in);
        if (dim > coordinates.length) {
            System.out.println("--Ошибка! Неверное количество координат!--\n" +
                    "Введите координаты заново:\n");
            this.coordinates = new double[dim];
            for (int i = 0; i < dim; i++) {
                System.out.print("Введите " + (i + 1) + "ю координату: ");
                double x = scanner.nextDouble();
                this.coordinates[i] = x;
            }
        }
        if (dim == coordinates.length) {
            this.coordinates = new double[dim];
            System.arraycopy(coordinates, 0, this.coordinates, 0, dim);
        }
    }

    public int getDim() {
        return dim;
    }

    public double[] getCoordinates() {
        return coordinates.clone();
    }

    public double getCoordinate(int i) {
        return coordinates[i];
    }

    public void setCoordinates(double... coordinates) {
        double[] newCoordinates = new double[coordinates.length];
        System.arraycopy(coordinates, 0, newCoordinates, 0, coordinates.length);
        this.dim = coordinates.length;
        this.coordinates = newCoordinates;
    }

    public void setCoordinate(int i, double x) {
        this.coordinates[i] = x;
    }

    /**
     * Длина от точки до начала координат (модуль точки)
     */
    public double abs() {
        double result = 0;
        for (double a : coordinates) {
            result += Math.pow(a, 2);
        }
        return Math.sqrt(result);
    }

    /**
     * Сложение точек
     */
    public static Point add(Point a, Point b) {
        if (a.coordinates.length != b.coordinates.length) {
            System.out.println("Точки имеют разное количество координат");
            return a;
        }

        Point resultPoint = new Point(a.dim, a.coordinates);
        for (int i = 0; i < b.coordinates.length; i++) {
            resultPoint.coordinates[i] += b.coordinates[i];
        }

        return resultPoint;
    }

    public Point add(Point b) {
        if (coordinates.length != b.coordinates.length) {
            System.out.println("Точки имеют разное количество координат");
            return this;
        }

        Point resultPoint = new Point(dim, coordinates);
        for (int i = 0; i < b.coordinates.length; i++) {
            resultPoint.coordinates[i] += b.coordinates[i];
        }

        return resultPoint;
    }

    /**
     * Разность точек
     */
    public static Point sub(Point a, Point b) {
        if (a.coordinates.length != b.coordinates.length) {
            System.out.println("Точки имеют разное количество координат");
            return a;
        }

        Point resultPoint = new Point(a.dim, a.coordinates);
        for (int i = 0; i < b.coordinates.length; i++) {
            resultPoint.coordinates[i] -= b.coordinates[i];
        }

        return resultPoint;
    }

    public Point sub(Point b) {
        if (coordinates.length != b.coordinates.length) {
            System.out.println("Точки имеют разное количество координат");
            return this;
        }

        Point resultPoint = new Point(dim, coordinates);
        for (int i = 0; i < b.coordinates.length; i++) {
            resultPoint.coordinates[i] -= b.coordinates[i];
        }

        return resultPoint;
    }

    /**
     * Умножение точки на число
     */
    public static Point mult(Point a, double r) {
        Point resultPoint = new Point(a.dim, a.coordinates);
        for (int i = 0; i < resultPoint.coordinates.length; i++) {
            resultPoint.coordinates[i] *= r;
        }

        return resultPoint;
    }

    public Point mult(double r) {
        Point resultPoint = new Point(dim, coordinates);
        for (int i = 0; i < resultPoint.coordinates.length; i++) {
            resultPoint.coordinates[i] *= r;
        }

        return resultPoint;
    }

    /**
     * Умножение точки на точку
     */
    public static double mult(Point a, Point b) {
        double result = 0;
        if (a.coordinates.length != b.coordinates.length) {
            System.out.println("Точки имеют разное количество координат");
            return 0;
        }

        for (int i = 0; i < a.coordinates.length; i++) {
            result += (a.coordinates[i] * b.coordinates[i]);
        }
        return result;
    }

    public double mult(Point b) {
        double result = 0;
        if (coordinates.length != b.coordinates.length) {
            System.out.println("Точки имеют разное количество координат");
            return 0;
        }

        for (int i = 0; i < coordinates.length; i++) {
            result += (coordinates[i] * b.coordinates[i]);
        }
        return result;
    }

    /**
     * Симметрия относительно оси под номером i
     */
    public static Point symaxis(Point a, int i) {
        if (i < 0 || i > a.coordinates.length) {
            System.out.println("Нет оси с таким номером");
            return a;
        }
        Point resultPoint = new Point(a.dim, a.coordinates);
        resultPoint.coordinates[i] = -resultPoint.coordinates[i];
        return resultPoint;
    }

    public Point symaxis(int i) {
        if (i < 0 || i > coordinates.length) {
            System.out.println("Нет оси с таким номером");
            return this;
        }
        Point resultPoint = new Point(dim, coordinates);
        for (int j = 0; j < dim; j++) {
            if (j != i) {
                resultPoint.coordinates[j] = -resultPoint.coordinates[j];
            }
        }
        return resultPoint;
    }

    @Override
    public String toString() {
        return "Точка {" +
                "размер: " + dim +
                ", координаты: " + Arrays.toString(coordinates) +
                '}';
    }
}
