/**
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

    // 中序遍历
    // 构建链表：pre.right = cur; cur.left = pre;
    TreeNode head;
    TreeNode pre;

    public TreeNode Convert(TreeNode root) {
        convert(root);
        return head;
    }

    // 返回值：前一个节点，即pre
    private void convert(TreeNode cur) {
        if (cur == null) {
            return;
        }
        convert(cur.left);
        if (pre == null) {
            head = cur;
            pre = head;
        } else {
            cur.left = pre;
            pre.right = cur;
            pre = cur;
        }
        convert(cur.right);
    }
}