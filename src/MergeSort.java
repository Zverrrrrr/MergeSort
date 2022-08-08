import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* Java program for Merge Sort */
class MergeSort
{
    // Merges two subArrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    void merge(int arr[], int l, int m, int r)
    {

        // Find sizes of two subArrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int [n1];
        int R[] = new int [n2];

        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    void sort(int arr[], int l, int r)
    {
        if (l < r)
        {
            // Find the middle point
            int m = (l+r)/2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr , m+1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    /* A utility function to print array of size n */
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    static List<Integer> readFile(String fileName) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
            List<Integer> tall = new ArrayList<Integer>();
            while (scanner.hasNextInt()) {
                tall.add(scanner.nextInt());
            }
            return tall;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("FileNotFoundException");
        }

        return null;
    }

    // Driver method
    public static void main(String args[])
    {
        int[] intArr;
        List<Integer> integerList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String fileName = reader.readLine();
            intArr =  readFile(fileName).stream().mapToInt(i -> i).toArray();
            System.out.println("Given Array");
            printArray(intArr);

            MergeSort ob = new MergeSort();
            ob.sort(intArr, 0, intArr.length-1);

            System.out.println("\nSorted array");
            printArray(intArr);



        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
