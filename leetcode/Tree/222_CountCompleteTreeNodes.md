## [222. Count Complete Tree Nodes](https://leetcode.com/problems/count-complete-tree-nodes/)

Given a complete binary tree, count the number of nodes.

>Definition of a complete binary tree from Wikipedia:  
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Example:
```
Input: 
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6
```

### Method 1: Traversal The whole tree
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// Method 1: traversal the tree
// Time: O(n)
// Space: O(height)
public int countNodes(TreeNode root) {
    if (root == null) {
        return 0;
    }
    int left = countNodes(root.left);
    int right = countNodes(root.right);
    return left + right + 1;
}

```

### Method 2: Binary Search
```java
// Method 2: binary-Search
// Time: O(log(n)^2)
// perfect Tree: height = h; nodes = 2^h - 1
public int countNodes(TreeNode root) {
    if (root == null) {
        return 0;
    }
    int leftDepth = getHeight(root.left);
    int rightDepth = getHeight(root.right);
    if (leftDepth > rightDepth) {
        // [right-subtree-nodes + root == 2^rightDepth] + left-subtree-nodes
        return (1 << rightDepth) + countNodes(root.left);
    } else {
        return (1 << leftDepth) + countNodes(root.right);
    }
}

private int getHeight(TreeNode root) {
    // complete tree: the height must be decided by the most left node!
    int height = 0;
    while (root != null) {
        height++;
        root = root.left;
    }
    return height;
}
```