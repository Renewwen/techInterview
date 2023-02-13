import java.util.*;

public class Solution {

    // 方法1：贪心算法
    // [2,4,5,6,4,3,6,8,6,5,2]
    // 如果我们仔细观察，可以看出，我们能取得的最大利润，其实就是吃到每一波短线，而非长线长持。
    public int maxProfit (int[] prices) {
        if(prices == null || prices.length <= 1) {
            return 0;
        }
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            sum = sum + Math.max(0, prices[i] - prices[i-1]);
        }
        return sum;
    }

    // 方法2：DP
    // [2,4,5,6,4,3,6,8,6,5,2]
    // dp[i][0]: 第i天，不持有股票时，当前的最大收益
    // dp[i][1]: 第i天，持有股票时，当前的最大收益
    // dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
    // dp[i][1] = Math.max(dp[i-1][1], dp[i-1][1] - prices[i]);
    // res = dp[i-1][0];
    public int maxProfit (int[] prices) {
        if(prices == null || prices.length <= 1) {
            return 0;
        }
        // 设置初始启动值
        int[][] profit = new int[prices.length][2];
        profit[0][0] = 0;
        profit[0][1] = 0 - prices[0];
        // 启动逻辑循环
        for (int i = 1; i < prices.length; i++) {
            profit[i][0] = Math.max(profit[i-1][0], profit[i-1][1] + prices[i]);
            profit[i][1] = Math.max(profit[i-1][1], profit[i-1][0] - prices[i]);
        }
        // 最后一天不持有股票，买出
        return profit[prices.length - 1][0];
    }

}