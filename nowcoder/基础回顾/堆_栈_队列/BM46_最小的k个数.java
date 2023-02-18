import java.util.*;

public class Solution {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (input == null || input.length == 0 || k <= 0) {
            return res;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                if (a.equals(b)) {
                    return 0;
                }
                return a > b ? -1 : 1;
            }
        });
        for (int i = 0; i < input.length; i++) {
            if (i < k) {
                pq.offer(input[i]);
            } else {
                if (input[i] < pq.peek()) {
                    pq.poll();
                    pq.offer(input[i]);
                }
            }
        }
        while (!pq.isEmpty()) {
            res.add(pq.poll());
        }
        return res;
    }
