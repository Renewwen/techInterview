## [200. Number of Islands](https://leetcode.com/problems/number-of-islands/)

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

```
Example 1:

Input:  11110
        11010
        11000
        00000
Output: 1

Example 2:

Input:  11000
        11000
        00100
        00011
Output: 3
```

### Method 1: Breadth-First Search
```Java
// Method: BFS, and don't change the input value
// Time: O(n * m) 
// Space: O(n * m) 
private static int[][] DIRS = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) {
        return 0;
    }
    int count = 0; // to recode the number of island
    boolean [][] visited = new boolean[grid.length][grid[0].length];
    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
            if (grid[i][j] == '1' && visited[i][j] == false) {
                bfs(grid, i, j, visited);
                count++;
            }
        }
    }
    return count;
}

static class Cell {
    int row;
    int col;
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

private void bfs(char[][] grid, int i, int j, boolean[][] visited) {
    visited[i][j] = true;
    
    Deque<Cell> queue = new ArrayDeque<>();
    queue.offerLast(new Cell(i, j));
    while (!queue.isEmpty()) {
        Cell cur = queue.pollFirst();
        for (int[] dir : DIRS) {
            int row = cur.row + dir[0];
            int col = cur.col + dir[1];
            if (isValid(visited, row, col, grid)) {
                visited[row][col] = true;
                queue.offerLast(new Cell(row, col));
            }
        }
    }
}

private boolean isValid(boolean[][] visited, int row, int col, char[][] grid) {
    return row >= 0 && row < grid.length &&
            col >= 0 && col < grid[0].length &&
            grid[row][col] == '1' &&
            visited[row][col] == false;
}
```

### Method 2: Depth-First Search

```Java
// Method: DFS
// Time: O(n * m) 
// Space: O(n * m) worst-case
private final static int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
        return 0;
    }
    int res = 0;
    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
            if (grid[i][j] == '1') {
                res++;
                dfs(grid, i, j);
            }
        }
    }
    return res;
}

private void dfs(char[][] grid, int i, int j) {
    grid[i][j] = '0';
    for (int[] dir : DIRS) {
        int row = i + dir[0];
        int col = j + dir[1];
        if (isValid(grid, row, col)) {
            dfs(grid, row, col);
        }
    }
}

private boolean isValid(char[][] grid, int i, int j) {
    return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == '1';
}
```