import java.util.*;

public class Solution {

    // 维护：maxHeap（左边部分）, minHeap（右边部分）
    // 原则：maxHeap.size() == minHeap.size() || maxHeap.size() == minHeap.size() + 1
    // 中位数：odd: maxHeap.peek()
    //       even: (maxHeap.peek() + minHeap.peek()) / 2
    // 插入： even: num <= maxHeap.peek(), maxHeap.offer(num);
    //             num > maxHeap.peek(), minHeap.poll() --> maxHeap, minHeap.offer(num)
    //       odd: num <= maxHeap().peek(), maxHeap().poll() --> minHeap, maxHeap.offer(num)
    //            num > maxHeap().peek(), minHeap().offer(num)
    // 优化插入：balance() 可读性

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    public void Insert(Integer num) {
        if (maxHeap.isEmpty() || maxHeap.peek() >= num) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        // rebalance
        rebalance(minHeap, maxHeap);
    }

    public Double GetMedian() {
        if ((minHeap.size() + maxHeap.size()) % 2 == 0) {
            return (minHeap.peek() + maxHeap.peek()) / 2.0; // even
        } else {
            return (double) maxHeap.peek(); // odd
        }
    }

    private void rebalance(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
        if (maxHeap.size() == minHeap.size() + 2){
            minHeap.offer(maxHeap.poll());
        } else if (maxHeap.size() == minHeap.size() - 1) {
            maxHeap.offer(minHeap.poll());
        }
    }

}
