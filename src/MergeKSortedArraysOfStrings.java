import java.io.*;
import java.util.*;

public class MergeKSortedArraysOfStrings {
    private static class HeapNodeString
            implements Comparable<MergeKSortedArraysOfStrings.HeapNodeString> {
        int x;
        int y;
        String value;

        HeapNodeString(int x, int y, String value)
        {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override public int compareTo(MergeKSortedArraysOfStrings.HeapNodeString hn)
        {
            if (this.value.compareTo(hn.value) <= 0) {
                return -1;
            }
            else {
                return 1;
            }
        }
    }

    // Function to merge k sorted arrays.
    public static ArrayList<String>
    mergeKArrays(String[][] arr)
    {
        ArrayList<String> result
                = new ArrayList<>();
        PriorityQueue<MergeKSortedArraysOfStrings.HeapNodeString> heap
                = new PriorityQueue<HeapNodeString>();

        // Initially add only first column of elements. First element of every array
        for (int i = 0; i < arr.length; i++) {
            heap.add(new MergeKSortedArraysOfStrings.HeapNodeString(i, 0, arr[i][0]));
        }

        MergeKSortedArraysOfStrings.HeapNodeString curr = null;
        while (!heap.isEmpty()) {
            curr = heap.poll();
            result.add(curr.value);

            // Check if next element of curr min exists, then add that to heap
            if (curr.y < (arr[curr.x].length - 1)) {
                heap.add(
                        new MergeKSortedArraysOfStrings.HeapNodeString(curr.x, curr.y + 1,
                                arr[curr.x][curr.y + 1]));
            }
        }
        return result;
    }
}
