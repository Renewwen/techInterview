import java.util.*;

/*
 * public class TreeNode {
 *   int val = 0;
 *   TreeNode left = null;
 *   TreeNode right = null;
 * }
 */

public class Solution {

    // 初始版本：携值向下
    public boolean hasPathSum (TreeNode root, int sum) {
        return hasPathSum(root, sum, 0);
    }

    private boolean hasPathSum (TreeNode root, int sum, int preSum) {
        if (root == null) {
            return false;
        }
        int curSum = root.val + preSum;
        if (root.left == null && root.right == null && curSum == sum) {
            return true;
        }
        return hasPathSum(root.left, sum, curSum) || hasPathSum(root.right, sum, curSum);
    }

    // 优化版本
    public boolean hasPathSum (TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        sum = sum - root.val;
        if (root.left == null && root.right == null && sum == 0) {
            return true;
        }
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }
}