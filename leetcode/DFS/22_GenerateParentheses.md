## [22. Generate Parentheses](https://leetcode.com/problems/generate-parentheses/)

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

```
For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
```

### Method 1: DFS
```Java
// Method: Backtracking
// Time: O(2^(2*n))
// Space: O(n)
public List<String> generateParenthesis(int n) {
    List<String> output = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    dfs(output, sb, n, 0, 0);
    return output;
}

// n store the number of "pair of ()", so total levels == 2 * n
// left: number of '(' added so far
// right: number of ')' added so far
private void dfs(List<String> output, StringBuilder sb, int n, int left, int right) {
    if (sb.length() == 2 * n) {
        output.add(sb.toString());
        return;
    }
    // case 1: add '(' on this level
    if (left < n) {
        sb.append('(');
        dfs(output, sb, n, left + 1, right);
        sb.deleteCharAt(sb.length() - 1);
    }
    // case 2: add ')' on this level
    if (right < left) {
        sb.append(')');
        dfs(output, sb, n, left, right + 1);
        sb.deleteCharAt(sb.length() - 1);
    }
}
```
