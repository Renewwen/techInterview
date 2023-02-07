import java.util.*;

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

    // 设置两个静态变量，一个用来表示空节点，一个用来分割各个节点
    private static final String NULL = "null";
    private static final String DELIMITER = "#";

    // method 1: 使用 preorder 进行序列化
    String Serialize(TreeNode root) {
        StringBuilder res = new StringBuilder();
        preOrder(root, res);
        // 最后以为是一个 DELIMITER，所以需要去掉，也可以选择不去, string.split() 最后都会处理
        return res.substring(0, res.length() - 1);
    }

    void preOrder(TreeNode root, StringBuilder res) {
        // 当root为空节点时，把定义好的 NULL 加入stringbuilder中，代表空节点
        if (root == null) {
            res.append(NULL).append(DELIMITER);
            return;
        }
        // 当root != null，继续将整个子树序列化
        res.append(root.val).append(DELIMITER);
        preOrder(root.left, res);
        preOrder(root.right, res);
    }

    TreeNode Deserialize(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        Queue<String> queue = new LinkedList<>();;
        // queue.addAll(只能是collection类，比如list，不可是Array)，所以需要使用 Arrays.asList()进行转化
        // string.split(String regex), 通过分隔符，将string拆分为string[]
        queue.addAll(Arrays.asList(str.split(DELIMITER)));
        return restore(queue);
    }

    TreeNode restore(Queue<String> queue) {
        // 当queue.isEmpty()为true时，代表构建完成，直接返回即可
        if (queue.isEmpty()) {
            return null;
        }
        String curStr = queue.poll();
        // 当前节点为空节点，直接返回
        if (curStr.equals(NULL)) {
            return null;
        }
        TreeNode curNode = new TreeNode(Integer.parseInt(curStr));
        curNode.left = restore(queue);
        curNode.right = restore(queue);
        return curNode;
    }
}