import java.util.*;

/*
 * public class ListNode {
 *   int val;
 *   ListNode next = null;
 * }
 */

public class Solution {
    /**
     *
     * @param head ListNode类
     * @param n int整型
     * @return ListNode类
     */
    public ListNode removeNthFromEnd (ListNode head, int n) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode fast = dummyHead;
        ListNode prev = dummyHead; // prev的物理意义：被删除节点前面的那个节点
        int count = 0;
        while (fast != null) {
            fast = fast.next;
            if (count > n) {
                prev = prev.next;
            }
            count++;
        }
        ListNode target = prev.next;
        prev.next = prev.next.next;
        target.next = null;
        return dummyHead.next;
    }
}