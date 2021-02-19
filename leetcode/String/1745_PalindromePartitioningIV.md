## [1745. Palindrome Partitioning IV](https://leetcode.com/problems/palindrome-partitioning-iv/)

Given a string s, return true if it is possible to split the string s into three non-empty palindromic substrings. Otherwise, return false.​​​​​

A string is said to be palindrome if it the same string when reversed.

 
```
Example 1:
Input: s = "abcbdd"
Output: true
Explanation: "abcbdd" = "a" + "bcb" + "dd", and all three substrings are palindromes.

Example 2:
Input: s = "bcbddxy"
Output: false
Explanation: s cannot be split into 3 palindromes.
 

Constraints:
    3 <= s.length <= 2000
    consists only of lowercase English letters.
```

## Solution 0: Brute Force for any cuts (Search)
```java
class Solution {
    // Method 1: brute force (search)
    // Time: O(n!)
    // Space: O(n)
    public boolean checkPartitioning(String s) {
        if (s == null || s.length() < 3) {
            return false;
        }
        char[] arr = s.toCharArray();
        return isPartitionAvailable(arr, 0, 0);
    }
    
    private boolean isPartitionAvailable(char[] arr, int index, int count) {
        if (index == arr.length || count > 2) {
            return false;
        }
        if (index == arr.length - 1 && count == 2) {
            return true;
        }
        List<Integer> nextIndexes = findPalindrome(arr, index);
        for (Integer nextIndex : nextIndexes) {
            if (nextIndex == arr.length - 1 && count == 2) {
                return true;
            }
            if (isPartitionAvailable(arr, nextIndex + 1, count + 1)) {
                return true;
            }
        }
        return isPartitionAvailable(arr, index + 1, count + 1);
    }
    
    private List<Integer> findPalindrome(char[] arr, int index) {
        List<Integer> res = new ArrayList<>();
        int cur = index + 1;
        while (cur < arr.length) {
            if (isPalindrome(arr, index, cur)) {
                res.add(cur);
            }
            cur++;
        }
        return res;
    }
    
    private boolean isPalindrome(char[] arr, int start, int end) {
        while (start < end) {
            if (arr[start] != arr[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
``` 

## Solution 1: Brute Force 
```java
class Solution {
    // Method 1: brute force 
    // True: [0, i] && [i + 1, j] && [j + 1, end] 
    // Time: O(n^3)
    // Space: O(1)
    public boolean checkPartitioning(String s) {
        if (s == null || s.length() < 3) {
            return false;
        }
        char[] arr = s.toCharArray();
        for (int i = 0; i < s.length() - 2; i++) {
            for (int j = i + 1; j < s.length() - 1; j++) {
                if (isPalindrome(arr, 0, i) && 
                    isPalindrome(arr, i + 1, j) && 
                    isPalindrome(arr, j + 1, s.length() - 1)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean isPalindrome(char[] arr, int start, int end) {
        while (start < end) {
            if (arr[start] != arr[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
```

## Solution 2: DP
```java
class Solution {
    // Method 2: DP
    // dp[i][j]: is the subString [i, j] is Palindrome or not
    //      dp[i][j] = 1  if i == j, single character
    //      dp[i][j] = 1  if i > j, emtpy string
    //      dp[i][j] = 1  s[i] == s[j] && dp[i+1][j-1]
    // Time: O(n^2)
    // Space: O(n^2)
    public boolean checkPartitioning(String s) {
        if (s == null || s.length() < 3) {
            return false;
        }
        char[] arr = s.toCharArray();
        boolean dp[][] = new boolean[s.length()][s.length()];
        
        // How to file the matrix? from bottom to up
        //   0 1 2 3 4 
        // 0 x ^ ^ ^ ^
        // 1   x | | |
        // 2     x | |
        // 3       x |
        // 4         x
        for (int j = 0; j < s.length(); j++) {
            for (int i = j; i >= 0; i--) {
                if (arr[i] == arr[j]) {
                    dp[i][j] = (i + 1 <= j - 1) ? dp[i + 1][j - 1] : true;
                } 
            }
        }
        
        for (int i = 0; i < s.length() - 2; i++) {
            for (int j = i + 1; j < s.length() - 1; j++) {
                if (dp[0][i] && dp[i + 1][j] && dp[j + 1][s.length() - 1]) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean isPalindrome(char[] arr, int start, int end) {
        while (start < end) {
            if (arr[start] != arr[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

}
```
