import java.util.*;

public class Solution {

    public int MoreThanHalfNum_Solution(int [] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("the input array is null or empty");
        }
        // Map: key: array里面的值; value: 该值出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : array) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) > array.length / 2) {
                return num;
            }
        }
        throw new IllegalArgumentException("no solution");
    }
}
