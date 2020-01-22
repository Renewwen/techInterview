## [545. Boundary of Binary Tree](https://leetcode.com/problems/boundary-of-binary-tree/)

Given a binary tree, return the values of its boundary in **anti-clockwise** direction starting from root. Boundary includes left boundary, leaves, and right boundary in order without duplicate **nodes**.  (The values of the nodes may still be duplicates.)

**Left boundary** is defined as the path from root to the **left-most** node. **Right boundary** is defined as the path from root to the right-most node. If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. Note this definition only applies to the input binary tree, and not applies to any subtrees.

The **left-most** node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.

The right-most node is also defined by the same way with left and right exchanged.

```
Example 1

Input:
  1
   \
    2
   / \
  3   4

Ouput:
[1, 3, 4, 2]

Explanation:
The root doesn't have left subtree, so the root itself is left boundary.
The leaves are node 3 and 4.
The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
So order them in anti-clockwise without duplicates and we have [1,3,4,2].


Example 2

Input:
    ____1_____
   /          \
  2            3
 / \          / 
4   5        6   
   / \      / \
  7   8    9  10  
       
Ouput:
[1,2,4,7,8,9,10,6,3]

Explanation:
The left boundary are node 1,2,4. (4 is the left-most node according to definition)
The leaves are node 4,7,8,9,10.
The right boundary are node 1,3,6,10. (10 is the right-most node).
So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
```

```Java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // DFS
    // Time: O(logn + n + logn)
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        res.add(root.val);
        // check if leftBoundary exist?
        if (root.left != null) {
            findLeftBoundary(root.left, res);
        }
        // check if leaf exist?
        if (root.left != null || root.right != null) {
            findLeaf(root, res);    
        }
        // check if rightBoundary exist?
        if (root.right != null) {
            findRightBoundary(root.right, res);
        }
        return res;
    }
    
    // only add leftBoundary, not include any leaf(even belong to leftBoundary...)
    private void findLeftBoundary(TreeNode root, List<Integer> res) {
        // left is from top to down, like pre-order
        // so res.add() first, then recursion!
        if (root.left != null) {
            res.add(root.val);
            findLeftBoundary(root.left, res);
        } else if (root.right != null) {
            res.add(root.val);
            findLeftBoundary(root.right, res);
        }
    }
    
    private void findLeaf(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            res.add(root.val);
        }
        findLeaf(root.left, res);
        findLeaf(root.right, res);
    }
    
    // same as leftBoundary
    private void findRightBoundary(TreeNode root, List<Integer> res) {
        // right is from bottom to up, like post-order
        // so recursion first, then res.add()
        if (root.right != null) {
            findRightBoundary(root.right, res);
            res.add(root.val);
        } else if (root.left != null) {
            findRightBoundary(root.left, res);
            res.add(root.val);
        }
    }
}   
```
