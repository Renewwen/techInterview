import java.util.*;

public class Solution {

    // Map 记录数组中元素出现的次数
    // Set 记录最后的结果
    public int[] FindNumsAppearOnce (int[] array) {
        if (array == null || array.length <= 1) {
            throw new IllegalArgumentException("the input array does not meet the requirements.");
        }
        Set<Integer> res = new HashSet<>();
        for (int num : array) {
            if (!res.contains(num)) {
                res.add(num);
            } else {
                res.remove(num);
            }
        }
        int[] output = new int[2];
        int index = 0;
        for (int num : res) {
            output[index++] = num;
        }
        return output;
    }

}
