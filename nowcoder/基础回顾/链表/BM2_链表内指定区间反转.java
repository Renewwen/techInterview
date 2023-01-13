import java.util.*;

/*
 * public class ListNode {
 *   int val;
 *   ListNode next = null;
 * }
 */

public class Solution {

    // 1 -> 2 -> 3 -> 4 -> 5 -> 6
    //           m         n
    // 1 -> 2    5 -> 4 -> 3    6
    // 1 -> 2 -> 5 -> 4 -> 3 -> 6
    public ListNode reverseBetween (ListNode head, int m, int n) {
        // 考虑到m = 1时，head也要参与反转，所以需要一个从不参与反转的新节点来提供头结点的功能。
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        // 找到反转区间的前一个节点
        ListNode pre = dummyHead;
        for (int i = 1; i < m; i++) {
            pre = pre.next;
        }
        // 找到反转起始节点，并断开与之前节点的连接
        ListNode start = pre.next;
        pre.next = null;

        // 找到反转末尾节点
        ListNode end = start;
        for (int i = m; i < n; i++) {
            end = end.next;
        }
        // 找到反转区间末尾节点的下一个节点，并断开连接
        ListNode tailHead = end.next;
        end.next = null;
        // 反转目标区间，并重新连接头尾
        reverseList(start);
        pre.next = end;
        start.next = tailHead;
        return dummyHead.next;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}