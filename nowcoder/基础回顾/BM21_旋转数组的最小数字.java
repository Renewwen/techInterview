import java.util.ArrayList;

public class Solution {
    // 方法1： 以右边为判断依据，循环写法
    public int minNumberInRotateArray(int [] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] < array[right]) {
                right = mid;
            } else if (array[mid] > array[right]) {
                left = mid + 1;
            } else {
                right--;
            }
        }
        return array[left];
    }

    // 方法2：递归写法
    public int minNumberInRotateArray(int [] array) {
        return minArray(array, 0, array.length - 1);
    }

    private int minArray(int[] array, int left, int right) {
        // 终止条件：只剩一个或两个元素
        if (left + 1 >= right) {
            return Math.min(array[left], array[right]);
        }
        // 如果是排好序的，直接跳过
        if (array[left] < array[right]) {
            return array[left];
        }
        // 如果没排好序，接着二分
        int mid = left + (right - left) / 2;
        return Math.min(minArray(array, left, mid - 1), minArray(array, mid, right));
    }

}
