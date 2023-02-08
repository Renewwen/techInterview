/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // preOrder [root][left][right]
    //          [8][5,1,7][10,12][MAX_VALUE]
    // inOrder: [1, 5,7,8, 10,12]
    private int preIndex = 0;

    public TreeNode bstFromPreorder(int[] preOrder) {
        return reBuild(preOrder, Integer.MAX_VALUE);
    }

    private TreeNode reBuild(int[] preOrder, int boundry) {
        if (preIndex >= preOrder.length || preOrder[preIndex] > boundry) {
            return null;
        }
        TreeNode root = new TreeNode(preOrder[preIndex]);
        preIndex++;
        root.left = reBuild(preOrder, root.val);
        root.right = reBuild(preOrder, boundry);
        return root;
    }

}