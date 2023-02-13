import java.util.*;


public class Solution {

    // 动态规划
    // M[i][j]: 当走到matrix[i][j]的时候，所有路径的最小累加之和
    // M[i][j] = Math.min(M[i][j-1], M[i-1][j]) + matrix[i][j]
    public int minPathSum (int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int[][] sum = new int[matrix.length][matrix[0].length];
        // 处理起点
        sum[0][0] = matrix[0][0];
        // 处理第一行
        for (int j = 1; j < matrix[0].length; j++) {
            sum[0][j] = sum[0][j-1] + matrix[0][j];
        }
        // 处理第一列
        for (int i = 1; i < matrix.length; i++) {
            sum[i][0] = sum[i-1][0] + matrix[i][0];
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                sum[i][j] = Math.min(sum[i][j-1], sum[i-1][j]) + matrix[i][j];
            }
        }
        return sum[matrix.length - 1][matrix[0].length - 1];
    }

}
