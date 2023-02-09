import java.util.*;

public class Solution {

    // 动态规划
    // dp[i][j]: s1.substring(0, i)和s2.substring(0, j)最长公共子序列是什么
    // case 1: s1.charAt(i-1) == s2.charAt(j-1),
    //         dp[i][j] = dp[i-1][j-1] + s1.charAt(i-1);
    // case 2: s1.charAt(i-1) != s2.charAt(j-1),
    //         dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1])
    public String LCS (String s1, String s2) {
        // 为何要多加一位？
        // 当 i == 0 时表示，当前s1.substring(0, i)是 ""
        String[][] dp = new String[s1.length() + 1][s2.length() + 1];
        // 初始化，当s1取""或者s2取""时，LCS自然为""
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = "";
        }
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = "";
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                // 这里的i-1，表示的是当前i的index
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + s1.charAt(i-1);
                } else {
                    dp[i][j] = dp[i][j-1].length() > dp[i-1][j].length() ? dp[i][j-1] : dp[i-1][j];
                }
            }
        }
        return dp[s1.length()][s2.length()] == "" ? "-1" : dp[s1.length()][s2.length()];
    }
}
