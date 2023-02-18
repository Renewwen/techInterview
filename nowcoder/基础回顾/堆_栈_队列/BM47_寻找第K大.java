import java.util.*;

public class Solution {

    public int findKth(int[] a, int n, int K) {
        if (a == null || a.length == 0) {
            return -1;
        }
        int left = 0;
        int right = n - 1;
        int target = n - K;
        while (left <= right) {
            int index = partition(a, left, right);
            if (index == target) {
                return a[index];
            } else if (index > target) {
                // [start...target...index...end]
                right = index - 1;
            } else {
                // [start...index...target...end]
                left = index + 1;
            }
        }
        return -1;
    }

    private int partition(int[] a, int left, int right) {
        Random rnd = new Random();
        int index = left + rnd.nextInt(right - left + 1);
        swap(a, index, right);
        int i = left;
        int j = right - 1;
        while (i <= j) {
            // 此处不能 a[i] <= a[right], 因为最后换位的时候, 要保证和a[index]相同的都在右边
            if (a[i] < a[right]) {
                i++;
            } else {
                swap(a, i, j);
                j--;
            }
        }
        swap(a, i, right);
        return i;
    }

    private void swap(int[] array, int left, int right) {
        int tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }
}