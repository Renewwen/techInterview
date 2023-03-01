import java.util.*;

public class Solution {

    // Method 1: 每次比较滑动窗口里的所有值
    // Time: O(n^2)

    // Method 2: 单调队列
    // [2,4,2,3,1,2,5,1], 3
    //    1     i = 4
    // deque实现的单调队列，保证当前滑动窗口的最大值一直在队列的最左边:
    // deque[...]: 每轮循环，先从左边poll掉脱离滑动窗口的值，再从右边poll掉比当前num[i]小的值
    // 保证deque[大 --> 小]
    // 结果就是当前deque中最左边的值
    // Time: O(n), Space:O(size)
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (num == null || num.length == 0 || num.length < size || size <= 0) {
            return res;
        }
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < num.length; i++) {
            if (!deque.isEmpty() && deque.peekFirst() == i - size) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && num[deque.peekLast()] <= num[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i >= size - 1) {
                res.add(num[deque.peekFirst()]);
            }
        }
        return res;
    }

}
