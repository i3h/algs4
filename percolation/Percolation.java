import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int size;
    private boolean[][] grid;
    private WeightedQuickUnionUF uf;
    private int top;
    private int bottom;

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
        grid[i][j] = true;

        if (i == 0) {
            uf.union(getIndex(i, j), top);
        }
        if (i == size - 1) {
            uf.union(getIndex(i, j), bottom);
        }
        if (i > 0 && isOpen(i - 1, j)) {
            uf.union(getIndex(i, j), getIndex(i - 1, j));
        }
        if (i < size - 1 && isOpen(i + 1, j)) {
            uf.union(getIndex(i, j), getIndex(i + 1, j));
        }
        if (j > 0 && isOpen(i, j - 1)) {
            uf.union(getIndex(i, j), getIndex(i, j - 1));
        }
        if (j < size - 1 && isOpen(i, j + 1)) {
            uf.union(getIndex(i, j), getIndex(i, j + 1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int i, int j) {
        return grid[i][j];
    }

    // is the site (row, col) full?
    public boolean isFull(int i, int j) {
        if (i >= 0 && i < size && j >= 0 && j < size) {
            return uf.find(top) == uf.find(getIndex(i, j));
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (isOpen(i, j)) {
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
        return size * i + j + 1;
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}