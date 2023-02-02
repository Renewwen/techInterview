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

    public boolean isCompleteTree (TreeNode root) {
        if (root == null) {
            return true;
        }
        // ArrayDeque 不接受 null，会报错NPE
        Queue<TreeNode> queue = new ArrayDeque<>();
        boolean flag = false;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            // 左右节点的逻辑一致，当第一次遇见null，说明碰到了叶节点
            // 也就代表着，之后所有的节点都是叶节点，如果还存在节点含有子节点，那么完全二叉树就不成立。
            if (cur.left == null) {
                flag = true;
            } else if (flag) {
                return false;
            } else {
                queue.offer(cur.left);
            }
            if (cur.right == null) {
                flag = true;
            } else if (flag) {
                return false;
            } else {
                queue.offer(cur.right);
            }
        }
        return true;
    }

    // 直接把节点传递进queue里，包含空节点...不推荐
    public boolean isCompleteTree (TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag = false;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            // 当第一次遇到空节点时刻, 则queue中剩余的节点都应为空节点
            if (cur == null) {
                flag = true;
                continue;
            }
            if (flag) {
                return false;
            }
            queue.offer(cur.left);
            queue.offer(cur.right);
        }
        return true;
    }
}