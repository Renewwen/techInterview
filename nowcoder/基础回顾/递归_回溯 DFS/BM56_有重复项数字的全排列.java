import java.util.*;

public class Solution {
    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (num == null || num.length == 0) {
            return res;
        }
        boolean[] isUsed = new boolean[num.length];
        ArrayList<Integer> output = new ArrayList<>();
        Arrays.sort(num);
        dfs(res, output, num, isUsed, 0);
        return res;
    }

    private void dfs(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> output,
                     int[] num, boolean[] isUsed, int depth) {
        if (depth == num.length) {
            res.add(new ArrayList<>(output));
            return;
        }
        for (int i = 0; i < num.length; i++) {
            if (isUsed[i]) {
                continue;
            }
            // 在同一层进行选择，如果在 i 之前，已经有与num[i]相同的值被选择过了，则跳过
            // !isUsed[i-1] 表示上一个没有被访问过，意味着上一个被跳过了，则当前这个相同的也该跳过。
            if (i > 0 && num[i] == num[i-1] && !isUsed[i-1]) {
                continue;
            }
            isUsed[i] = true;
            output.add(num[i]);
            dfs(res, output, num, isUsed, depth + 1);
            isUsed[i] = false;
            output.remove(output.size() - 1);
        }
    }

    // method 2: 利用swap
    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (num == null || num.length == 0) {
            return res;
        }
        dfs(res, num, 0);
        return res;
    }

    private void dfs(ArrayList<ArrayList<Integer>> res, int[] num, int depth) {
        if (depth == num.length) {
            res.add(convertArrayToList(num));
            return;
        }
        for (int i = depth; i < num.length; i++) {
            if (!isUsed(num, depth, i)) {
                swap(num, depth, i);
                dfs(res, num, depth + 1);
                swap(num, depth, i);
            }
        }
    }

    private boolean isUsed(int[] num, int depth, int i) {
        for (int j = depth; j < i; j++) {
            if (num[i] == num[j]) {
                return true;
            }
        }
        return false;
    }

    private void swap(int[] num, int left, int right) {
        int tmp = num[left];
        num[left] = num[right];
        num[right] = tmp;
    }

    private ArrayList<Integer> convertArrayToList(int[] num) {
        ArrayList<Integer> output = new ArrayList<>();
        for (int i : num) {
            output.add(i);
        }
        return output;
    }
}