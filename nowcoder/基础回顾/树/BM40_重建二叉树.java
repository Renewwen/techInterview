import java.util.*;
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    // Method 1: 双指针
    // 本质只有一套遍历，我们可以用前序遍历和中序遍历来确认当前的位置
    // 前序遍历：用来确认当前的root是谁？
    // 中序遍历：用来判定当前root的左子树是否已经构建完毕
    // preIndex与inIndex会同步进行, 但各自只会在各自的位置（preroder，inorder）时候++
    private int preIndex;
    private int inIndex;

    public TreeNode reConstructBinaryTree(int [] pre, int [] vin) {
        // 虚拟一个root，将我们最终需要的结果当成其左子树，开启递归
        preIndex = 0;
        inIndex = 0;
        return helper(pre, vin, Integer.MAX_VALUE);
    }

    // target 终止节点的值：
    // inIndex [left, root, right][cur root][left, root, right][upper root][...]
    //    当构建 左子树 的时候，终止条件是 [cur root]，也就是root.val
    //    当构建 右子树 的时候，终止条件是 [upper root]，也就是target
    private TreeNode helper(int[] pre, int[] vin, int target) {
        if (inIndex >= vin.length || vin[inIndex] == target) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preIndex]);
        preIndex++;
        root.left = helper(pre, vin, root.val);
        inIndex++;
        root.right = helper(pre, vin, target);
        return root;
    }
}