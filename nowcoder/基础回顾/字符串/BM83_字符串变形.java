import java.util.*;

public class Solution {
    public String trans(String s, int n) {
        if (n == 0) {
            return "";
        }
        String res = capConvert(s);
        return reverseLetterOrder(res);
    }

    private String capConvert(String s) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur >= 'A' && cur <= 'Z') {
                cur = (char)(cur - 'A' + 'a');
            } else if (cur >= 'a' && cur <= 'z') {
                cur = (char)(cur - 'a' + 'A');
            }
            builder.append(cur);
        }
        return builder.toString();
    }

    private String reverseLetterOrder(String s) {
        StringBuilder builder = new StringBuilder();
        int begin = 0;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur == ' ') {
                String letter = s.substring(begin, i);
                builder.insert(0, ' ' + letter);
                begin = i + 1;
            } else if (i == s.length() - 1) {
                String letter = s.substring(begin, i + 1);
                builder.insert(0, letter);
            }
        }
        return builder.substring(0, builder.length());
    }
}