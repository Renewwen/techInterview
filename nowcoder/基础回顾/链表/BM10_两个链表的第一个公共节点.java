/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class Solution {

    // Time: O(n)
    // Space: O(1)
    // 解法: 我走过了你的路，我就变成了你；你走过了我的路，你也变成我。所以我们终将在一起。
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode pA = pHead1;
        ListNode pB = pHead2;
        while (pA != pB) {
            pA = pA == null ? pHead2 : pA.next;
            pB = pB == null ? pHead1 : pB.next;
        }
        return pA;
    }
}
