public class Solution {
    // Method 1: 先求高，再判断
    // Time: O(nlogn)
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) {
            return true;
        }
        int left = height(root.left);
        int right = height(root.right);
        if (Math.abs(left - right) > 1) {
            return false;
        }
        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    // Method 2: 一起判断
    // Time: O(n)
    // 原来的两轮遍历，返回了两个值，true/false 和 height
    // 我们可以将二者合并，这样一轮遍历就能完成
    // -1 == false, 0 ~ 正无穷 == true和height
    public boolean IsBalanced_Solution(TreeNode root) {
        return helper(root) == -1 ? false : true;
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }
}
