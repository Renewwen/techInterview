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

## Solution 1:
```java
class Solution {
    // Method 1: brute force (search)
    // Time: worst case: O(n^n)
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