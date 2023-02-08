import java.util.*;

public class Solution {

    // method 1：经典DFS，状态记录 + 回溯
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (num == null || num.length == 0) {
            return res;
        }
        ArrayList<Integer> output = new ArrayList<>();
        boolean[] visit = new boolean[num.length];
        dfs(res, output, num, visit, 0);
        return res;
    }

    private void dfs(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> output,
                     int[] num, boolean[] visit, int depth) {
        if (depth == num.length) {
            res.add(new ArrayList<>(output));
            return;
        }
        for (int i = 0; i < num.length; i++) {
            if (visit[i]) {
                continue;
            }
            output.add(num[i]);
            visit[i] = true;
            dfs(res, output, num, visit, depth + 1);
            visit[i] = false;
            output.remove(output.size() - 1);
        }
    }


    // Method 2: 使用swap省略visit的使用
    // [1, 2, 3]
    // [1, 3, 2]
    // [2, 1, 3]
    // [2, 3, 1]
    // [3, 2, 1]
    // [3, 1, 2]
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
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
            swap(num, depth, i);
            dfs(res, num, depth + 1);
            swap(num, depth, i);
        }
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