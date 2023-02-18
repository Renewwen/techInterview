import java.util.*;


public class Solution {

    // aba
    // abba
    public boolean judge (String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        int head = 0;
        int tail = str.length() - 1;
        while (head <= tail) {
            if (str.charAt(head) != str.charAt(tail)) {
                return false;
            }
            head++;
            tail--;
        }
        return true;
    }
}