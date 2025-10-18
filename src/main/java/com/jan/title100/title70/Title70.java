package com.jan.title100.title70;

/**
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 *
 * 示例 2：
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 *
 * 提示：
 * 1 <= n <= 45
 */
public class Title70 {
    public static void main(String[] args) {
        System.out.println(climbStairs(2));
        System.out.println(climbStairs(3));
        System.out.println(climbStairs(4));
        System.out.println(climbStairs(5));

        System.out.println(climbStairs2(2));
        System.out.println(climbStairs2(3));
        System.out.println(climbStairs2(4));
        System.out.println(climbStairs2(5));

        System.out.println(climbStairs3(2));
        System.out.println(climbStairs3(3));
        System.out.println(climbStairs3(4));
        System.out.println(climbStairs3(5));

        System.out.println(climbStairs4(2));
        System.out.println(climbStairs4(3));
        System.out.println(climbStairs4(4));
        System.out.println(climbStairs4(5));
    }

    /**
     * 动态规划，使用一维数组存放结果
     * 递推式（状态转移方程） f[i]=f[i−1]+f[i−2]
     */
    public static int climbStairs(int n) {
        int[] f = new int[n + 1];
        f[0] = f[1] = 1;
        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];
    }

    /**
     * 动态规划，使用3个常量滚动存放计算数据
     * 递推式（状态转移方程） f[i]=f[i−1]+f[i−2]
     */
    public static int climbStairs2(int n) {
        int f0 = 1, f1 = 1;
        for (int i = 2; i <= n; i++) {
            int new_f = f0 + f1;
            f0 = f1;
            f1 = new_f;
        }
        return f1;
    }

    /**
     * 递归，时间复杂度：O(2^n)，空间复杂度O(n)
     * 没有利用已计算过的值，易超时
     */
    public static int climbStairs3(int n) {
        return dfs(n);
    }
    private static int dfs(int i) {
        if(i <= 1) {
            return 1;
        }
        return dfs(i - 1) + dfs(i - 2);
    }

    /**
     * 递归，时间复杂度：O(2^n)，空间复杂度O(n)
     */
    public static int climbStairs4(int n) {
        int[] memo = new int[n + 1];
        return dfs(n, memo);
    }
    private static int dfs(int i, int[] memo) {
        // 递归边界
        if(i <= 1) {
            return 1;
        }
        // 之前计算过
        if(memo[i] != 0) {
            return memo[i];
        }
        return memo[i] = dfs(i - 1) + dfs(i - 2);
    }
}
