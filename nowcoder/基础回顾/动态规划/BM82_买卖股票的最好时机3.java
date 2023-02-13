import java.util.*;

public class Solution {

    // 跟股票2的状态一致，不过多加了2个状态
    // Time:O(n), Space:O(n)
    public int maxProfit (int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        // 最多可进行两次交易
        int[][] dp = new int[prices.length][5];
        // 初始化dp[0]为最小值
        Arrays.fill(dp[0], -100000);
        dp[0][0] = 0;
        dp[0][1] = 0 - prices[0];
        for (int i = 1; i < prices.length; i++) {
            // 从未持有过股票
            dp[i][0] = dp[i-1][0];
            // 在price[i]之前，包括此刻，完成第一次买入操作后的最大值
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
            // .....第一次卖出......后的最大值
            dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1] + prices[i]);
            // .....第二次买入......后的最大值
            dp[i][3] = Math.max(dp[i-1][3], dp[i-1][2] - prices[i]);
            // .....第二次卖出......后的最大值
            dp[i][4] = Math.max(dp[i-1][4], dp[i-1][3] + prices[i]);
        }
        return Math.max(dp[prices.length - 1][2], Math.max(0, dp[prices.length - 1][4]));
    }
}
