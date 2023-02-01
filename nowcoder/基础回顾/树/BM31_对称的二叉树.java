/*
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    // Method 1: 递归
    boolean isSymmetrical(TreeNode pRoot) {
        return isSymmetrical(pRoot, pRoot);
    }

    private boolean isSymmetrical(TreeNode root1, TreeNode root2) {
        // 两者都为空时，可以判定对称
        if (root1 == null && root2 == null) {
            return true;
        }
        // 只有一个空节点或者节点值不同，必定不对称
        if (root1 == null || root2 == null || root1.val != root2.val) {
            return false;
        }
        // 每层对应的节点进入递归比较
        return isSymmetrical(root1.left, root2.right) && isSymmetrical(root1.right, root2.left);
    }

    // Method 2: 使用Queue来简单模拟
    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) return true;
        Queue<TreeNode> qLeft = new LinkedList<>();
        Queue<TreeNode> qRight = new LinkedList<>();

        qLeft.offer(pRoot.left);
        qRight.offer(pRoot.right);
        while (!qLeft.isEmpty() && !qRight.isEmpty()) {
            TreeNode left = qLeft.poll();
            TreeNode right = qRight.poll();
            // 二者都为空时，暂时对称
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            // 左子树：从左往右
            qLeft.offer(left.left);
            qLeft.offer(left.right);
            // 右子树：从右往左
            qRight.offer(right.right);
            qRight.offer(right.left);
        }
        return true;
    }
}
