import java.util.*;

public class Solution {

    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public int solve (int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int curMax = dfs(matrix, i, j);
                res = Math.max(res, curMax);
            }
        }
        return res;
    }

    private int dfs(int[][] matrix, int row, int col) {
        // 既然能进来，就保证了至少是1
        int res = 1;
        for (int[] dir : DIRS) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (isValid(matrix, nextRow, nextCol, matrix[row][col])) {
                res = Math.max(res, dfs(matrix, nextRow, nextCol) + 1);
            }
        }
        return res;
    }

    private boolean isValid(int[][] matrix, int row, int col, int pre) {
        return row >= 0 && row < matrix.length &&
                col >= 0 && col < matrix[0].length && matrix[row][col] > pre;
    }
}
