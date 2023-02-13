import java.util.*;


public class Solution {

    // [1, 5, 7, 3, 5, 8, 1]
    //  i        j
    // res = Math.max(j - i);
    // Time: O(n^2)
    // LargestNumFromRightToLeft[8, 8, 8, 8, 8, 8, 1]
    // [1, 5, 7, 3, 5, 8, 1]
    //                    i
    // Time:O(n) Space:O(n)
    public int maxProfit (int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // LFR: Largest num in [cur, end]
        int[] LFR = new int[prices.length];
        LFR[prices.length - 1] = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            if (LFR[i + 1] > prices[i]) {
                LFR[i] = LFR[i + 1];
            } else {
                LFR[i] = prices[i];
            }
        }
        // find the result
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            res = Math.max(res, LFR[i] - prices[i]);
        }
        return res;
    }

    // 优化：
    // Time:O(n), Space:O(1)
    public int maxProfit (int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int maxValue = prices[prices.length - 1];
        int res = 0;
        for (int i = prices.length - 2; i >= 0; i--) {
            res = Math.max(res, maxValue - prices[i]);
            maxValue = Math.max(maxValue, prices[i]);
        }
        return res;
    }
}