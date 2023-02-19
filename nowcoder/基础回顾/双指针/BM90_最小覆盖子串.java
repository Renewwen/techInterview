import java.util.*;


public class Solution {

    // target = s, f, g
    // [a, s, d, f, g, h, s, j, k, l]
    //                   fast
    //          slow
    public String minWindow (String S, String T) {
        // 存储结果
        int[] res = new int[3]; // length, left, right
        res[0] = Integer.MAX_VALUE;
        // step 1: 构建Map
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < T.length(); i++) {
            char cur = T.charAt(i);
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }
        int fast = 0;
        int slow = 0;
        int match = 0;
        while (fast < S.length()) {
            char right = S.charAt(fast);
            if (map.containsKey(right)) {
                int count = map.get(right) - 1;
                map.put(right, count);
                if (count == 0) {
                    match++;
                }
                while (match == map.size()) {
                    if (res[0] > fast - slow + 1) {
                        res[0] = fast - slow + 1;
                        res[1] = slow;
                        res[2] = fast + 1;
                    }
                    char left = S.charAt(slow);
                    if (map.containsKey(left)) {
                        int leftCount = map.get(left);
                        map.put(left, leftCount + 1);
                        if (leftCount == 0) {
                            match--;
                        }
                    }
                    slow++;
                }
            }
            fast++;
        }
        return res[0] == Integer.MAX_VALUE ? "" : S.substring(res[1], res[2]);
    }

}