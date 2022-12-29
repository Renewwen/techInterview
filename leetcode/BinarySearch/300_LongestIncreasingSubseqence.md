## [300. Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/)

Given an integer array nums, return the length of the longest strictly increasing subsequence.

A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].

```
Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Example 2:
Input: nums = [0,1,0,3,2,3]
Output: 4

Example 3:
Input: nums = [7,7,7,7,7,7,7]
Output: 1
 
Constraints:

1 <= nums.length <= 2500
-104 <= nums[i] <= 104

Follow up:
Could you come up with the O(n2) solution?
Could you improve it to O(n log(n)) time complexity?
```

## Solution 0: Recursive (Brute Force) (Search method)
```java
class Solution {
    // Time: O(2^n)
    // Space: O(n^2)
    public int lengthOfLIS(int[] nums) {
        return lengthOfLIS(nums, Integer.MIN_VALUE, 0);
    }
    
    // prev: [0, .... , prev, index, ..., nums.length - 1]
    // index: the first element of current array [index, nums.length - 1]
    public int lengthOfLIS(int[] nums, int prev, int index) {
        if (index == nums.length) {
            return 0;
        }
        // case 1: nums[index] can be included in LIS
        int taken = 0;
        if (nums[index] > prev) {
            taken = 1 + lengthOfLIS(nums, nums[index], index + 1);
        }
        // case 2: nums[index] be not included in LIS
        int notTaken = lengthOfLIS(nums, prev, index + 1);
        return Math.max(taken, notTaken);
    }
    
}
```

## Solution 1: DP (From Tail to Head)
```java
class Solution {
    // Time: O(n^2)
    // Space: O(n)
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
         return 0;
        }
        // the longest increasing subsequence array:
        // array[i] means the LIS of nums[i, nums.length - 1]
        int[] array = new int[nums.length];
        // the first element is the base case
        array[nums.length - 1] = 1;
        int result = 1;
        // so we can start from the second elements
        for (int i = nums.length - 2; i >= 0; i--) {
            int maxVal = 0;
            for (int j = 1; i + j < nums.length; j++) {
                if (nums[i] < nums[i + j]) {
                    maxVal = Math.max(maxVal, array[i + j]);
                }
            }
            array[i] = maxVal + 1;
            result = Math.max(result, array[i]);
        }
        return result;
    }

}
```

## Solution 2: DP (From Head to Tail)
```java
class Solution {
    // Time: O(n^2)
    // Space: O(n)
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
         return 0;
        }
        // the longest increasing subsequence array:
        // array[i] means the LIS of nums[0, i]
        int[] array = new int[nums.length];
        // the first element is the base case
        array[0] = 1;
        int result = 1;
        // start from the second elements
        for (int i = 1; i < nums.length; i++) {
            int maxValPrev = 0;
            for (int j = 1; i - j >= 0; j++) {
                if (nums[i] > nums[i - j]) {
                    maxValPrev = Math.max(maxValPrev, array[i - j]); 
                }
                array[i] = maxValPrev + 1;
                result = Math.max(result, array[i]);
            }
        }
        return result;
    }

}
```

## Solution 3: Binary Search
```java
class Solution {
    // Method 3: DP + Binary Search
    // Time: O(nlogn)
    // Space: O(n)
    
    // DP[i]: the smallest ending numebr of a subsequence that has length i + 1
    // For each num, we can use it to
    //   1. Extend the longest subsequence
    //   2. Replace a number to generate a better subsequence
    // result = length(dp)
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int len = 1;  // the result
        int right = 0; // the right boundary of dp[]
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            // using binary search find the dp[i] can be replace by num
            int pos = binarySearch(dp, right, num);
            dp[pos] = num;
            if (pos > right) {
                len++;
                right++;
            }
        }
        return len;
    }
    
    // find the first element equal or bigger than target
    private int binarySearch(int[] dp, int right, int target) {
        int left = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (dp[mid] == target) {
                return mid;
            } else if (dp[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // dp[right] < target: means target is bigger than every element in dp, need to append
        return dp[right] < target ? right + 1 : right;
    } 
}
```
