import java.util.*;

public class Solution {

    // 对数组中的每一个元素，找到 target = 0 - num[i]
    // 对数组中剩下的元素，做 双数之和 = target
    // 找到所有的解:
    // 问题1: 重复元素的问题
    //       1  1 -1 -1 0, 跳过重复的元素，1做完之后，接着从-1开始
    // 问题2: 结果去重
    //       1  1 -1 -1 0, 只能往后找，不能往回找。
    // 问题3: 结果升序排列（输出的时候，排序)

    // 用什么数据结构来实现？HashMap
    // 注意事项：在做 双数之和的时候，也要去重
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (num == null || num.length <= 2) {
            return res;
        }
        Arrays.sort(num);
        for (int i = 0; i < num.length; i++) {
            int target = 0 - num[i];
            twoSum(num, target, i, res);
            while (i < num.length - 1 && num[i] == num[i + 1]) {
                i++;
            }
        }
        return res;
    }

    private void twoSum(int[] num, int target, int start, ArrayList<ArrayList<Integer>> res) {
        if (start > num.length - 3) {
            return;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = start + 1; i < num.length; i++) {
            if (set.contains(target - num[i])) {
                res.add(new ArrayList<>(Arrays.asList(num[start], target - num[i], num[i])));
                while (i < num.length - 1 && num[i] == num[i + 1]) {
                    i++;
                }
            } else {
                set.add(num[i]);
            }
        }
    }

    // 优化twoSum(), 使用双指针，将空间复杂度降为 O(1)
    private void twoSum(int[] num, int target, int start,
                        ArrayList<ArrayList<Integer>> res) {
        if (start > num.length - 3) {
            return;
        }
        int left = start + 1;
        int right = num.length - 1;
        while (left < right) {
            if (num[left] + num[right] == target) {
                res.add(new ArrayList<>(Arrays.asList(num[start], num[left], num[right])));
                while (left < right && num[left] == num[left + 1]) {
                    left++;
                }
                while (left < right && num[right] == num[right - 1]) {
                    right--;
                }
            }
            if (num[left] + num[right] > target) {
                right--;
            } else {
                left++;
            }
        }
    }

}
