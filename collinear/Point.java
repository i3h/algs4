import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {
    private final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public int compareTo(Point that) {
        if (y == that.y && x == that.x)
            return 0;

        if (y < that.y || y == that.y && x < that.x)
            return -1;

        return 1;
    }

    public double slopeTo(Point that) {
        if (that == null)
            throw new NullPointerException();
        if (x == that.x) {
            if (y == that.y) {
                return Double.NEGATIVE_INFINITY;
            }
            return Double.POSITIVE_INFINITY;
        }
        if (y == that.y) {
            return 0.0;
        }
        return (double) (that.y - this.y) / (that.x - this.x);
    }

    public Comparator<Point> slopeOrder() {
        return new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                double s1 = slopeTo(o1);
                double s2 = slopeTo(o2);
                if (s1 < s2)
                    return -1;
                if (s1 > s2)
                    return 1;
                return 0;
            }
        };
    }
}