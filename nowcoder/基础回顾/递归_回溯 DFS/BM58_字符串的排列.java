import java.util.*;

public class Solution {
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return res;
        }
        char[] array = str.toCharArray();
        boolean[] isUsed = new boolean[array.length];
        StringBuilder builder = new StringBuilder();
        dfs(array, isUsed, builder, 0, res);
        return res;
    }

    private void dfs(char[] array, boolean[] isUsed, StringBuilder builder,
                     int depth, ArrayList<String> res) {

        if (depth == array.length) {
            res.add(builder.toString());
            return;
        }

        Set<Character> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            if (isUsed[i] || set.contains(array[i])) {
                continue;
            }
            // 使用set来进行去重，注意：set不参与backtrack，所以不需要进行回撤操作
            set.add(array[i]);
            isUsed[i] = true;
            builder.append(array[i]);
            dfs(array, isUsed, builder, depth + 1, res);
            builder.deleteCharAt(builder.length() - 1);
            isUsed[i] = false;
        }
    }
}