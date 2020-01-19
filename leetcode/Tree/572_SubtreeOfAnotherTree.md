## [572. Subtree of Another Tree](https://leetcode.com/problems/subtree-of-another-tree/)

Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

```
Example 1:
Given tree s:
     3
    / \
   4   5
  / \
 1   2

 Given tree t:
   4 
  / \
 1   2
Return true, because t has the same structure and node values with a subtree of s.

Example 2:
Given tree s:
     3
    / \
   4   5
  / \
 1   2
    /
   0

Given tree t:
   4
  / \
 1   2
Return false.
```

### Method 1: Burte force
```java
// Method 1: compare every node in s to t, one by one!
// Time: O(n*m)
// Space: O(n + m)
public boolean isSubtree(TreeNode s, TreeNode t) {
    if (s == null) {
        return false;
    }
    boolean res = isSame(s, t);
    return res || isSubtree(s.left, t) || isSubtree(s.right, t);
}

private boolean isSame(TreeNode s, TreeNode t) {
    if (s == null && t == null) {
        return true;
    }
    if (s == null || t == null) {
        return false;
    }
    if (s.val != t.val) {
        return false;
    }
    return isSame(s.left, t.left) && isSame(s.right, t.right);
}
```


