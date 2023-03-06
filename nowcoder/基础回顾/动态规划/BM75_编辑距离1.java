import java.util.*;


public class Solution {

    // dp[i][j]: 最少操作把 str1[0...i) 转化为 str2[0...j)
    // case 1: str1.charAt(i) == str2.charAt(j),
    //         dp[i][j] = dp[i-1][j-1];
    // case 2: str1.charAt(i) != str2.charAt(j),
    //         insert: dp[i][j] = dp[i][j-1] + 1
    //         delete: dp[i][j] = dp[i-1][j] + 1
    //         modify: dp[i][j] = dp[i-1][j-1] + 1
    //         min(insert, delete, modify)
    // base case: dp[0][j] = j, dp[i][0] = i;
    public int editDistance (String str1, String str2) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        // 多加一位，是为了考虑其中一个字符串为 "" 的情况
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                        dp[i][j] = dp[i-1][j-1];
                    } else {
                        int insert = dp[i][j-1] + 1;
                        int delete = dp[i-1][j] + 1;
                        int modify = dp[i-1][j-1] + 1;
                        dp[i][j] = Math.min(Math.min(insert, delete), modify);
                    }
                }
            }
        }
        return dp[str1.length()][str2.length()];
    }
}
