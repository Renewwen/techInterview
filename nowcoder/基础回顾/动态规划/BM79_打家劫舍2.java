import java.util.*;

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param nums int整型一维数组
     * @return int整型
     */
    public int rob (int[] nums) {
        // M[i] = Math.max(M[i-1], M[i-2] + nums[i])
        // 分两种情况来做
        // case 1: 打劫头部，不打劫尾部
        // case 2: 打劫尾部，不打劫头部
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int robFirst = rob(nums, 0, nums.length - 2);
        int robLast = rob(nums, 1, nums.length - 1);
        return Math.max(robFirst, robLast);
    }

    // 打家劫舍1的代码
    private int rob(int[] nums, int start, int end) {
        int rob = 0;
        int notRob = 0;
        for (int i = start; i <= end; i++) {
            int tmp = rob;
            rob = nums[i] + notRob;
            notRob = Math.max(tmp, notRob);
        }
        return Math.max(rob, notRob);
    }
}
