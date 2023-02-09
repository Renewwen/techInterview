import java.util.*;


public class Solution {

    // 动态规划问题：
    // 2, 5, 10, 4, 2, 4
    //           i
    // sum[i]：从底部爬到第i层台阶需要的最小的总花费
    // sum[i] = Math.min(sum[i-2] + cost[i-2], sum[i-1] + cost[i-1])
    // return sum[cost.length]
    public int minCostClimbingStairs (int[] cost) {
        if (cost == null || cost.length <= 2) {
            return 0;
        }
        // 楼梯顶部，意味着爬出数组
        int[] sum = new int[cost.length + 1];
        for (int i = 2; i <= cost.length; i++) {
            sum[i] = Math.min(sum[i-1] + cost[i-1], sum[i-2] + cost[i-2]);
        }
        return sum[cost.length];
    }
}