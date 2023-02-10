import java.util.*;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param nums int整型一维数组
     * @return int整型
     */
    public int rob (int[] nums) {

        // M[i]: 当打劫到第i家的时候，所获得的最大收益
        // M[i] = Math.max(M[i-2] + nums[i], M[i-1])

        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] maxSum = new int[nums.length];
        maxSum[0] = nums[0];
        maxSum[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            maxSum[i] = Math.max(maxSum[i-2] + nums[i], maxSum[i-1]);
        }
        return maxSum[nums.length - 1];
    }


    public int rob (int[] nums) {
        // M[i]: 当打劫到第i家的时候，所获得的最大收益
        // M[i] = Math.max(M[i-2] + nums[i], M[i-1])
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 考虑到我们每次都只用到了 M[i-2] 和 M[i-1]，所以可以用两个变量来更替保存这两个值，
        // 无需再维护一个数组，从而将空间复杂度降低到 O(1)
        int curMax = 0;
        int prev = 0;
        for (int i = 0; i < nums.length; i++) {
            int tmp = curMax;
            curMax = Math.max(curMax, prev + nums[i]);
            prev = tmp;
        }
        return curMax;
    }

    // 同样是O(1)，第二种理解方式
    public int rob (int[] nums) {
        // Optimize Space: O(1)
        // Two states: Rob and notRob
        // Example: A  B  C  D  E
        //          robC = notRobB + nums[C]
        //       notRobC = Math.max(robB, notRobB)
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int rob = 0;
        int notRob = 0;
        for (int i = 0; i < nums.length; i++) {
            int tmp = rob;
            rob = notRob + nums[i];
            notRob = Math.max(tmp, notRob);
        }
        return Math.max(rob, notRob);
    }
}