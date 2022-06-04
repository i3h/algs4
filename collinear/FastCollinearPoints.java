import java.util.Arrays;

public class FastCollinearPoints {
    private final LineSegment[] segments;
    private int num;
    private LineSegment[] segmentsCache;

    public FastCollinearPoints(Point[] points) {
        checkNullPoints(points);
        checkDuplicatedPoints(points);

        int n = points.length;
        Point[] pointsCopy = new Point[n];
        System.arraycopy(points, 0, pointsCopy, 0, n);
        Arrays.sort(pointsCopy);

        num = 0;
        segmentsCache = new LineSegment[n];

        for (int i = 0; i < n - 3; i++) {
            Point p = pointsCopy[i];
            Point[] sortedPoints = new Point[n];  // content length = n - 1, last = null
            System.arraycopy(pointsCopy, 0, sortedPoints, 0, i);
            System.arraycopy(pointsCopy, i + 1, sortedPoints, i, n - i - 1);
            Arrays.sort(sortedPoints, 0, n - 1, p.slopeOrder());

            int currentStartIndex = 0;
            for (int j = 1; j < n; j++) {
                Point currentStartPoint = sortedPoints[currentStartIndex];
                Point currentPoint = sortedPoints[j];
                if (currentPoint == null || currentPoint.slopeTo(p) != currentStartPoint.slopeTo(p)) {
                    int len = j - currentStartIndex;
                    if (len >= 3 && currentStartPoint.compareTo(p) > 0) {
                        segmentsCache[num] = new LineSegment(p, sortedPoints[j - 1]);
                        num++;
                        if (num == segmentsCache.length) {
                            resizeSegmentsCache(num * 2);
                        }
                    }
                    currentStartIndex = j;
                }
            }
        }

        //
        segments = new LineSegment[num];
        System.arraycopy(segmentsCache, 0, segments, 0, num);
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