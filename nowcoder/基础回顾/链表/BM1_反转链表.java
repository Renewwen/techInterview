/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class Solution {
    // 举例：1 -> 2 -> 3 -> 4

    // 起始: head == 1, prev == null;
    // 起始结果: 1 -> null, prev == 1, 1 == 2;

    // 中间循环：head == 2, prev == 1;
    // 中间结果：2 -> 1, head == 3, prev == 2;
    // ......

    // 终结：head == 4，prev == 3
    // 结果：4 -> 3, head == 4.next == null, prev == 4
    public ListNode ReverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode nextNode = head.next;
            head.next = prev;
            prev = head;
            head = nextNode;
        }
        return prev;
    }

    // 递归
    // 举例：1 -> 2 -> 3 -> 4
    // 走到节点: head == 2
    // 继续向下递归...
    // 开始节点：1 -> 2 -> 3 <- 4
    // 走完节点：1 -> 2 <- 3 <- 4
    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = ReverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
