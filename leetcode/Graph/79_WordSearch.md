## [79. Word Search](https://leetcode.com/problems/word-search/)

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

```
Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
```

### Method 1: DFS
```java
// Method: DFS
// Time: O(m * n * (4^s)) worst case
// Space: O(m * n)

// Four directions
private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

public boolean exist(char[][] board, String word) {
    // corner case
    if (board == null || board.length == 0 || word == null || word.length() == 0) {
        return false;
    }
    // for every character int the grid, do DFS()
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
            if (dfs(board, i, j, 0, word)) {
                return true;
            }
        }
    }
    return false;
}

private boolean dfs(char[][] board, int i, int j, int index, String word) {
    if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(index)) {
        return false;
    }
    if (index == word.length() - 1) {
        return true;
    }
    // change board[i][j] to '0' temporarily, means it has used
    char tmp = board[i][j];
    board[i][j] = '0';
    for (int[] dir : DIRS) {
        int row = i + dir[0];
        int col = j + dir[1];
        if (dfs(board, row, col, index + 1, word)) {
            return true;
        }
    }
    // change it back. (backtracking...)
    board[i][j] = tmp;
    return false;
}
```
