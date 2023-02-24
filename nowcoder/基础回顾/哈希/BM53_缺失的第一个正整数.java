import java.util.*;


public class Solution {

    // 方法 1：
    // 记录所有的结果...然后遍历结果，找到最小值...
    // time: O(n), space: O(n)
    public int minNumberDisappeared (int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int i = 1; i <= set.size(); i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return set.size() + 1;
    }


    // 方法 2：
    // 利用数组下标，来对应 1 ~ n
    // time: O(n), space: O(1)
    public int minNumberDisappeared (int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        // step 1: 将负数改为 n + 1，免除干扰
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                nums[i] = nums.length + 1;
            }
        }
        int i = 0;
        while (i < nums.length) {
            // step 2: 将出现并落在 [1, n] 的正整数的值，都标记为 -1，表示该数字已经出现
            if (nums[i] < nums.length + 1 && nums[i] > 0) {
                int index = nums[i] - 1;
                swap(nums, i, index);
                nums[index] = -1;
            } else {
                i++;
            }
        }
        // step 3: 遍历数组，第一个不为负数的数组下标，+ 1 之后，就是我们要找的最小正整数
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] > 0) {
                return j + 1;
            }
        }
        return nums.length + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
