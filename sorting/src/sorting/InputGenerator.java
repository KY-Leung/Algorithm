package sorting;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class InputGenerator {
    public static void main(String[] args) {
        int i, size;

        FileOutputStream out;
        PrintStream p;

        Random rand = new Random();

        try {
            for (size = 2000; size <= 10000; size = size + 2000) {
                out = new FileOutputStream("/Users/Leung/Documents/Developer/CZ2001/Lab 3/rand/rand_num_" + size + ".txt");
                p = new PrintStream(out);

                for (int j = 1; j <= size; j++) {
                    i = rand.nextInt((size - 1) + 1) + 1;
                    p.println(i);
                }

                p.close();
            }

            for (size = 2000; size <= 10000; size = size + 2000) {
                out = new FileOutputStream("/Users/Leung/Documents/Developer/CZ2001/Lab 3/rand/ascending_" + size + ".txt");
                p = new PrintStream(out);

                for (int j = 1; j <= size; j++) {
                    p.println(j);
                }

                p.close();
            }

            for (size = 2000; size <= 10000; size = size + 2000) {
                out = new FileOutputStream("/Users/Leung/Documents/Developer/CZ2001/Lab 3/rand/descending_" + size + ".txt");
                p = new PrintStream(out);

                for (int j = size; j >= 1; j--) {
                    p.println(j);
                }

                p.close();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
