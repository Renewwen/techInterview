import java.util.*;

public class Solution {

    // dp[i][j]: str1.substring(0, i)和str2.substring(0, j)的最长公共子串的长度, 必须以i和j为结尾
    // case 1: str1.charAt(i) != str2.charAt(j), dp[i][j] = 0;
    // case 2: str1.charAt(i) == str2.charAt(j), dp[i][j] = dp[i-1][j-1] + 1;
    // 逻辑没有问题，但每次构建string太浪费时间
    public String LCS (String str1, String str2) {
        int[][] res = new int[str1.length() + 1][str2.length() + 1];
        // 记录最长公共子串最后一个元素在字符串str1中的位置
        int end = -1;
        // 记录最长公共子串的长度
        int maxLen = 0;
        for (int i = 1; i < res.length; i++) {
            for (int j = 1; j < res[0].length; j++) {
                // case 1: dp[i][j] 默认是0，所以不用写
                // case 2:
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    res[i][j] = res[i-1][j-1] + 1;
                    // 记录下来从最初到现在的最大值
                    if (res[i][j] > maxLen) {
                        maxLen = res[i][j];
                        end = i;
                    }
                }
            }
        }
        return str1.substring(end - maxLen, end);
    }

}