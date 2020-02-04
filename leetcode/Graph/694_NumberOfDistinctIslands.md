## [694. Number of Distinct Islands](https://leetcode.com/problems/number-of-distinct-islands/)

Given a non-empty 2D array `grid` of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of `distinct` islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

**Example 1:**
```
11000
11000
00011
00011
```
return 1.

**Example 2:**
```
11011
10000
00001
11011
```
return 3


Notice that:
```
11
1
```
and
```
 1
11
```
are considered different island shapes, because we do not consider reflection / rotation.

## Method 1: DFS + HashSet (record the path!)

```java
class Solution {
    // Method 1: DFS + HashSet
    // Time: O(m*n)
    // Space: Worst Case: O(m*n)
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static final char[] arrow = {'d', 'u', 'r', 'l'};
    
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        // Using set to distinguish islands 
        Set<String> set = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    // the last '0' is the depth of dfs
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, sb, 1);
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }
    
    private void dfs(int[][] grid, int i, int j, StringBuilder sb, int depth) {
        grid[i][j] = 0;
        sb.append(depth);
        for (int index = 0; index < DIRS.length; index++) {
            int[] dir = DIRS[index];
            int row = i + dir[0];
            int col = j + dir[1];
            if (isValid(grid, row, col)) {
                sb.append(arrow[index]);
                dfs(grid, row, col, sb, depth + 1);
            }
        }
    }
    
    private boolean isValid(int[][] grid, int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == 1;
    }
}
```