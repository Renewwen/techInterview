import java.util.*;


public class Solution {

    // [3,4,3,2,3,6,4]
    //                i
    //        j
    // res = Math.max(res, i - j)
    // i: arr[i] existed in set
    // j: moving j until set only contains element arr[i] as 1
    // set(), add from i, remove from j
    public int maxLength (int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int fast = 0;
        int slow = 0;
        int res = 0;
        Set<Integer> set = new HashSet<>();
        // [2,2,3,4,3]
        //          i
        //    j
        while (fast < arr.length) {
            if (!set.contains(arr[fast])) {
                set.add(arr[fast]);
                fast++;
            } else {
                // 碰到重复元素的时候，保存当前的最大值
                res = Math.max(res, fast - slow);
                // 移动slow指针，直到消除重复元素
                do {
                    set.remove(arr[slow]);
                    slow++;
                } while (set.contains(arr[fast]));
            }
        }
        return Math.max(res, fast - slow);
    }
}
