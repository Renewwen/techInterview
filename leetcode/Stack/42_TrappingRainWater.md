## [42. Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


![](https://assets.leetcode.com/uploads/2018/10/22/rainwatertrap.png)

>The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

```
Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
```

### Method 1: Brute Force
```Java
// Method 1: brute force
// Time: O(n^2)
// Space: O(1)
public int trap(int[] height) {
    if (height == null || height.length <= 2) {
        return 0;
    }
    int res = 0;
    for (int i = 1; i < height.length - 1; i++) {
        res += capacity(height, i);
    }
    return res;
}

private int capacity(int[] height, int target) {
    int left = 0;
    for (int i = 0; i < target; i++) {
        left = Math.max(left, height[i]);
    }
    int right = 0;
    for (int i = target + 1; i < height.length; i++) {
        right = Math.max(right, height[i]);
    }
    int res = Math.min(left, right) - height[target];
    return res > 0 ? res : 0;
}
```

### Method 2: Dynamic Programming
```Java
// Method 2: DP
// left[i]: represent the max value from index 0 to index i
// left[i] = Math.max(height[i], left[i-1]);
// Time: O(n)
// Space: O(n)
public int trap(int[] height) {
    if (height == null || height.length <= 2) {
        return 0;
    }
    int[] left = new int[height.length];
    left[0] = height[0];
    for (int i = 1; i < height.length; i++) {
        left[i] = Math.max(height[i], left[i - 1]);
    }
    
    int res = 0;
    int rightMax = height[height.length - 1];
    for (int i = height.length - 2; i >= 0; i--) {
        rightMax = Math.max(rightMax, height[i]);
        int capacity = Math.min(rightMax, left[i]) - height[i];
        res += capacity > 0 ? capacity : 0;
    }
    return res;
}
```

### Method 3: Two Pointers
```Java
// Method : Two Pointers
// Time: O(n)
// Space: O(1)
public int trap(int[] height) {
    if (height == null || height.length <= 2) {
        return 0;
    }
    int left = 0;  // left boundary of be detecting area
    int right = height.length - 1; // right boundary of be detecting area
    int leftMax = 0; // the highest of left, include left
    int rightMax = 0; // the highest of right, include right
    int res = 0; 
    while (left <= right) {
        if (height[left] < height[right]) { // the water will lose from left
            leftMax = Math.max(height[left], leftMax);
            res += leftMax - height[left++];
        } else { // the water will lost from right
            rightMax = Math.max(height[right], rightMax);
            res += rightMax - height[right--];
        }
    }
    return res;
}
```

### Method 4: Monotone Stack (from left to right, decrease)
```Java
// Method 4: Monotone Stack
// 口诀：长江后浪推前浪，前浪死在沙滩上
// Time: O(n)
// Space: O(n) worst-case
public int trap(int[] height) {
    if (height == null || height.length <= 2) {
        return 0;
    }
    int res = 0;
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < height.length; i++) {
        while (!stack.isEmpty() && height[i] > height[stack.peekFirst()]) { 
            int tmp = height[stack.pollFirst()];
            if (stack.isEmpty()) {
                break;
            }
            res += (i - stack.peekFirst() - 1) * (Math.min(height[stack.peekFirst()], height[i]) - tmp);
        }
        stack.offerFirst(i);
    }
    return res;
}
```