import java.util.*;

public class Solution {

    // Step 1: restore the binary tree
    // preOrder: [root][left][right]
    // inOrder: [left][root][right]
    private int preIndex = 0;
    private int inIndex = 0;

    public int[] solve (int[] xianxu, int[] zhongxu) {
        TreeNode root = restore(xianxu, zhongxu, Integer.MAX_VALUE);
        return rightView(root);
    }

    private TreeNode restore(int[] preOrder, int[] inOrder, int boundry) {
        if (inIndex == inOrder.length || inOrder[inIndex] == boundry) {
            return null;
        }
        TreeNode root = new TreeNode(preOrder[preIndex]);
        preIndex++;
        root.left = restore(preOrder, inOrder, root.val);
        inIndex++;
        root.right = restore(preOrder, inOrder, boundry);
        return root;
    }

    // Step 2: Level order traversal, but from right to left
    private int[] rightView(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            res.add(queue.peek().val);

            int size = queue.size();
            while (size > 0) {
                TreeNode cur = queue.poll();
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                size--;
            }
        }

        int[] output = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            output[i] = res.get(i);
        }
        return output;
    }
}