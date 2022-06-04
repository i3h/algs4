import java.util.Arrays;

public class FastCollinearPoints {
    private int num;
    private LineSegment[] segments;
    private LineSegment[] segmentsCache;

    public FastCollinearPoints(Point[] points) {
        checkNullPoints(points);
        checkDuplicatedPoints(points);

        num = 0;
        segmentsCache = new LineSegment[1];

        int n = points.length;
        for (int i = 0; i < n; i++)
            checkSegment(points, i);

        segments = new LineSegment[num];
        System.arraycopy(segmentsCache, 0, segments, 0, num);
    }

    private void checkSegment(Point[] points, int i) {
        int n = points.length;
        Point p = points[i];
        Point[] pointsCopy = new Point[n];
        System.arraycopy(points, 0, pointsCopy, 0, n);
        Arrays.sort(pointsCopy, 0, n, p.slopeOrder());

        int count = 0;
        int cur = -1;
        boolean found = false;
        double slope = Double.NEGATIVE_INFINITY;
        for (int j = 0; j < n; j++) {
            if (p.compareTo(pointsCopy[j]) == 0)
                continue;

            double tmp = p.slopeTo(pointsCopy[j]);
            if (tmp != slope) {
                slope = tmp;
                if (found) {
                    segmentsCache[num] = new LineSegment(p, pointsCopy[cur]);
                    num++;
                    if (num == segmentsCache.length)
                        resizeSegmentsCache(num * 2);

                    count = 0;
                    cur = -1;
                    found = false;
                }
            } else {
                count++;
                if (count > 1) {
                    found = true;
                    cur = j;
                }
            }
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