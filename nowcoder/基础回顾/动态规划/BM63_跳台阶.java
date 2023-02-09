class Solution {

    // 经典的动态规划问题：
    // M[n]: 表示跳到第n级，有多少种不同的跳法
    //      case 1: 在第n-1阶，跳1格到，第n阶
    //      case 2: 在第n-2阶，跳2格到，第n阶
    // M[n] = M[n-1] + M[n-2]
    public int climbStairs(int n) {
        if (n <= 0) {return 0;}
        if (n == 1) {return 1;}
        int[] sum = new int[n + 1];
        // 设置启动值，当只有1阶的时候，只能跳一次
        sum[1] = 1;
        // 2阶的时候，跳2次
        sum[2] = 2;
        for (int i = 3; i <= n; i++) {
            sum[i] = sum[i - 1] + sum[i - 2];
        }
        return sum[n];
    }

    // method 2: 不使用数组
    int jumpFloor(int number) {
        if (number <= 0) {
            return 0;
        }
        if (number == 1) {
            return 1;
        }
        int pre = 1;
        int cur = 2;
        while (number > 2) {
            int next = cur + pre;
            pre = cur;
            cur = next;
            number--;
        }
        return cur;
    }
}