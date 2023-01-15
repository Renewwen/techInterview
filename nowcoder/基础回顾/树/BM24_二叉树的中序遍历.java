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
     * @return int整型一维数组
     */
    public int[] inorderTraversal (TreeNode root) {
        if (root == null) return new int[0];
        List<Integer> list = new ArrayList<>();

        inorder(root, list);

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private void inorder(TreeNode node, List<Integer> list) {
        if (node != null) {
            inorder(node.left, list);
            list.add(node.val);
            inorder(node.right, list);
        }
    }

    // Method: iterative 1
    public int[] inorderTraversal (TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        TreeNode cur = root;
        // 注意条件判断，当stack为空时，cur可能还没打印
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                // 如果当前节点不是null，就将其push进stack里，标记：已访问
                stack.offerFirst(cur);
                // 然后继续向左走，在下一轮循环check
                cur = cur.left;
            } else {
                // 如果curr是null，则回滚到上一个Node
                cur = stack.pollFirst();
                // 此时代表当前Node左子树已经全部打印完毕，然后打印自己
                list.add(cur.val);
                // 接着去看右子树
                cur = cur.right;
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    // Method: Iterative 2x
    public int[] inorderTraversal (TreeNode root) {
        if (root == null) return new int[0];
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        pushLeftNodes(root, stack);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pollFirst();
            list.add(cur.val);
            pushLeftNodes(cur.right, stack);
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private void pushLeftNodes(TreeNode node, Deque<TreeNode> stack) {
        while (node != null) {
            stack.offerFirst(node);
            node = node.left;
        }
    }

}