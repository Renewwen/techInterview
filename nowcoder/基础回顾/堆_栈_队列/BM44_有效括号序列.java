import java.util.*;


public class Solution {
    /**
     *
     * @param s string字符串
     * @return bool布尔型
     */
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        Deque<Character> stack = new ArrayDeque<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (isValidCase(arr[i], stack)) {
                continue;
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }

    private boolean isValidCase(char cur, Deque<Character> stack) {
        // case 0: stack 为null，直接添加
        if (stack.isEmpty()) {
            stack.offerLast(cur);
            return true;
        }
        char prev = stack.peekLast();
        // case 1: 左右匹配，相消
        if ((cur == ')' && prev == '(') || (cur == '}' && prev == '{') ||
                (cur == ']' && prev == '[')) {
            stack.pollLast();
            return true;
        }
        // case 2: 上一边为左括号，直接添加
        if (prev == '(' || prev == '[' || prev == '{') {
            stack.offerLast(cur);
            return true;
        }
        return false;
    }

}
