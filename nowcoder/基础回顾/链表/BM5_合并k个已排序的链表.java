import java.util.*;
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {

    // Time: O(nlogk)
    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        PriorityQueue<ListNode> minQueue = new PriorityQueue<>(
                (node1, node2) -> Integer.compare(node1.val, node2.val));
        for (ListNode list : lists) {
            if (list != null) {
                minQueue.offer(list);
                list = list.next;
            }
        }
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (!minQueue.isEmpty()) {
            ListNode tmp = minQueue.poll();
            cur.next = tmp;
            cur = cur.next;
            if (tmp.next != null) {
                minQueue.offer(tmp.next);
            }
        }
        return dummyHead.next;
    }
}