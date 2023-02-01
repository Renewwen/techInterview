
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

    // method 1: 使用deque来实现
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> output = new ArrayList<>();
        if (root == null) {
            return output;
        }
        Deque<TreeNode> level = new ArrayDeque<>();
        // 第1层从左向右，那就理解为第0层是从右向左
        level.offerFirst(root);
        // order == true, 表示方向是从左到右，false是从右到左
        boolean order = true;
        while (!level.isEmpty()) {
            int count = level.size();
            ArrayList<Integer> cur = new ArrayList<>();
            while (count > 0) {
                if (order) {
                    TreeNode node = level.pollFirst();
                    cur.add(node.val);
                    if (node.left != null) {
                        level.offerLast(node.left);
                    }
                    if (node.right != null) {
                        level.offerLast(node.right);
                    }
                } else {
                    TreeNode node = level.pollLast();
                    cur.add(node.val);
                    if (node.right != null) {
                        level.offerFirst(node.right);
                    }
                    if (node.left != null) {
                        level.offerFirst(node.left);
                    }
                }
                count--;
            }
            output.add(cur);
            order = !order;
        }
        return output;
    }
}
