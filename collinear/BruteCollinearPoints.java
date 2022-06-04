import java.util.Arrays;

public class BruteCollinearPoints {
    private int num;
    private LineSegment[] segments;
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
                    for (int l = k + 1; l < n; l++)
                        checkSegment(new Point[]{points[i], points[j], points[k], points[l]});

        if (num == 0)
            return;
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
        LineSegment[] new_segments = new LineSegment[segments.length];
        System.arraycopy(segments, 0, new_segments, 0, segments.length);
        return new_segments;
    }

    private void checkNullPoints(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException();
        }
    }

    private void checkDuplicatedPoints(Point[] points) {
        Point[] new_points = new Point[points.length];
        System.arraycopy(points, 0, new_points, 0, points.length);
        Arrays.sort(new_points);
        Point k = new_points[0];
        for (int i = 1; i < points.length; i++) {
            if (k.compareTo(new_points[i]) == 0)
                throw new IllegalArgumentException();
            k = new_points[i];
        }
    }
}