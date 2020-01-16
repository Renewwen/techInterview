## [337. House Robber III](https://leetcode.com/problems/house-robber-iii/)

The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

```
Example 1:
Input: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \ 
     3   1

Output: 7 
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

Example 2:
Input: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \ 
 1   3   1

Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
```

```Java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // two state for one node
    // rob = notRobLeft + notRobRight + root.val
    // notRob = max(robLeft, notrobLeft) + max(rotRight, notRobRight)
    // return rob, notRob
    // Time: O(n)
    // Space: O(height)
    int result = 0;
    
    static class States {
        int rob;
        int notRob;
        public States(int rob, int notRob) {
            this.rob = rob;
            this.notRob = notRob;
        }
    }
    
    public int rob(TreeNode root) {
        helper(root);
        return result;
    }
    
    public States helper(TreeNode root) {
        if (root == null) {
            return new States(0, 0);
        }    
        States left = helper(root.left);
        States right = helper(root.right);

        int rob = left.notRob + right.notRob + root.val;
        int notRob = Math.max(left.rob, left.notRob) + 
                     Math.max(right.rob, right.notRob);
        result = Math.max(result, Math.max(rob, notRob));
        
        return new States(rob, notRob);
    }
}
```