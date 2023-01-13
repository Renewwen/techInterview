import java.util.*;

/*
 * public class ListNode {
 *   int val;
 *   ListNode next = null;
 * }
 */

public class Solution {

    // 第一步：找中点
    // 第二步：反转链表 mid 到 tail 的部分
    // 第三部：逐节点对比
    // case 1:
    //     1 -> 2 -> 3 -> 2 -> 1
    //              mid
    //     翻转后:
    //     1 -> 2 ->
    //               3 -> null
    //     1 -> 2 ->
    // case 2:
    //     1 -> 2 -> 2 -> 1
    //              mid
    //     翻转后:
    //     1 -> 2 ->
    //               2 -> null
    //          1 ->
    public boolean isPail (ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode mid = findMid(head);
        mid = reverse(mid);
        while (head != null && mid != null) {
            if (head.val != mid.val) {
                return false;
            }
            head = head.next;
            mid = mid.next;
        }
        return true;
    }

    private ListNode findMid(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode reverse(ListNode head) {
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