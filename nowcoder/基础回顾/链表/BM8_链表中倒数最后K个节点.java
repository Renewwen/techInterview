import java.util.*;

/*
 * public class ListNode {
 *   int val;
 *   ListNode next = null;
 *   public ListNode(int val) {
 *     this.val = val;
 *   }
 * }
 */

public class Solution {

    // 解法：双指针 fast = slow + k,
    // Time: O(n)
    // Space: O(1)
    public ListNode FindKthToTail (ListNode pHead, int k) {
        ListNode fast = pHead;
        ListNode slow = pHead;
        int count = 0;  // 物理意义: fast向前走了几步
        while (fast != null) {
            fast = fast.next;
            if (count >= k) {  // 当fast向前走了k步以后，slow也开始行动
                slow = slow.next;
            }
            // 同时计算整条list的长度，用于判断list长度小于k的特殊情况
            count++;
        }
        // 当循环结束时，slow要再走k步才能追上fast，而fast此时的位置：tail -> null(fast)
        // 此时的slow，正好落位在倒数第k个节点上
        return count < k ? null : slow;
    }
}