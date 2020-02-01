## [5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

```
Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Example 2:

Input: "cbbd"
Output: "bb"
```

## Method 1: Brute force
```Java
// Method 1: Brute force: try every substring!
// Time: O(n^3)
public String longestPalindrome(String s) {
    if (s == null || s.length() == 0) {
        return s;
    }
    char[] arr = s.toCharArray();
    int maxLength = 0;
    String res = null;
    for (int i = 0; i < arr.length; i++) {
        for (int j = 0; j <= i; j++) {
            if (isPalindromic(arr, j, i) && maxLength < i - j + 1) {
                maxLength = i - j + 1;
                res = s.substring(j, i + 1);
            }
        }
    }
    return res;
}

private boolean isPalindromic(char[] arr, int left, int right) {
    while (left <= right) {
        if (arr[left] != arr[right]) {
            return false;
        }    
        left++;
        right--;
    }
    return true;
}
```

## Method 2: Dynamic Programming
```java
// Method 2: Dynamic Programming
// Time: O(n^2)
// Space: O(n^2)
// M[i][j] = M[i + 1][j - 1] + 2  (if j - 2 > 2)
public String longestPalindrome(String s) {
    if (s == null || s.length() <= 1) {
        return s;
    }
    int n = s.length();
    int[][] matrix = new int[n][n];
    int globalMax = 1;
    int start = 0;
    // 注意：两重for循环究竟从何开始，是跟induction rules息息相关的
    // 就像本题的性质：Si.....Sj，所以i一定要小于j才可以
    // 而这一点同时也决定了如何填表！
    // 本题有两种填表方式
    // 1. 从左往右，从上到下，竖着填表
    //    for (int j = 0; j < n; j++) 
    //      for (int i = 0; i < j; i++)
    // 2. 从下到上，从左往右，横着填表
    //    for (int i = n - 1; i >=0; i++) 
    //      for (int j = i + 1; j < n; j++)
    for (int j = 0; j < n; j++) {
        matrix[j][j] = 1;
        for (int i = 0; i < j; i++) {
            if (s.charAt(i) == s.charAt(j) && (j - i == 1 || matrix[i + 1][j - 1] > 0)) {
                matrix[i][j] = matrix[i + 1][j - 1] + 2;
                if (globalMax < matrix[i][j]) {
                    start = i;
                    globalMax = matrix[i][j];
                }
            }
            
        }
    }
    return s.substring(start, start + globalMax);
}
```

## Method 3: expand around center
```java
// Method: expand around center
// Time: O(n^2)
public String longestPalindrome(String s) {
    if (s == null || s.length() <= 1) {
        return s;
    }
    char[] arr = s.toCharArray();
    int maxLength = 0;
    int index = -1;
    for (int i = 0; i < arr.length; i++) {
        // case 1: left, mid, right
        int len1 = findLongestPalin(arr, i, i);
        // case 2: left(mid), right
        int len2 = findLongestPalin(arr, i, i + 1);
        int len = Math.max(len1, len2);
        if (len > maxLength) {
            maxLength = len;
            // to make sure get the start index, no matter case1 or case2
            index = i - (len - 1) / 2;
        }
    }
    return s.substring(index, index + maxLength);
}

private int findLongestPalin(char[] arr, int left, int right) {
    // add offset to case 1 and case 2
    // case 1: -1
    // case 2: 0
    int len = left == right ? -1 : 0;
    while (left >= 0 && right < arr.length) {
        if (arr[left] == arr[right]) {
            len += 2;
            left--;
            right++;
        } else {
            break;
        }
    }
    return len;
}
```