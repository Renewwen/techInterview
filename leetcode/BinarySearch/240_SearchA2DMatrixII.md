## [240. Search a 2D Matrix II](https://leetcode.com/problems/search-a-2d-matrix-ii/)

Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

- Integers in each row are sorted in ascending from left to right.
- Integers in each column are sorted in ascending from top to bottom.

```
Example:

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]

Given target = 5, return true.

Given target = 20, return false.
```

## Method 1: Brute force
```java
// Method 1: brute foce
// Time: O(n^2)
public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0) {
        return false;
    }
    for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[i][j] == target) {
                return true;
            }
        }
    }
    return false;
}
```

## Method 2: young tableaux
```Java
// Method 2: Young tableaux
// Time: O(n)
public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0) {
        return false;
    }    
    int row = matrix.length - 1;
    int col = 0;
    // for the condition, better to keep the physical meaning of row and col unified. 
    while (row >= 0 && col <= matrix[0].length - 1) {
        if (matrix[row][col] == target) {
            return true;
        } else if (matrix[row][col] > target) {
            // all elements on this row is bigger than target
            row--;
        } else {
            // all elements on this col is smaller than target
            col++;
        }
    }
    return false;
}
```