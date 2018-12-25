package task1.objects;

public class Point2D extends Point implements Cloneable {

    public Point2D() {
        super(2);
    }

    public Point2D(double... x) {
        super(2, x);
    }

    public double getX() {
        return getCoordinate(0);
    }

    public double getY() {
        return getCoordinate(1);
    }

    public void setX(double x) {
        setCoordinate(0, x);
    }

    public void setY(double y) {
        setCoordinate(1, y);
    }

    /**
     * Поворот точки на угол alpha
     * Положительное направление - против часовой стрелки
     */
    public static Point2D rot(Point2D a, double alpha) {
        double newX = a.getCoordinate(0) * Math.cos(alpha) - a.getCoordinate(1) * Math.sin(alpha);
        double newY = a.getCoordinate(0) * Math.sin(alpha) + a.getCoordinate(1) * Math.cos(alpha);
        return new Point2D(newX, newY);
    }

    public Point2D rot(double alpha) {
        double newX = getX() * Math.cos(alpha) - getY() * Math.sin(alpha);
        double newY = getX() * Math.sin(alpha) + getY() * Math.cos(alpha);
        return new Point2D(newX, newY);
    }

    public static double distance(Point2D p1, Point2D p2) {
        double xd = Math.pow(p2.getX() - p1.getX(), 2);
        double yd = Math.pow(p2.getY() - p1.getY(), 2);
        return Math.sqrt(xd + yd);
    }

    @Override
    protected Point2D clone() {
        Point2D newPoint = new Point2D(getX(), getY());
        return newPoint;
    }

    @Override
    public String toString() {
        return "(" + getX() + " ; " + getY() + ")";
    }
}
