import java.util.*;

/*
 * public class TreeNode {
 *   int val = 0;
 *   TreeNode left = null;
 *   TreeNode right = null;
 *   public TreeNode(int val) {
 *     this.val = val;
 *   }
 * }
 */

public class Solution {

    // method 1: 使用全局变量
    int pre = Integer.MIN_VALUE;
    public boolean isValidBST (TreeNode root) {
        if (root == null) {
            return true;
        }
        if(!isValidBST(root.left) || root.val < pre) {
            return false;
        }
        pre = root.val;
        return isValidBST(root.right);
    }

    // method 2: 使用参数传递，避免使用全局变量
    public boolean isValidBST (TreeNode root) {
        return isValidBST(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private boolean isValidBST(TreeNode root, int max, int min) {
        if (root == null) {
            return true;
        }
        if (root.val >= max || root.val <= min) {
            return false;
        }
        return isValidBST(root.left, root.val, min) && isValidBST(root.right, max, root.val);
    }
}