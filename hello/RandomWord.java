import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = "";
        String candidate = "";
        int i = 0;

        while (!StdIn.isEmpty()) {
            candidate = StdIn.readString();
            if (i == 0 || StdRandom.bernoulli(1 / (double) i)) {
                champion = candidate;
            }
            i++;
        }

        StdOut.println(champion);
    }
}