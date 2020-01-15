## [198. House Robber](https://leetcode.com/problems/house-robber/)

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

```
Examples 1:
Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3). Total amount you can rob = 1 + 3 = 4.

Examples 2:
Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1). Total amount you can rob = 2 + 9 + 1 = 12.
```

```java
    // Dynamic Programming
    // M[i] = Math.max(M[i-1], M[i-2] + arr[i])
    // Time: O(n)
    // Space: O(n)  
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] M = new int[nums.length];
        M[0] = nums[0];
        M[1] = Math.max(M[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            M[i] = Math.max(M[i-1], M[i-2] + nums[i]);
        }
        return M[nums.length - 1];
    }

    // Optimize Space: O(1)
    // Two states: Rob and notRob
    // Example: A  B  C  D  E
    //          robC = notRobB + nums[C]
    //       notRobC = Math.max(robB, notRobB) 
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int rob = nums[0];
        int notRob = 0;
        for (int i = 1; i < nums.length; i++) {
            int tmp = rob;
            rob = notRob + nums[i];
            notRob = Math.max(tmp, notRob);
        }
        return Math.max(rob, notRob);
    }
```