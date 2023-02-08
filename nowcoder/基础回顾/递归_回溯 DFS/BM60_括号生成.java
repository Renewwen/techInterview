import java.util.*;

public class Solution {

    // Method: DFS()
    // 条件：string '0...end', (()
    // n, depth = 2n 一个tree
    // n = 2, 2个'('，2个')'
    //     root
    //       (
    //  (          )
    //  )         （
    //  )          )
    // (())       ()()
    public ArrayList<String> generateParenthesis (int n) {
        ArrayList<String> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }
        StringBuilder builder = new StringBuilder();
        dfs(res, builder, 0, 0, n);
        return res;
    }

    private void dfs(ArrayList<String> res, StringBuilder builder, int left, int right, int n) {
        if (left + right == 2 * n) {
            res.add(builder.toString());
            return;
        }
        if (left < n) {
            builder.append('(');
            dfs(res, builder, left + 1, right, n);
            builder.deleteCharAt(builder.length() - 1);
        }
        if (right < left) {
            builder.append(')');
            dfs(res, builder, left, right + 1, n);
            builder.deleteCharAt(builder.length() - 1);
        }
    }
}