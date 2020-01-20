## [230. Kth Smallest Element in a BST](https://leetcode.com/problems/kth-smallest-element-in-a-bst/)

Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

**Note:**  
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

```
Example 1:
Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1

Example 2:
Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
```

### Method 1: In-Order Traversal
```java
// Method 1: In-Order traversal
// Time: O(height + k), height == the depth of call stack
// Space: O(height)
public int kthSmallest(TreeNode root, int k) {
    int[] count = new int[1];
    int[] res = new int[1];
    helper(root, k, count, res);
    return res[0];
}

private void helper(TreeNode root, int k, int[] count, int[] res) {
    if (root == null) {
        return;
    }
    helper(root.left, k, count, res);
    count[0]++;
    if (count[0] == k) {
        res[0] = root.val;
        return;
    }
    helper(root.right, k, count, res);
}
```

>**Follow up:**  
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

### Method 2: Rebuild the tree first!
```Java
// Method 2: solve the follow questions, rebuild the tree with count
// Time: O(height) logn..
// Space: O(height)
static class Node {
    int val;
    int count;
    Node left;
    Node right;
    public Node (int val, int count) {
        this.val = val;
        this.count = count;
    }
}

public int kthSmallest(TreeNode root, int k) {
    Node newRoot = rebuild(root);
    return helper(newRoot, k);
}

private Node rebuild(TreeNode root) {
    if (root == null) {
        return null;
    }
    Node cur = new Node(root.val, 0);
    cur.left = rebuild(root.left);
    cur.right = rebuild(root.right);
    
    int tmp = 0;
    if (cur.left != null) { tmp = cur.left.count; }
    if (cur.right != null) { tmp = tmp + cur.right.count; }
    cur.count = tmp + 1;
    return cur;
}

private int helper(Node root, int k) {
    if (root == null || k <= 0) {
        return -1;
    }
    while (k > 0) {
        // check the left child
        if (root.left != null) {
            // if the res in the left subtree, go into it!
            if (root.left.count >= k) {
                root = root.left;
                continue;
            } else { // if not, Minus the num of the left subtree
                k = k - root.left.count;
            }
        } 
        // check if current root is the result
        // be careful, only check this one AFTER checking the left subtree
        if (k == 1) {
            return root.val;
        }
        // go right child
        k--;
        root = root.right;
    }
    return -1;
}
```