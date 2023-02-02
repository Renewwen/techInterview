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
    // Method 1: 传统的LCA，并未利用BST的特性
    // Time: O(n)
    public int lowestCommonAncestor (TreeNode root, int p, int q) {
        if (root == null) {
            return -1;
        }
        if (root.val == p || root.val == q) {
            return root.val;
        }
        int left = lowestCommonAncestor(root.left, p, q);
        int right = lowestCommonAncestor(root.right, p, q);
        if (left != -1 && right != -1) {
            return root.val;
        }
        return left == -1 ? right : left;
    }

    // Method 2: 利用BST的特性，寻找LCA
    // Time: O(logn)
    public int lowestCommonAncestor (TreeNode root, int p, int q) {
        if (root == null) {
            return -1;
        }
        // p和q都小于root.val，p和q皆在root的左子树，那么LCA一定在左子树
        if (p < root.val && q < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        // p和q都大于root.val，代表p和q皆在root的右子树，LCA一定在右子树
        if (p > root.val && q > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        // p和q分列在左右子树之中，当然root本身也可能就是p，q之一，此时root自己就是LCA
        return root.val;
    }
}