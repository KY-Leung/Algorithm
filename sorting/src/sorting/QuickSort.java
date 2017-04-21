package sorting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class QuickSort {
public static void main(String[] args) {
        System.out.println("------ QUICK SORT ------\n");
        
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
        comparisons = quickSort(arr, 0, (size - 1));
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        
        System.out.println(inputID);
        System.out.println("Time Taken: "+totalTime+" nanoseconds");
        System.out.println("Comparisons: "+comparisons);
        System.out.println("");
    }
    
    public static int quickSort(int[] array, int lowerIndex, int higherIndex) {
        int comparisons = 0;
        int i = lowerIndex;
        int j = higherIndex;
        
        int pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
        
        while (i <= j) {
            while (array[i] < pivot) {
                i++;
                comparisons++;
            }
            while (array[j] > pivot) {
                comparisons++;
                j--;
            }
            if (i <= j) {
                exchangeNumbers(array, i, j);
                comparisons++;
                i++;
                j--;
            }
        }
        
        if (lowerIndex < j) {
            comparisons = comparisons + quickSort(array, lowerIndex, j);
        }
        if (i < higherIndex) {
            comparisons = comparisons + quickSort(array, i, higherIndex);
        }
        
        return comparisons;
    }
 
    public static void exchangeNumbers(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}