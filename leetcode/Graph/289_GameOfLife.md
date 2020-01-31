## [289. Game of Life](https://leetcode.com/problems/game-of-life/)

According to the Wikipedia's article: "**The Game of Life**, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

1. Any live cell with fewer than two live neighbors dies, as if caused by under-population.
2. Any live cell with two or three live neighbors lives on to the next generation.
3. Any live cell with more than three live neighbors dies, as if by over-population..
4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.

```
Input: 
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
Output: 
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]
```

Follow up:
- Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.  

- In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?

## Method 1: Basic

```java
class Solution {
    // Method 1: create a new Matrix and calculate every element's neighbor
    // Time: O(m*n)
    // Space: O(m*n)
    private static final int[][] DIRS = {{1,0}, {-1,0}, {0,1}, {0,-1}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};
    
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        int[][] next = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                next[i][j] = nextState(board, i, j);
            }
        }
        // copy result
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = next[i][j];
            }
        }
    }
    
    private int nextState(int[][] board, int i, int j) {
        int count = 0;
        for (int[] dir : DIRS) {
            int row = i + dir[0];
            int col = j + dir[1];
            if (isValid(board, row, col)) {
                count++;
            }
        }
        if (count == 2 || count == 3) {
            if (count == 2 && board[i][j] == 0) {
                return 0;
            }  
            return 1;  
        }
        return 0;
    }
    
    private boolean isValid(int[][] board, int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length && board[row][col] == 1;
    }
}
```

## Method 2: In place

```java
class Solution {
    // Method 2: inplace!
    // Time: O(m*n)
    // Space: O(1)
    private static final int[][] DIRS = {{1,0}, {-1,0}, {0,1}, {0,-1}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};
    
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = nextState(board, i, j);
            }
        }
        // restore the result
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1 || board[i][j] == -4) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }
    }
    
    // using number to express different states
    // 1: from live to live
    // 4: from live to die
    // 0: from die to die
    // -4: from die to live
    private int nextState(int[][] board, int i, int j) {
        int count = 0;
        for (int[] dir : DIRS) {
            int row = i + dir[0];
            int col = j + dir[1];
            if (isValid(board, row, col)) {
                count++;
            }
        }
        if (count == 2 || count == 3) {
            if (board[i][j] == 0) {
                return count == 3 ? -4 : 0;    
            }
            return 1;
        } else {
            return board[i][j] == 1 ? 4 : 0;
        }
    }
    
    private boolean isValid(int[][] board, int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length && board[row][col] >= 1;
    }
}
```