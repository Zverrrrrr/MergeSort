import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MergeKSortedArrays {
    private static class HeapNode
            implements Comparable<HeapNode> {
        int x;
        int y;
        int value;

        HeapNode(int x, int y, int value)
        {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override public int compareTo(HeapNode hn)
        {
            if (this.value <= hn.value) {
                return -1;
            }
            else {
                return 1;
            }
        }
    }

    // Function to merge k sorted arrays.
    public static ArrayList<Integer>
    mergeKArrays(Integer[][] arr, int K)
    {
        ArrayList<Integer> result
                = new ArrayList<Integer>();
        PriorityQueue<HeapNode> heap
                = new PriorityQueue<HeapNode>();

        // Initially add only first column of elements. First
        // element of every array
        for (int i = 0; i < arr.length; i++) {
            heap.add(new HeapNode(i, 0, arr[i][0]));
        }

        HeapNode curr = null;
        while (!heap.isEmpty()) {
            curr = heap.poll();
            result.add(curr.value);

            // Check if next element of curr min exists,
            // then add that to heap.
            if (curr.y < (arr[curr.x].length - 1)) {
                heap.add(
                        new HeapNode(curr.x, curr.y + 1,
                                arr[curr.x][curr.y + 1]));
            }
        }

        return result;
    }

    static ArrayList<Integer> readFile(String fileName) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
            List<Integer> tall = new ArrayList<Integer>();
            while (scanner.hasNextInt()) {
                tall.add(scanner.nextInt());
            }
            return (ArrayList<Integer>) tall;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("FileNotFoundException");
        }

        return null;
    }

    public static void main(String[] args)
    {

        int[][] arr = { { 2, 6, 12 },
                { 1, 9, 67, 99, 809},
                { 23, 34, 90, 2000 },
                {1, 67, 99, 100, 777}};

        List<ArrayList<Integer>> integerList = new ArrayList<>();


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String inputString = reader.readLine();
            String[] input = inputString.split(", ");
            for(String str: input) {
                integerList.add(readFile(str));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Integer[][] integerArray = integerList.stream().map(u -> u.toArray(new Integer[0])).toArray(Integer[][]::new);


        System.out.println(
                MergeKSortedArrays.mergeKArrays(integerArray, integerArray.length)
                        .toString());
    }
}