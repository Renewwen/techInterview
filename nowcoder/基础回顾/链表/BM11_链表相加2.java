import java.util.*;

/*
 * public class ListNode {
 *   int val;
 *   ListNode next = null;
 * }
 */

public class Solution {

    // 数据结构: stack
    public ListNode addInList (ListNode head1, ListNode head2) {
        Deque<ListNode> stack1 = putNodeIntoStack(head1);
        Deque<ListNode> stack2 = putNodeIntoStack(head2);

        ListNode head = null;
        int count = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || count != 0) {
            int num1 = stack1.isEmpty() ? 0 : stack1.pollFirst().val;
            int num2 = stack2.isEmpty() ? 0 : stack2.pollFirst().val;
            int res = num1 + num2 + count;
            int val = res % 10;
            count = res / 10;
            ListNode cur = new ListNode(val);
            cur.next = head;
            head = cur;
        }
        return head;
    }

    private Deque<ListNode> putNodeIntoStack(ListNode head) {
        Deque<ListNode> stack = new ArrayDeque<>();
        while (head != null) {
            stack.offerFirst(head);
            head = head.next;
        }
        return stack;
    }
}