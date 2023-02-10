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
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param root TreeNode类
     * @return int整型
     */
    public int rob (TreeNode root) {
        // 对于每一个当前节点：
        // case 1: 偷，left和right都不能偷
        // case 2: 不偷，left和right都可以偷
        // 返回值，必须包含两种状态，偷和不偷
        Status res = getStatus(root);
        return Math.max(res.rob, res.notRob);
    }

    private Status getStatus(TreeNode root) {
        if (root == null) {
            return new Status(0, 0);
        }
        Status left = getStatus(root.left);
        Status right = getStatus(root.right);
        int rob = left.notRob + right.notRob + root.val;
        int notRob = Math.max(left.rob, left.notRob) + Math.max(right.rob, right.notRob);
        return new Status(rob, notRob);
    }

    static class Status {
        int rob;
        int notRob;
        public Status (int rob, int notRob) {
            this.rob = rob;
            this.notRob = notRob;
        }
    }
}