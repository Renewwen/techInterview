## [23. Merge K Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/)

Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

```
Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
```

### Method 1: Using Heap
```Java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    // Method 1: Heap
    // Assume: we have lists.length == k; num of nodes == n
    // Time: O(nlogk)
    // Space: O(k)
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        // build the minHeap
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>(){
            @Override
            public int compare(ListNode n1, ListNode n2) {
                if (n1.val == n2.val) {
                    return 0;
                }
                // do not use "return n1.val - n2.val", it could occur OVERFLOW
                return n1.val < n2.val ? -1 : 1;
            }
        });
        
        // Initialize the heap
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                pq.offer(lists[i]);   
            }
        }
        
        // dummy node will help to get the head of result!
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (!pq.isEmpty()) {
            ListNode tmp = pq.poll();
            if (tmp.next != null) {
                pq.offer(tmp.next);
            }
            cur.next = tmp;
            cur = cur.next;
        }
        return dummy.next;
    }
}
```