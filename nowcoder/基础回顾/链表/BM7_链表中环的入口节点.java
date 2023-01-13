/*
 public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
*/
public class Solution {

    // 弗洛伊德判圈算法
    // Time: O(n)
    // Space: O(1)
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        ListNode fast = pHead;
        ListNode slow = pHead;

        // fast一次走两个节点，slow一次走一个，当二者相遇时，停止循环
        do {
            if (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            } else {
                // 无环，直接退出
                return null;
            }
        } while (fast != slow);

        fast = pHead;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}