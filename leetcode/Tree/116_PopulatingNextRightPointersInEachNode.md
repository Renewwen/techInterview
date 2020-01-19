## [116. Populating Next Right Pointers In Each Node](https://leetcode.com/problems/populating-next-right-pointers-in-each-node/)

You are given a **perfect binary tree** where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

```
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
```

Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

```
Follow up:

You may only use constant extra space.
Recursive approach is fine, 
you may assume implicit stack space does not count as extra space for this problem.
```


Example 1:

<img src="https://assets.leetcode.com/uploads/2019/02/14/116_sample.png" width="450" height="160">

```
Input: root = [1,2,3,4,5,6,7]
Output: [1,#,2,3,#,4,5,6,7,#]
Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
```

### Method 1: Level Order Traversal (BFS)
```java
// Method 1: Level order traversal (BFS)
// Time: O(n)
// Space: O(n)
public Node connect(Node root) {
    if (root == null) {
        return root;
    }
    Deque<Node> queue = new ArrayDeque<>();
    queue.offerLast(root);
    while (!queue.isEmpty()) {
        int size = queue.size();
        Node prev = null;
        for (int i = 0; i < size; i++) {
            Node cur = queue.pollFirst();
            if (prev != null) {
                prev.next = cur;
            }
            if (cur.left != null) {
                queue.offerLast(cur.left);   
            }
            if (cur.right != null) {
                queue.offerLast(cur.right);   
            }
            prev = cur;
        }
        prev.next = null;
    }
    return root;
}
```

### Method 2: Recursion
```Java
// Method 2: recursion 
// Time: O(n)
// Space: O(1)
public Node connect(Node root) {
    if (root == null) {
        return root;
    }
    // for each node, just take care how to populate it's children's next right pointers
    // if root.left != null, that means, root has left and right child
    if (root.left != null) {
        root.left.next = root.right;
        if (root.next != null) { // if root.next != null, that's means the next.left is exist!
            root.right.next = root.next.left;
        }
    }
    // do the same thing for left child and right child
    connect(root.left);
    connect(root.right);
    return root;
}
```