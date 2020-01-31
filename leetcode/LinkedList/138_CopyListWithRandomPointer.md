## [138. Copy List with Random Pointer](https://leetcode.com/problems/copy-list-with-random-pointer/)

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

The Linked List is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

- val: an integer representing Node.val
- random_index: the index of the node (range from 0 to n-1) where random pointer points to, or null if it does not point to any node.

```
Example 1:
Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]

Example 2:
Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]
```

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/

class Solution {
    // 1 --> 2 --> 3 --> 4 --> 5
    // |     |     |           |
    // 3     1     5           1
    
    // 1' --> 2' --> 3'
    // |      |      |
    // 3'     1'     5'
    
    // 1. we need to recode the relationship between origin node and copied node.
    // Hashmap<Node, Node> key: origin value: copied
    // Time: O(n)
    // Space: O(n)
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node dummy = new Node(0);
        Node cur = dummy;
        while (head != null) {
            // step 1: copy next pointer
            if (!map.containsKey(head)) {
                map.put(head, new Node(head.val));
            }
            cur.next = map.get(head);
            // step 2: copy the random pointer
            if (head.random != null) {  // make sure the random is not point to null
                if (!map.containsKey(head.random)) {
                    map.put(head.random, new Node(head.random.val));
                }
                cur.next.random = map.get(head.random);
            }
            cur = cur.next;
            head = head.next;
        }
        return dummy.next;
    }
}
```
