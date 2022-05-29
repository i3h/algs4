import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int size;
    private boolean[][] grid;
    private final WeightedQuickUnionUF uf;
    private final int top;
    private final int bottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n must be >= 1");
        }

        size = n;
        grid = new boolean[size][size];
        // initialize union-find data type (including virtual top & bottom)
        uf = new WeightedQuickUnionUF(size * size + 2);
        top = 0;
        bottom = size * size + 1;
    }

    // opens the site (i, j) if it is not open already
    public void open(int i, int j) {
        isIndiceOK(i, j);

        grid[i - 1][j - 1] = true;

        if (i == 1) {
            uf.union(getIndex(i, j), top);
        }
        if (i == size) {
            uf.union(getIndex(i, j), bottom);
        }
        if (i > 1 && isOpen(i - 1, j)) {
            uf.union(getIndex(i, j), getIndex(i - 1, j));
        }
        if (i < size && isOpen(i + 1, j)) {
            uf.union(getIndex(i, j), getIndex(i + 1, j));
        }
        if (j > 1 && isOpen(i, j - 1)) {
            uf.union(getIndex(i, j), getIndex(i, j - 1));
        }
        if (j < size && isOpen(i, j + 1)) {
            uf.union(getIndex(i, j), getIndex(i, j + 1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int i, int j) {
        isIndiceOK(i, j);

        return grid[i - 1][j - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int i, int j) {
        isIndiceOK(i, j);

        return uf.find(top) == uf.find(getIndex(i, j));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (isOpen(i + 1, j + 1)) {
                    count++;
                }
            }
        }

        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(top) == uf.find(bottom);
    }

    private int getIndex(int i, int j) {
        return size * (i - 1) + j;
    }

    private void isIndiceOK(int i, int j) {
        if (i < 1 || i > size || j < 1 || j > size) {
            throw new IllegalArgumentException("Indices must be in the range of [1, n]");
        }
    }
}