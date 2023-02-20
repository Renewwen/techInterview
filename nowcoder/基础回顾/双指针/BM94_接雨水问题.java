import java.util.*;

public class Solution {

    // [3, 1, 2, 5, 2, 4]
    // 方法 1: 暴力解：1. 计算每个值左右两边的最大值，Math.min(leftMax, rightMax)
    //               2. 将所有的值加起来
    //               Time: O(n^2)
    // 方法 2: DP（动态规划）：先用两个数组，数组1：左边最大值，数组2：右边最大值
    //               Time: O(n), Space: O(n)
    // 方法 3: 仔细理解：水都是从左右两边流走的，如果 LeftMax < rightMax, 水只能从左边流
    public long maxWater (int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 左右指针，代表当前位置
        int left = 0;
        int right = arr.length - 1;
        // 代表当前左右边界，最大高度
        int leftMax = 0;
        int rightMax = 0;
        // 最后的结果，即能接多少雨水
        int res = 0;
        while (left <= right) {
            if (leftMax > rightMax) {
                rightMax = Math.max(rightMax, arr[right]);
                res += rightMax - arr[right];
                right--;
            } else {
                leftMax = Math.max(leftMax, arr[left]);
                res += leftMax - arr[left];
                left++;
            }
        }
        return res;
    }
}
