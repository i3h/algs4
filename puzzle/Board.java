public class Board {
    private int[][] tiles;
    private int N;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        N = tiles.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    // unit testing (not graded)
    public static void main(String[] args) {

    }

    // string representation of this board
    public String toString() {
        String s = Integer.toString(N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s += " " + tiles[i][j];
            }
            s += "\n";
        }
        return s;
    }

    // board dimension n
    public int dimension() {
        return N;
    }

    // number of tiles out of place
    public int hamming() {

    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {

    }

    // is this board the goal board?
    public boolean isGoal() {

    }

    // does this board equal y?
    public boolean equals(Object y) {

    }

    // all neighboring boards
    public Iterable<Board> neighbors() {

    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {

    }
}