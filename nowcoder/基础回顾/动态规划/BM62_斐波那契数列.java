public class Solution {

    // 1, 1, 2, 3, 5, 8, 13 ...
    // n = n - 1 + n - 2
    // Time: O(n)
    // Space: O(n)
    public int Fibonacci(int n) {
        if (n == 0 ) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[] fib = new int[n];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < n; i++) {
            fib[i] = fib[i-1] + fib[i-2];
        }
        return fib[n-1];
    }

    // 可将空间优化至 O(1)
    // Time: O(n)
    // Space: O(1)
    public int Fibonacci(int n) {
        if (n == 0 ) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int prev = 1;
        int cur = 1;
        while (n > 2) {
            int next = prev + cur;
            prev = cur;
            cur = next;
            n--;
        }
        return cur;
    }
}
