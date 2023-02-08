import java.util.*;

public class Solution {

    // 表示方位，左右，上下
    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    // Method 1: DFS
    // 如果找到一个 grid[i][j] == 1, dfs, 找出其中所有的相连点，并标记已访问
    // 遍历整个矩阵
    public int solve (char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        boolean[][] isUsed = new boolean[grid.length][grid[0].length];
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!isUsed[i][j] && grid[i][j] == '1') {
                    res++;
                    findIslands(grid, isUsed, i, j);
                }
            }
        }
        return res;
    }

    private void findIslands(char[][] grid, boolean[][] isUsed, int row, int column) {
        isUsed[row][column] = true;
        for (int[] dir : DIRS) {
            int nextRow = row + dir[0];
            int nextCol = column + dir[1];
            if (isValid(grid, isUsed, nextRow, nextCol)) {
                findIslands(grid, isUsed, nextRow, nextCol);
            }
        }
    }

    private boolean isValid(char[][] grid, boolean[][] isUsed, int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length &&
                grid[row][col] == '1' && !isUsed[row][col];
    }

    // Method 2: BFS
    // 广度优先搜索
    public int solve (char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        boolean[][] isUsed = new boolean[grid.length][grid[0].length];
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!isUsed[i][j] && grid[i][j] == '1') {
                    res++;
                    findIslands(grid, isUsed, i, j);
                }
            }
        }
        return res;
    }

    private void findIslands(char[][] grid, boolean[][] isUsed, int row, int col) {
        Queue<Cell> queue = new LinkedList<>();
        queue.offer(new Cell(row, col));
        while (!queue.isEmpty()) {
            Cell cur = queue.poll();
            isUsed[cur.row][cur.col] = true;
            for (int[] dir : DIRS) {
                int nextRow = cur.row + dir[0];
                int nextCol = cur.col + dir[1];
                if (isValid(grid, isUsed, nextRow, nextCol)) {
                    queue.offer(new Cell(nextRow, nextCol));
                }
            }
        }
    }

    static class Cell {
        int row;
        int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

}
