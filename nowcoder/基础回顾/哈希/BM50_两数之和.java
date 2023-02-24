import java.util.*;

public class Solution {

    // 方法1：HashMap
    public int[] twoSum (int[] numbers, int target) {
        // Map: key: numbers[i], value: index
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (indexMap.containsKey(target - numbers[i])) {
                int index = indexMap.get(target - numbers[i]);
                return new int[]{index + 1, i + 1};
            } else {
                indexMap.put(numbers[i], i);
            }
        }
        throw new IllegalArgumentException("No Solution");
    }
}
