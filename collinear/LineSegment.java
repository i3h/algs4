public class LineSegment {
    private final Point p, q;

    public LineSegment(Point p, Point q) {
        if (p == null || q == null)
            throw new NullPointerException();

        this.p = p;
        this.q = q;
    }

    public void draw() {
        p.drawTo(q);
    }

    public String toString() {
        return p + " -> " + q;
    }
}