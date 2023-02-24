import java.util.*;


public class Solution {

    public String solve (String str) {
        if (str == null || str == "") {
            return "";
        }
        char[] arr = str.toCharArray();
        reverse(arr);
        return new String(arr);
    }

    private void reverse(char[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }

    private void swap(char[] arr, int left, int right) {
        char tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }
}