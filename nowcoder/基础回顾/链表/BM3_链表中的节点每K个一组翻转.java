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
     * @param k int整型
     * @return ListNode类
     */
    // 第一步: 找到head，经过k个node之后的tail，切断之间的联系，保留：prev, head, tail, nextHead,
    // 第二步: 反转链表 head -> tail 变为 tail -> head
    // 第三步: 重新连接 prev -> tail -> head -> nextHead, 重复循环
    public ListNode reverseKGroup (ListNode head, int k) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        ListNode tail = null;
        ListNode cur = head;  // 新循环的起点，即下一轮的head
        while (cur != null) {
            int count = 0;
            while (count < k - 1) {
                cur = cur.next;
                count++;
                if (cur == null) {
                    return dummyHead.next;
                }
            }
            tail = cur;
            cur = cur.next; // 让 cur 充当 nextHead
            // 切断prev -> head, 以及 tail -> nextHead
            prev.next = null;
            tail.next = null;
            // 反转 head -> tail 为 tail -> head
            reverse(head);
            // 连接 prev -> tail, 以及 head -> nextHead
            prev.next = tail;
            head.next = cur;
            // 让 prev 和 head 为下一轮循环做好准备
            prev = head;
            head = cur;
        }
        return dummyHead.next;
    }

    private void reverse(ListNode head){
        ListNode prev = null;
        while(head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
    }
}