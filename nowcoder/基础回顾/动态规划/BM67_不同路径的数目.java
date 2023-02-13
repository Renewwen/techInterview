import java.util.*;

public class Solution {

    // 构建一个matrix[i][j]
    // matrix[i][j]: 从matrix[0][0], 前进到 matrix[i][j], 一共有多少种走法
    // matrix[i][j] = matrix[i][j-1] + matrix[i-1][j]
    public int uniquePaths (int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        // 保证边界 m[0][j] 和 m[i][0] 都为0，使下标和当前行进的步数保持一致
        int[][] matrix = new int[m+1][n+1];
        // 设置起始条件
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 || j == 1) {
                    matrix[i][j] = 1;
                } else {
                    matrix[i][j] = matrix[i][j-1] + matrix[i-1][j];
                }
            }
        }
        return matrix[m][n];
    }
}