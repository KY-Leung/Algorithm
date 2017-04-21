package sorting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MergeSort {
    public static void main(String[] args) {
        System.out.println("------ MERGE SORT ------\n");
        
        for (int i = 0; i < 3; i++) {
            for (int j = 1; j <= 5; j++) {
                convertToArray(i, (j * 2000));
            }
        }
    }

    public static void convertToArray(int i, int size) {
        String filename, inputID = "";
        String dir = "/Users/Leung/Documents/Developer/CZ2001/Lab 3/rand";
        int index = 0;
        int[] arr = new int[size];
        int comparisons = 0;

        switch (i) {
            case 0:
                filename = dir + "rand_num_" + size + ".txt";
                inputID = "Random Numbers. Size: "+size;
                break;
            case 1:
                filename = dir + "ascending_" + size + ".txt";
                inputID = "Ascending Numbers. Size: "+size;
                break;
            case 2:
                filename = dir + "descending_" + size + ".txt";
                inputID = "Descending Numbers. Size: "+size;
                break;
            default:
                filename = "error.txt";
        }

        try {
            BufferedReader read = new BufferedReader(new FileReader(filename));
            String line = read.readLine();

            while (line != null) {
                arr[index] = Integer.parseInt(line);
                line = read.readLine();
                index++;
            }

            read.close();
        } catch (IOException iox) {
            System.out.println("Problem reading " + filename);
        }

        long startTime = System.nanoTime();
        comparisons = mergeSort(arr, 0, (size - 1));
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        
        System.out.println(inputID);
        System.out.println("Time Taken: "+totalTime+" nanoseconds");
        System.out.println("Comparisons: "+comparisons);
        System.out.println("");
    }

    public static int mergeSort(int[] arr, int start, int end) {
        int comparisons = 0;
        int mid = (start + end) / 2;

        if (end - start <= 0) {
            return 0;
        } else if ((end - start) > 0) {
            mergeSort(arr, start, mid);
            mergeSort(arr, (mid + 1), end);
            comparisons = comparisons + merge(arr, start, mid, end);
        }
        
        return comparisons;
    }

    public static int merge(int[] arr, int start, int mid, int end) {
        int a = start;
        int b = mid + 1;
        int i, tmp, comparisons = 0;

        if (end - start <= 0) {
            return 0;
        }

        while ((a <= mid) && (b <= end)) {
            if (arr[a] > arr[b]) {
                tmp = arr[b++];

                for (i = ++mid; i > a; i--) {
                    arr[i] = arr[i - 1];

                }

                arr[a++] = tmp;
            } else if (arr[a] < arr[b]) {
                a++;
            } else {
                if ((a == mid) && (b == end)) {
                    break;
                }

                tmp = arr[b++];
                a++;

                for (i = ++mid; i > a; i--) {
                    arr[i] = arr[i - 1];
                }

                arr[a++] = tmp;
            }
            comparisons++;
        }

        return comparisons;
    }
}
