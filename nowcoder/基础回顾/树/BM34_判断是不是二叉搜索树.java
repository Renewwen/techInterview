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
}