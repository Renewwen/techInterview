import java.util.*;

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param nums int整型一维数组
     * @return int整型
     */
    // Time:O(logn)
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        // 循环至少要有三个数，才能有意义执行，不然直接去下面比大小就行了
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            // 那边呈上升趋势，就让那边的边界等于max(mid, mid-1)
            if (nums[mid] > nums[mid - 1]) {
                // mid比mid-1大，那就左边等于mid
                left = mid;
            } else {
                // mid比mid-1小，那就右边等于mid-1
                right = mid - 1;
            }
        }
        return nums[left] > nums[right] ? left : right;
    }
}