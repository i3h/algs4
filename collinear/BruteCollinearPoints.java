import java.util.Arrays;

public class BruteCollinearPoints {
    private final LineSegment[] segments;
    private int num;
    private LineSegment[] segmentsCache;

    public BruteCollinearPoints(Point[] points) {
        checkNullPoints(points);
        checkDuplicatedPoints(points);

        int n = points.length;
        num = 0;
        segmentsCache = new LineSegment[n];

        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                for (int k = j + 1; k < n; k++)
                    for (int x = k + 1; x < n; x++)
                        checkSegment(new Point[]{points[i], points[j], points[k], points[x]});

        segments = new LineSegment[num];
        System.arraycopy(segmentsCache, 0, segments, 0, num);
    }

    private void checkSegment(Point[] points) {
        Point p = points[0];
        Point q = points[1];
        Point r = points[2];
        Point s = points[3];
        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(r) == p.slopeTo(s)) {
            Point[] tuple = new Point[]{p, q, r, s};
            Arrays.sort(tuple);
            segmentsCache[num] = new LineSegment(tuple[0], tuple[3]);
            num++;
            if (num == segmentsCache.length)
                resizeSegmentsCache(num * 2);
        }
    }

    private void resizeSegmentsCache(int capacity) {
        if (capacity <= segmentsCache.length)
            return;

        LineSegment[] tmp = new LineSegment[capacity];
        System.arraycopy(segmentsCache, 0, tmp, 0, num);
        segmentsCache = tmp;
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        System.out.println(segments.length);
        LineSegment[] newSegments = new LineSegment[segments.length];
        System.arraycopy(segments, 0, newSegments, 0, segments.length);
        return newSegments;
    }


    private void checkNullPoints(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException();
        }
    }

    private void checkDuplicatedPoints(Point[] points) {
        Point[] newPoints = new Point[points.length];
        System.arraycopy(points, 0, newPoints, 0, points.length);
        Arrays.sort(newPoints);
        Point k = newPoints[0];
        for (int i = 1; i < points.length; i++) {
            if (k.compareTo(newPoints[i]) == 0)
                throw new IllegalArgumentException();
            k = newPoints[i];
        }
    }
}