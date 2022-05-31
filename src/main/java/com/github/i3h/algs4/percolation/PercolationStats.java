import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    private final double CONFIDENCE_95 = 1.96;
    private final double[] experiments;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int t) {
        if (n < 1 || t < 1) {
            throw new IllegalArgumentException("n and t must be >= 1");
        }

        experiments = new double[t];

        for (int k = 0; k < t; k++) {
            Percolation percolation = new Percolation(n);
            long counter = 0;
            while (!percolation.percolates()) {
                int i = StdRandom.uniform(n) + 1;
                int j = StdRandom.uniform(n) + 1;

                if (!percolation.isOpen(i, j)) {
                    percolation.open(i, j);
                    counter++;
                }
            }
            experiments[k] = (double) counter / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {

        return StdStats.mean(experiments);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {

        return StdStats.stddev(experiments);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {

        return mean() - ((CONFIDENCE_95 * stddev()) / Math.sqrt(experiments.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {

        return mean() + ((CONFIDENCE_95 * stddev()) / Math.sqrt(experiments.length));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, t);

        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + "[" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }

}