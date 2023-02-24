import java.util.*;


public class Solution {

    // Method 1: 暴力解 Time:O(n^2)
    // Method 2: 双指针
    // [3, 1, 4, 1, 7, 5, 4]
    //  i                 j
    // Time:O(n)
    // [1,3,1,4,3,1]
    public int maxArea (int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int res = 0;
        while (left < right) {
            int lower = Math.min(height[left], height[right]);
            res = Math.max(res, (right - left) * lower);
            // 如果是左边界
            if (lower == height[left]) {
                while (left < right && lower >= height[left]) {
                    left++;
                }
            } else { // 如果是右边界
                while (left < right && lower >= height[right]) {
                    right--;
                }
            }
        }
        return res;
    }
}
